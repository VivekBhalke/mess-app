package com.messapp.messapp.middleware;

import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.exception.IncorrectEmailFormatException;
import com.messapp.messapp.exception.IncorrectPasswordFormatException;

public class EmailAndPasswordValidator {

	private static EmailValidator validator = null;
	public static boolean validate(String email)
	{
		if(validator == null)
		{
			validator = EmailValidator.getInstance();
		}
		return validator.isValid(email);
	}
	private static RegexValidator 	passwordValidator = null;
	public static boolean isValidPassword(String password)
	{
		if(passwordValidator ==null)
		{
			passwordValidator = new RegexValidator("^(?=.*\\d)(?=.*[\\W_].*[\\W_].*[\\W_]).{10,}$");
		}
		
		return passwordValidator.isValid(password);
	}
	
	public static void validateEverything(String email , String password) throws Exception
	{
		if (!EmailAndPasswordValidator.validate(email)) {
            throw new IncorrectEmailFormatException();
        }

        if (!EmailAndPasswordValidator.isValidPassword(password)) {
        	throw new IncorrectPasswordFormatException();
        }
	}
}