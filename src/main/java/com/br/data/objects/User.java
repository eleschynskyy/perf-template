package com.br.data.objects;

public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}