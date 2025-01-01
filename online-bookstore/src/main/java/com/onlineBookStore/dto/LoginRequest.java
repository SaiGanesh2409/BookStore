package com.onlineBookStore.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String userNameOrEmail;
	private String password;
}
