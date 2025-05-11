package com.messapp.messapp.controllers;

import java.sql.Date;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messapp.messapp.dto.AttendanceMarkForOneDay;
import com.messapp.messapp.dto.AttendanceShowUserDTO;
import com.messapp.messapp.dto.LeaveInputDTO;
import com.messapp.messapp.dto.LeaveInputWithSqlDates;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.exception.IncorrectMealTypeException;
import com.messapp.messapp.serviceImplementation.AttendanceServiceImpl;
import com.messapp.messapp.serviceImplementation.LeaveServiceImpl;
import com.messapp.messapp.serviceImplementation.PersonServiceImpl;
import com.messapp.messapp.serviceImplementation.UserServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PersonServiceImpl personService;
	
	@Autowired
	private LeaveServiceImpl leaveService;
	
	
	@Autowired
	private AttendanceServiceImpl attendanceService;
	
	@PostMapping(value="/markOneDayAttendance")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<String>> markAttendance(@RequestBody AttendanceMarkForOneDay attendance) throws RuntimeException, ParseException, ApiException,IncorrectMealTypeException{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    Date date = attendance.getSqlDate();
		    personService.markAttendance(person, attendance.getMealType(), date);
			ApiResponse<String> response = new ApiResponse<>();
			response.setMessage("ATTENDANCE ADDED");
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	
	@PostMapping(value="/markLeaveForOneDay")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<String>> markLeaveForOneDay(@RequestBody LeaveInputDTO leaveInput) throws RuntimeException, ParseException, ApiException,IncorrectMealTypeException{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    Date date = leaveInput.getSqlDate();
		    leaveService.putLeaveForOneDay(person, date, leaveInput.getLeaveType(), leaveInput.getReason());
			ApiResponse<String> response = new ApiResponse<>();
			response.setMessage("LEAVE ADDED");
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	@PostMapping("/markLeaveForMoreThanOneDay")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<String>> markLeaveForMoreThanOneDay(@RequestBody List<LeaveInputDTO> leaveInputs) throws ParseException, ApiException
	{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    List<LeaveInputWithSqlDates> leaveInputsSql = new ArrayList<>();
		    System.out.println("reached the mark more than one leave");
		    for(var leaveInput : leaveInputs)
		    {
		    	var leaveInputWithSql = new LeaveInputWithSqlDates();
		    	leaveInputWithSql.setLeaveType(leaveInput.getLeaveType());
		    	leaveInputWithSql.setReason(leaveInput.getReason());
		    	leaveInputWithSql.setDate(leaveInput.getSqlDate());
		    	leaveInputsSql.add(leaveInputWithSql);
		    }
		    leaveService.putLeaveForMoreThanOneDay(person, leaveInputsSql);
			ApiResponse<String> response = new ApiResponse<>();
			response.setMessage("LEAVES ADDED");
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	
	
	
	@GetMapping("/getAllAttendance")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<List<AttendanceShowUserDTO>>> getAllAttendance() throws ApiException
	{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    List<AttendanceShowUserDTO> output = attendanceService.getAllAttendane(person);
			ApiResponse<List<AttendanceShowUserDTO>> response = new ApiResponse<>();
			response.setMessage("ATTENDANCE GIVEN");
			response.setData(output);
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	
	@GetMapping("/getAttendanceForAMonth")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<List<AttendanceShowUserDTO>>> getAttendanceForAMonth(@RequestParam long year, @RequestParam int month) throws ApiException
	{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    List<AttendanceShowUserDTO> output = attendanceService.getAttendanceForOneMonth(person , month , year);
			ApiResponse<List<AttendanceShowUserDTO>> response = new ApiResponse<>();
			response.setMessage("ATTENDANCE GIVEN");
			response.setData(output);
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	
	@GetMapping("/getAttendanceForAYear")
	@PreAuthorize("hasRole('PERSON')")
	public ResponseEntity<ApiResponse<List<AttendanceShowUserDTO>>> getAttendanceForAYear(@RequestParam long year) throws ApiException
	{
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    PersonEntity person = userService.getUserFromEmail(username).getPerson();
		    List<AttendanceShowUserDTO> output = attendanceService.getAttendanceForOneYear(person  , year);
			ApiResponse<List<AttendanceShowUserDTO>> response = new ApiResponse<>();
			response.setMessage("ATTENDANCE GIVEN");
			response.setData(output);
			response.setStatusCode(HttpStatus.ACCEPTED);
			return ResponseEntity.ok(response);
		}catch(RuntimeException e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	
	
	
	
	
	
}
	
	
	
