package com.imo.authorization.model;

import org.springframework.stereotype.Component;

@Component
public class RequestUserBody {

	private String username;
	private String password;

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

	public RequestUserBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestUserBody(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
