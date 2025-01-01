package com.onlineBookStore.service;

import com.onlineBookStore.dto.LoginHistoryDTO;

public interface LoginHistoryService {

	void recordLogin(LoginHistoryDTO loginHistoryDTO);

	void recordLogout(LoginHistoryDTO loginHistoryDTO);
}
