package com.onlineBookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserValidationException.class)
	public ResponseEntity<?> handleUserValidationException(UserValidationException userValidationException) {

		return new ResponseEntity<>(userValidationException.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
