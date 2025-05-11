package com.messapp.messapp.dto;

import java.sql.Date;

import com.messapp.messapp.enums.MealType;

public class AttendanceShowUserDTO {
	
	private String date;
	private MealType mealtype;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MealType getMealtype() {
		return mealtype;
	}
	public void setMealtype(MealType mealtype) {
		this.mealtype = mealtype;
	}
	
	
	public void converSqlDateToString(Date date)
	{
		 setDate( date.toLocalDate().toString()); 
	}
	
}
