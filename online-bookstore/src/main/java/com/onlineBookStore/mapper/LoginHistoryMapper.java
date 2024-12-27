package com.onlineBookStore.mapper;

import com.onlineBookStore.Entity.LoginHistory;
import com.onlineBookStore.Entity.User;
import com.onlineBookStore.dto.LoginHistoryDTO;
import com.onlineBookStore.exceptions.UserValidationException;
import com.onlineBookStore.repository.UserRepository;

public class LoginHistoryMapper {

	// Convert LoginHistory entity to LoginHistoryDTO
	public static LoginHistoryDTO toDTO(LoginHistory loginHistory) {
		LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO();
		loginHistoryDTO.setId(loginHistory.getId());
		loginHistoryDTO.setUserId(loginHistory.getUser().getId());
		loginHistoryDTO.setTimestamp(loginHistory.getTimestamp());
		loginHistoryDTO.setAction(loginHistory.getAction());

		return loginHistoryDTO;

	}

	public static LoginHistory toEntity(LoginHistoryDTO loginHistoryDTO, UserRepository userRepository) {

		User user = userRepository.findById(loginHistoryDTO.getUserId())
				.orElseThrow(() -> new UserValidationException("User not found"));
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setId(loginHistoryDTO.getId());
		loginHistory.setUser(user);
		loginHistory.setTimestamp(loginHistoryDTO.getTimestamp());
		loginHistory.setAction(loginHistoryDTO.getAction());

		return loginHistory;

	}

}
