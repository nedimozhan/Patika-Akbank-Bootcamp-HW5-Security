package com.ned.banking.successresponses;

public class AccountDeleteSuccessResponse {

	private String message;

	public AccountDeleteSuccessResponse() {
		this.message = "Deleted Successfully";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
