package com.onlineBookStore.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBookStore.Entity.LoginHistory;
import com.onlineBookStore.Entity.User;
import com.onlineBookStore.dto.LoginHistoryDTO;
import com.onlineBookStore.repository.LoginHistoryRepository;
import com.onlineBookStore.repository.UserRepository;
import com.onlineBookStore.service.LoginHistoryService;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void recordLogin(LoginHistoryDTO loginHistoryDTO) {
		User user = userRepository.findById(loginHistoryDTO.getUserId()).orElse(null);
		if (user != null) {
			LoginHistory loginHistory = new LoginHistory();
			loginHistory.setUser(user);
			loginHistory.setAction("LOGIN");
			loginHistory.setRole(loginHistoryDTO.getRole());
			loginHistory.setTimestamp(new Date());
			loginHistoryRepository.save(loginHistory);
		}
	}

	@Override
	public void recordLogout(LoginHistoryDTO loginHistoryDTO) {
		User user = userRepository.findById(loginHistoryDTO.getUserId()).orElse(null);
		if (user != null) {
			LoginHistory loginHistory = new LoginHistory();
			loginHistory.setUser(user);
			loginHistory.setAction("LOGOUT");
			loginHistory.setRole(loginHistoryDTO.getRole());
			loginHistory.setTimestamp(new Date());
			loginHistoryRepository.save(loginHistory);
		}
	}

}
