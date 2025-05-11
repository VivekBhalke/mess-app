package com.messapp.messapp.exception;

import org.springframework.http.HttpStatus;


public class ApiResponse<V>{
	private V data;
	private String message;
	private HttpStatus statusCode;
	public V getData() {
		return data;
	}
	public void setData(V data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public ApiResponse()
	{
		
	}
	
}
