package com.onlineBookStore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBookStore.Entity.User;
import com.onlineBookStore.dto.UserDTO;
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
			throw new RuntimeException("User not found");
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

}
