package com.ned.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(InvalidTypeException.class)
	public ResponseEntity<?> invalidAccountType(InvalidTypeException invalidTypeException) {
		String errorMessage = invalidTypeException.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<?> insufficientBalanceException(InsufficientBalanceException insufficientBalanceException) {
		String errorMessage = insufficientBalanceException.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> InvalidIdException(InvalidIdException invalidIdException) {
		String errorMessage = invalidIdException.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		return new ResponseEntity<>(errorResponse, null, HttpStatus.FORBIDDEN);

	}
	
	
}
