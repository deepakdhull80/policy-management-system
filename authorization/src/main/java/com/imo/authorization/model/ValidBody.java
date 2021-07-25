package com.imo.authorization.model;

public class ValidBody {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ValidBody(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return "ValidBody [token=" + token + "]";
	}
	
	
	
}
