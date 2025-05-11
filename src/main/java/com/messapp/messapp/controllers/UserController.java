package com.messapp.messapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messapp.messapp.dto.AdminRegistrationDTO;
import com.messapp.messapp.dto.PersonRegistrationDTO;
import com.messapp.messapp.entities.AdminEntity;
import com.messapp.messapp.entities.UserEntity;
import com.messapp.messapp.enums.Role;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.exception.IncorrectEmailFormatException;
import com.messapp.messapp.exception.IncorrectPasswordFormatException;
import com.messapp.messapp.jpaRepositories.UserRepository;
import com.messapp.messapp.middleware.EmailAndPasswordValidator;
import com.messapp.messapp.middleware.JWTService;
import com.messapp.messapp.serviceImplementation.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
//	@PostMapping("/register")
//	public ResponseEntity<ApiResponse<String>> registerAdmin(@RequestBody UserEntity user)  throws Exception
//	{
//		EmailAndPasswordValidator.validateEverything(user.getEmail() , user.getPassword());
//		AdminEntity admin = new AdminEntity();
//		admin.setName("MADHURA");
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setAdmin(admin);
//		admin.setUser(user);
//		userRepository.save(user);
//		String token = jwtService.generateToken(user.getEmail());
//		ApiResponse<String> response = new ApiResponse<>();
//	    response.setMessage("USER SAVED");
//	    response.setData(token); // or you can set some data if needed
//	    response.setStatusCode(HttpStatus.ACCEPTED);
//	    return ResponseEntity.status(HttpStatus.ACCEPTED)
//	                         .body(response);
//	}
	
	
	@PostMapping("/register/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody AdminRegistrationDTO admin)  throws Exception
	{
		EmailAndPasswordValidator.validateEverything(admin.getEmail() , admin.getPassword());
		userServiceImpl.saveUserAsAdmin(admin);
		String token = jwtService.generateToken(admin.getEmail());
		ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("USER SAVED");
	    response.setData(token); // or you can set some data if needed
	    response.setStatusCode(HttpStatus.ACCEPTED);
	    return ResponseEntity.status(HttpStatus.ACCEPTED)
	                         .body(response);
	}
	
	
	
	@PostMapping("/register/person")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody PersonRegistrationDTO person)  throws Exception
	{
		EmailAndPasswordValidator.validateEverything(person.getEmail(), person.getPassword());
		userServiceImpl.saveUserAsPerson(person);
		String token = jwtService.generateToken(person.getEmail());
		ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("USER SAVED");
	    response.setData(token); // or you can set some data if needed
	    response.setStatusCode(HttpStatus.ACCEPTED);
	    return ResponseEntity.status(HttpStatus.ACCEPTED)
	                         .body(response);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<String>> login(@RequestBody UserEntity user) throws Exception
	{
		EmailAndPasswordValidator.validateEverything(user.getEmail() , user.getPassword());
		System.out.println("REACHED /Login");
		try {
			String token = userServiceImpl.verify(user);
			ApiResponse<String> response = new ApiResponse<>();
		    response.setMessage("USER VERIFIED");
		    response.setData(token); 
		    response.setStatusCode(HttpStatus.ACCEPTED);
		    return ResponseEntity.status(HttpStatus.ACCEPTED)
		                         .body(response);
		}catch(UsernameNotFoundException e)
		{
			System.out.println("IN THE LOGIN CONTROLLER USERNAME NOT FOUND EXCEPTION THROWN");
			System.out.println(e);
			throw e;
		}
		
	}
	
	@GetMapping("/me")
	public ResponseEntity<ApiResponse<String>> me() {
	    // Get the current authentication details
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
	    // Get the roles (authorities) associated with the authenticated user
	    String role = authentication.getAuthorities().stream()
	                                .map(authority -> authority.getAuthority())
	                                .findFirst()  // Assuming the user only has one role
	                                .orElse("ROLE_NOT_FOUND");  // Default if no role is found

	    // Prepare the response
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("Authenticated user: " + username);
	    response.setData("Role: " + role);  // Set the role in the response
	    response.setStatusCode(HttpStatus.ACCEPTED);

	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

	
}
