package com.onlineBookStore.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String UserName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;

}
