package com.onlineBookStore.service;

import com.onlineBookStore.dto.UserDTO;

public interface UserService {

	UserDTO getUserById(Long id);

	UserDTO createUser(UserDTO userDTO);
	
	boolean isEmailExist(String email);
	
	boolean isUserNameExist(String userName);
}
