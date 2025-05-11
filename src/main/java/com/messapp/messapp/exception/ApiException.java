package com.messapp.messapp.exception;

public class ApiException extends Exception{
	
	private ApiResponse response;

	public ApiResponse getResponse() {
		return response;
	}

	public void setResponse(ApiResponse response) {
		this.response = response;
	}
	
}
