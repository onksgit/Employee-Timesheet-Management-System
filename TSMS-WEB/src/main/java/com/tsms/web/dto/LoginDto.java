package com.tsms.web.dto;

import com.tsms.web.session.SessionDetails;

public class LoginDto {
	private String username;
	private String password;
	private SessionDetails sessionDetails;

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

	public SessionDetails getSessionDetails() {
		return sessionDetails;
	}

	public void setSessionDetails(SessionDetails sessionDetails) {
		this.sessionDetails = sessionDetails;
	}

}
