package com.onlineBookStore.mapper;

import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.enums.Role;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.Entity.User;

public class UserMapper {

	// User(Entity) converting into DTO
	public static UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());

		if (user.getRole() != null) {
			userDTO.setRole(user.getRole().name()); // Convert Enum to String
		}

		return userDTO;
	}

	// DTO converting into User(Entity)
	public static User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		
		if (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) {
			try {
				user.setRole(Role.valueOf(userDTO.getRole().toUpperCase())); // Convert String to Enum
			} catch (IllegalArgumentException e) {
				throw new UserValidationException("Invalid role: " + userDTO.getRole());
			}
		}

		return user;
	}
}
