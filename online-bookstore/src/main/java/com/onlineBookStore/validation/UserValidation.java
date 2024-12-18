package com.onlineBookStore.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.service.impl.UserServiceImpl;

@Component
public class UserValidation {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	public List<String> validate(UserDTO userDTO) {
		List<String> errorMessages = new ArrayList<>();

		// UserName Validation
		String userName = userDTO.getUserName().trim();
		if (userName == null || userName.isEmpty()) {
			errorMessages.add("Username cannot be empty");
		} else if (userName.length() < 3 || userName.length() > 50) {
			errorMessages.add("Username must be between 3 and 50 characters");
		} else if (userServiceImpl.isUserNameExist(userName)) {
			errorMessages.add("User Name already exists");
		}

		// FirstName Validation
		String firstName = userDTO.getFirstName().trim();
		if (firstName == null || firstName.isEmpty()) {
			errorMessages.add("First Name cannot be empty");
		} else if (firstName.length() < 3 || firstName.length() > 50) {
			errorMessages.add("First Name must be between 3 and 50 characters");
		}

		// SecondName Validation
		String secondName = userDTO.getLastName().trim();
		if (secondName == null || secondName.isEmpty()) {
			errorMessages.add("Second Name cannot be empty");
		} else if (secondName.length() < 3 || secondName.length() > 50) {
			errorMessages.add("Second Name must be between 3 and 50 characters");
		}

		// Email Validation
	//	UserServiceImpl userServiceImpl = new UserServiceImpl();
		String email = userDTO.getEmail().trim();
		if (email == null || email.isEmpty()) {
			errorMessages.add("Email cannot be empty");
		} else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			errorMessages.add("Invalid email format");
		} else if (userServiceImpl.isEmailExist(email)) {
			errorMessages.add("Email already exists");
		}

		// Password Validation
		String password = userDTO.getPassword().trim();
		if (password == null || password.isEmpty()) {
			errorMessages.add("Password cannot be empty");
		} else if (password.length() < 6) {
			errorMessages.add("Password must be at least 6 characters long");
		} else if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$")) {
			errorMessages.add("Password must contain at least one letter, one number, and one special character");
		}

		return errorMessages;
	}
}
