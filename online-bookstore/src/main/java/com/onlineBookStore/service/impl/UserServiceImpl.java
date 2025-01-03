package com.onlineBookStore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
			userDTO.setRole("ROLE_CUSTOMER");
		}
		String encodedPasswod = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(encodedPasswod);
		User user = UserMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		return UserMapper.toDTO(savedUser);
	}

	@Override
	public UserDTO createAdminUser(UserDTO userDTO) {
		if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
			userDTO.setRole("ROLE_ADMIN");
		}
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(encodedPassword);
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
	
	@Override
	public UserDTO findByUserName(String userName) {
	    User user = userRepository.findByUserName(userName)
	            .orElseThrow(() -> new UserValidationException("User not found with username: " + userName));
	    return UserMapper.toDTO(user);
	}

	@Override
	public UserDTO findByEmail(String email) {
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserValidationException("User not found with email: " + email));
	    return UserMapper.toDTO(user);
	}


}
