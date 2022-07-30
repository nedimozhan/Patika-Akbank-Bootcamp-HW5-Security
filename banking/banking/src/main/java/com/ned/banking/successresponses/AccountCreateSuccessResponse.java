package com.ned.banking.successresponses;

public class AccountCreateSuccessResponse {
	private String message;
	private String accountNumber;

	public AccountCreateSuccessResponse() {
		this.message = "Account Created";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
