package com.onlineBookStore.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String userName;
	private String password;
	private String email;
}
