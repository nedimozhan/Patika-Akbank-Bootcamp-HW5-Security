package com.ned.banking.exception;

public class InvalidIdException extends RuntimeException{
	public InvalidIdException(String message) {
		super(message);
	}
}
