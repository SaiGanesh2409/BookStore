package com.onlineBookStore.exceptions;

public class BookValidationException extends RuntimeException {

	private final String message;

	public String getMessage() {
		return message;
	}

	public BookValidationException(String message) {
		super();
		this.message = message;
	}

}
