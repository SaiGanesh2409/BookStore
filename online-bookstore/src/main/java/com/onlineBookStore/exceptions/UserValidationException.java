package com.onlineBookStore.exceptions;

public class UserValidationException extends RuntimeException {

	private final String message;

	public UserValidationException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
