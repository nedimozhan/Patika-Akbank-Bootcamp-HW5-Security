package com.ned.banking.model;

import java.sql.Timestamp;

public class Account {

	private String name;
	private String surname;
	private String email;
	private String tc;
	private String type;
	private String accountNumber;
	private float accountBalance;
	private Timestamp accountDateOfUpdate;

	private boolean isDeleted;
	private int id;
	private int userId;

	public Account(AccountCreateRequest accountCreateRequest, String accountNumber, float accountBalance,
			Timestamp accountDateOfUpdate, boolean isDeleted) {
		this.name = accountCreateRequest.getName();
		this.surname = accountCreateRequest.getSurname();
		this.email = accountCreateRequest.getEmail();
		this.tc = accountCreateRequest.getTc();
		this.type = accountCreateRequest.getType();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountDateOfUpdate = accountDateOfUpdate;
		this.isDeleted = isDeleted;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Account() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Timestamp getAccountDateOfUpdate() {
		return accountDateOfUpdate;
	}

	public void setAccountDateOfUpdate(Timestamp accountDateOfUpdate) {
		this.accountDateOfUpdate = accountDateOfUpdate;
	}
}