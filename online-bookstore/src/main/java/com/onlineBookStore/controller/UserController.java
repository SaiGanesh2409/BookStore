package com.onlineBookStore.controller;

import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.service.UserService;
import com.onlineBookStore.validation.UserValidation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        List<String> errorMessages = userValidation.validate(userDTO);

		if (!errorMessages.isEmpty()) {
			throw new UserValidationException(String.join(", ", errorMessages));
		}

		UserDTO createdUser = userService.createUser(userDTO);
		return ResponseEntity.ok().body(createdUser);
	}
}
