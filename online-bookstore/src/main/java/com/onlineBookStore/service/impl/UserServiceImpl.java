package com.onlineBookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBookStore.Entity.User;
import com.onlineBookStore.dto.UserDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.mapper.UserMapper;
import com.onlineBookStore.repository.UserRepository;
import com.onlineBookStore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO getUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return UserMapper.toDTO(userOptional.get());
		} else {
			throw new UserValidationException("User not found");
		}
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = UserMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		return UserMapper.toDTO(savedUser);
	}

	@Override
	public boolean isEmailExist(String email) {
		boolean res = userRepository.existsByEmail(email);
		return res;
	}

	@Override
	public boolean isUserNameExist(String userName) {
		boolean res = userRepository.existsByUserName(userName);
		return res;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : allUsers) {
			userDTO.add(UserMapper.toDTO(user));
		}
		return userDTO;
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			userRepository.delete(user);
		} else {
			throw new UserValidationException("User with ID " + id + " not found");
		}
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			user.setUserName(userDTO.getUserName());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setEmail(userDTO.getEmail());
			userRepository.save(user);
			return UserMapper.toDTO(user);
		} else {
			throw new UserValidationException("User not found");
		}

	}

}
