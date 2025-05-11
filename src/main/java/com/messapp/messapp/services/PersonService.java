package com.messapp.messapp.services;

import java.sql.Date;

import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.enums.MessType;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.AttendanceAlreadyMarked;
import com.messapp.messapp.exception.IncorrectMealTypeException;
import com.messapp.messapp.exception.PersonAlreadyExistsException;

public interface PersonService {
	public void savePerson(PersonEntity person) throws PersonAlreadyExistsException;
	
	public PersonEntity getPersonById();
	
	
	public void markAttendance(PersonEntity person , MealType mealType  , Date date) throws ApiException ,  AttendanceAlreadyMarked , IncorrectMealTypeException;
	
}
