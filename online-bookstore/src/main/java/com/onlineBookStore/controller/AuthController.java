package com.onlineBookStore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBookStore.dto.LoginRequest;
import com.onlineBookStore.dto.LoginResponse;
import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.dto.LoginHistoryDTO;
import com.onlineBookStore.jwtutil.JwtUtil;
import com.onlineBookStore.service.UserService;
import com.onlineBookStore.service.LoginHistoryService;
import com.onlineBookStore.exceptions.UserValidationException;

@RestController
@RequestMapping("/users/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	private final UserService userService;
	private final LoginHistoryService loginHistoryService;

	// Constructor Injection for dependencies
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService, UserService userService, LoginHistoryService loginHistoryService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
		this.loginHistoryService = loginHistoryService;
	}

	// Login method: Logs in using either username or email
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    try {
	        String identifier = loginRequest.getUserNameOrEmail(); // Could be username or email
	        
	        if (identifier == null || identifier.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or email is required");
	        }

	        UserDTO userDTO;

	        // Fetch the user by username or email
	        if (identifier.contains("@")) {
	            userDTO = userService.findByEmail(identifier);
	        } else {
	            userDTO = userService.findByUserName(identifier);
	        }

	        // Authenticate using the found username and provided password
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(userDTO.getUserName(), loginRequest.getPassword())
	        );

	        // Generate JWT token
	        String token = jwtUtil.generateToken(userDTO.getUserName());

	        // Record login event
	        LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO();
	        loginHistoryDTO.setUserId(userDTO.getId());
	        loginHistoryDTO.setRole(userDTO.getRole());
	        loginHistoryService.recordLogin(loginHistoryDTO);

	        return ResponseEntity.ok(new LoginResponse(token));

	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/email or password");
	    } catch (UserValidationException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}


	// Logout method: Invalidates the JWT token
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.badRequest().body("Invalid Authorization header");
		}

		String token = authHeader.substring(7); // Remove "Bearer " prefix

		// Invalidate the token (add to blacklist or database)
		boolean isTokenInvalidated = jwtUtil.invalidateToken(token);

		if (isTokenInvalidated) {
			// Record logout event
			// Extract user details from the token and record logout
			String username = jwtUtil.extractUsername(token);
			UserDTO userDTO = userService.findByUserName(username); // Could be username or email

			LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO();
			loginHistoryDTO.setUserId(userDTO.getId());
			loginHistoryDTO.setRole(userDTO.getRole());
			loginHistoryService.recordLogout(loginHistoryDTO);

			return ResponseEntity.ok("Successfully logged out");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error invalidating token");
		}
	}
}
