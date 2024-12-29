package com.onlineBookStore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String UserName;
	private String firstName;
	private String lastName;
	private String email;
	@JsonIgnore
	private String password;
	private String role;

}
