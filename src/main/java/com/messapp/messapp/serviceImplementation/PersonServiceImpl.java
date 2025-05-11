package com.messapp.messapp.serviceImplementation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.LeavesEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.enums.MessType;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.exception.AttendanceAlreadyMarked;
import com.messapp.messapp.exception.IncorrectMealTypeException;
import com.messapp.messapp.exception.PersonAlreadyExistsException;
import com.messapp.messapp.jpaRepositories.PersonRepository;
import com.messapp.messapp.middleware.ValidateLeaveMealType;
import com.messapp.messapp.middleware.ValidateMealTypes;
import com.messapp.messapp.services.PersonService;

import jakarta.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AttendanceServiceImpl attendanceService;
	
	@Autowired
	private ValidateLeaveMealType validateLeaveMealType;
	
	
	
	@Override
	public void savePerson(PersonEntity person) throws PersonAlreadyExistsException {
		try {
			personRepository.save(person);
		}catch(DataIntegrityViolationException e)
		{
			throw new PersonAlreadyExistsException();
		}
	}

	@Override
	public PersonEntity getPersonById() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void markAttendance(PersonEntity person, MealType mealType, Date date)  throws RuntimeException, ApiException , AttendanceAlreadyMarked, IncorrectMealTypeException
	{
		try 
		{
			ValidateMealTypes.validateMeal(person, mealType);
			validateLeaveMealType.validateLeaveMealType(person, date, mealType);
			AttendanceEntity attendance = new AttendanceEntity();
			attendance.setPerson(person);
			attendance.setDate(date);
			attendance.setMealType(mealType);
			attendanceService.save(attendance);
		}catch(DataIntegrityViolationException e)
		{
			System.out.println("exception caused");
			System.out.println(e);
			throw new AttendanceAlreadyMarked();
		}
		catch(IncorrectMealTypeException e)
		{
			System.out.println("exception caused");
			System.out.println(e);
			throw new IncorrectMealTypeException();
		}
		catch(RuntimeException e)
		{
			System.out.println("exception caused");
			System.out.println(e);
			throw e;
		}
	}

}
