package com.springboot.quickstart.dataTransfer;

public class LoginResponse {

	private String firstName;
	private String lastName;
	private String authToken;

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginResponse(String firstName, String lastName, String authToken) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.authToken = authToken;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public String toString() {
		return "LoginResponse [firstName=" + firstName + ", lastName=" + lastName + ", authToken=" + authToken + "]";
	}

}
