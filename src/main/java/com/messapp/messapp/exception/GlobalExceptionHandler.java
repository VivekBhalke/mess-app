package com.messapp.messapp.exception;

import java.text.ParseException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolation(DataIntegrityViolationException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("EMAIL ALREADY EXISTS!");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE HANDLEDATAINTEGRITY EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse<String>> handleApiException(ApiException e) {
	    
	    System.out.println("REACHED THE ApiException");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(e.getResponse());
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<String>> handleUserAlreadyExists(UserAlreadyExistsException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("EMAIL ALREADY EXISTS!");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE HANDLEDATAINTEGRITY EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> userNotFound(UsernameNotFoundException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("USER NOT FOUND!");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE USERNOT FOUNDEXCEPTION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	
	@ExceptionHandler(AttendanceAlreadyMarked.class)
	public ResponseEntity<ApiResponse<String>> attendanceAlreadyMarked(AttendanceAlreadyMarked e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("Attendance Already Marked");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE AttendanceAlreadyMarked");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<ApiResponse<String>> attendanceAlreadyMarked(ParseException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("DATE UNABLE TO PARSE SHOULD BE IN YYYY-MM-DD");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE parse exception");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(IncorrectMealTypeException.class)
	public ResponseEntity<ApiResponse<String>> incorrectMealType(IncorrectMealTypeException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("INCORRECT MEAL TYPE PROVIDED");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE IncorrectMealTypeException");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(MorningLeaveTimeGone.class)
	public ResponseEntity<ApiResponse<String>> incorrectMealType(MorningLeaveTimeGone e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("MORNING LEAVE TIME GONE");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE MorningLeaveTimeGone");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(LeaveForThisDayExists.class)
	public ResponseEntity<ApiResponse<String>> incorrectMealType(LeaveForThisDayExists e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("LEAVE ALREADY EXITS");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE LeaveForThisDayExists");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(LeaveDateInPastException.class)
	public ResponseEntity<ApiResponse<String>> incorrectMealType(LeaveDateInPastException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("LEAVE DATE IN PAST");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE LeaveDateInPastException");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(EveningLeaveTimeGone.class)
	public ResponseEntity<ApiResponse<String>> incorrectMealType(EveningLeaveTimeGone e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    
	    response.setMessage("Evening LEAVE TIME GONE");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE EveningLeaveTimeGone");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	
	//BadCredentialsException
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse<String>> handleBadCredentialsException(BadCredentialsException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("BAD CREDENTIALS");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE BAD CREDENTIALS EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	//HttpMessageNotReadableException
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<String>> notCompatibleInput(HttpMessageNotReadableException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("BODY INPUT NOT COMPATIBLE");
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE HttpMessageNotReadableException EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(IncorrectPasswordFormatException.class)
	public ResponseEntity<ApiResponse<String>> incorrectPasswordFormat(IncorrectPasswordFormatException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage(e.getMessage());
	    response.setData(null); // or you can set some data if needed
	    System.out.println("REACHED THE IncorrectPasswordFormatException EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
	
	@ExceptionHandler(IncorrectEmailFormatException.class)
	public ResponseEntity<ApiResponse<String>> incorrectEmailFormat(IncorrectEmailFormatException e) {
	    ApiResponse<String> response = new ApiResponse<>();
	    response.setMessage("INCORRECT EMAIL FORMAT");
	    response.setData(null); 
	    System.out.println("REACHED THE IncorrectEmailFormatException EXCPETION");
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	                         .body(response);
	}
}
