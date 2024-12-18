package com.onlineBookStore.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserValidationException.class)
	public ResponseEntity<?> handleUserValidationException(UserValidationException userValidationException) {

		return new ResponseEntity<>(List.of(userValidationException.getMessage()), HttpStatus.BAD_GATEWAY);

	}

}
