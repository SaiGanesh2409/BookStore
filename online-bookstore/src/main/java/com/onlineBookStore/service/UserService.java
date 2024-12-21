package com.onlineBookStore.service;

import java.util.List;

import com.onlineBookStore.dto.UserDTO;

public interface UserService {

	UserDTO getUserById(Long id);
	
	List<UserDTO> getAllUsers();

	UserDTO createUser(UserDTO userDTO);
	
	boolean isEmailExist(String email);
	
	boolean isUserNameExist(String userName);
	
	void deleteUser(Long id);
	
	UserDTO updateUser(Long id, UserDTO userDTO);
}
