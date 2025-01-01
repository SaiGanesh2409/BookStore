package com.onlineBookStore.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LoginHistoryDTO {
	private Long id;
	private Long userId;
	private String action;
	private String role;
	private Date timestamp;
}
