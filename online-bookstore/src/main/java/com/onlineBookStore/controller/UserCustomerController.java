package com.onlineBookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.service.UserService;
import com.onlineBookStore.validation.UserValidation;

@RestController
@RequestMapping("/users/customer")
public class UserCustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

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
