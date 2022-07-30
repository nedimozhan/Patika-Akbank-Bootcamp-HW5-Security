package com.ned.banking.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

	private int id;
	private String username;
	private String password;
	private String authorities;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public List<String> getPermissionList() {
		if (this.authorities.length() > 0) {
			return Arrays.asList(this.authorities.split(","));
		}
		return new ArrayList<>();
	}
}
