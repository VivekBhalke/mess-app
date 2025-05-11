package com.messapp.messapp.dto;

import com.messapp.messapp.enums.MessType;

public class PersonRegistrationDTO {
	
	
	private String email;
	private String password;
	private MessType messtype;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MessType getMesstype() {
		return messtype;
	}
	public void setMesstype(MessType messtype) {
		this.messtype = messtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
