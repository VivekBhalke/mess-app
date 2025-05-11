package com.messapp.messapp.exception;

public class IncorrectPasswordFormatException extends Exception{
	private String message;
	public IncorrectPasswordFormatException()
	{
		message = "THE PASSWORD MUST BE";
	}
	public String getMessage()
	{
		return message;
	}
}


