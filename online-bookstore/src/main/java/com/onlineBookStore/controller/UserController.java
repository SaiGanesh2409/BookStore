package com.onlineBookStore.controller;

import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.service.UserService;
import com.onlineBookStore.validation.UserValidation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/all")
	public List<UserDTO> getAllUsers() {
		List<UserDTO> allUsers = new ArrayList<>();
		allUsers = userService.getAllUsers();
		return allUsers;
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "User with id " + id + " deleted";
	}

	@PutMapping("{id}")
	public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return userService.updateUser(id, userDTO);
	}
}
