package com.messapp.messapp.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.messapp.messapp.dto.AdminRegistrationDTO;
import com.messapp.messapp.dto.PersonRegistrationDTO;
import com.messapp.messapp.entities.AdminEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.enums.Role;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.exception.UserAlreadyExistsException;
import com.messapp.messapp.jpaRepositories.UserRepository;
import com.messapp.messapp.middleware.JWTService;
import com.messapp.messapp.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private PersonServiceImpl personService;
	
	@Override
	@Transactional
	public void saveUserAsPerson(PersonRegistrationDTO person) throws UserAlreadyExistsException {
		try {
			UserEntity user = new UserEntity();
			user.setEmail(person.getEmail());
			user.setPassword(person.getPassword());
			user.setRole(Role.PERSON);
			PersonEntity personDB = new PersonEntity();
			personDB.setUser(user);
			personDB.setMessType(person.getMesstype());
			personDB.setName(person.getName());
			user.setPerson(personDB);
			user.setPassword(encoder.encode(user.getPassword()));
			UserEntity userFromDB = userRepository.save(user);
			System.out.println("USER SAVED");
			
		}catch(DataIntegrityViolationException e)
		{
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	@Transactional
	public void saveUserAsAdmin(AdminRegistrationDTO admin) throws UserAlreadyExistsException {
		try {
			UserEntity user = new UserEntity();
			user.setEmail(admin.getEmail());
			user.setPassword(admin.getPassword());
			user.setRole(Role.ADMIN);
			user.setPassword(encoder.encode(user.getPassword()));
			AdminEntity adminDB = new AdminEntity();
			adminDB.setUser(user);
			adminDB.setName(admin.getName());
			user.setAdmin(adminDB);
			System.out.println("tried to save the user");
			UserEntity userFromDB = userRepository.save(user);
			System.out.println("USER SAVED");
			
		}catch(DataIntegrityViolationException e)
		{
			System.out.println("reached the user already exists exception throwing part");
			throw new UserAlreadyExistsException();
		}
		
	}
	
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserEntity getUserFromEmail(String email) {
		// TODO Auto-generated method stub
		try {
			return userRepository.getUserFromEmail(email);
		}
		catch(Exception e)
		{
			System.out.println("its here");
			System.out.println(e);
			throw e;
		}
	}
	
	/**
	 * used for the verification of User in login and register
	 * @throws ApiException 
	 * */
	public String verify(UserEntity user)  {
		//uses the authentication provider to authenticate 
		//authentication provider uses the DAO authentication provider
       
        	try {
        		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
                if (authentication.isAuthenticated()) {
                    return jwtService.generateToken(user.getEmail());
                }
                System.out.println("REACEHDD THE FAILED ONE ");
                return "fail";
        	}catch(Exception e)
        	{
        		System.out.println("exception in the authentication manager thing");
        		System.out.println(e);
        		throw e;
        	}
        
    }





	
	
	
	

	
	
}
