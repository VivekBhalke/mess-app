package com.messapp.messapp.services;

import com.messapp.messapp.dto.AdminRegistrationDTO;
import com.messapp.messapp.dto.PersonRegistrationDTO;
import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.exception.UserAlreadyExistsException;

public interface UserService {
	
	public void saveUserAsPerson(PersonRegistrationDTO person) throws UserAlreadyExistsException;
	
	public void saveUserAsAdmin(AdminRegistrationDTO admin) throws UserAlreadyExistsException;
	
	public UserEntity getUserFromEmail(String email);
	

	
}
