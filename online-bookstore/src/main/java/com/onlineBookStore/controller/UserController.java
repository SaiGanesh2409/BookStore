package com.onlineBookStore.controller;

import com.onlineBookStore.dto.LoginRequest;
import com.onlineBookStore.dto.LoginResponse;
import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.jwtutil.JwtUtil;
import com.onlineBookStore.service.UserService;
import com.onlineBookStore.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
			String token = jwtUtil.generateToken(userDetails.getUsername());
			return ResponseEntity.ok(new LoginResponse(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	// Get a user by ID
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	// Create a new user
	@PostMapping("/registerCustomer")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		List<String> errorMessages = userValidation.validate(userDTO);
		if (!errorMessages.isEmpty()) {
			throw new UserValidationException(String.join(", ", errorMessages));
		}
		UserDTO createdUser = userService.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // 201 Created
	}

	// Create a new user as admin
	@PostMapping("/registerAdmin")
	public ResponseEntity<UserDTO> createAdminUser(@RequestBody UserDTO userDTO) {
		List<String> errorMessages = userValidation.validate(userDTO);
		if (!errorMessages.isEmpty()) {
			throw new UserValidationException(String.join(", ", errorMessages));
		}
		UserDTO createdUser = userService.createAdminUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // 201 Created
	}

	// Get all users
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	// Delete a user by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("User with id " + id + " deleted");
	}

	// Update an existing user
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		UserDTO updatedUser = userService.updateUser(id, userDTO);
		return ResponseEntity.ok(updatedUser);
	}
}
