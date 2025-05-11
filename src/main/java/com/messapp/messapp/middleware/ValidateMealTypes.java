package com.messapp.messapp.middleware;

import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.enums.MessType;
import com.messapp.messapp.exception.IncorrectMealTypeException;

public interface ValidateMealTypes {
	public static boolean validateMeal(PersonEntity person , MealType mealtype ) throws IncorrectMealTypeException
	{
		MessType messtype = person.getMessType();
		if(messtype == MessType.BOTH) return true;
		else if(messtype == MessType.MORNING &&  mealtype == MealType.EVENING)
		{
			throw new IncorrectMealTypeException();
		}
		else if(messtype == MessType.EVENING && mealtype == MealType.MORNING)
		{
			throw new IncorrectMealTypeException();
		}
		return true;
	}
	
	public static boolean validateMeal(PersonEntity person , LeaveType leavetype) throws IncorrectMealTypeException
	{
		MessType messtype = person.getMessType();
		if(messtype == MessType.BOTH) return true;
		else if(messtype == MessType.MORNING && leavetype == LeaveType.EVENING)
		{
			throw new IncorrectMealTypeException();
		}
		else if(messtype == MessType.EVENING && leavetype == LeaveType.MORNING)
		{
			throw new IncorrectMealTypeException();
		}
		return true;
	}
}
