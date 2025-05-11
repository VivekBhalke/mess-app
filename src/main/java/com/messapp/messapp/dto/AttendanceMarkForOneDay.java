package com.messapp.messapp.dto;



import java.util.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.messapp.messapp.enums.MealType;

public class AttendanceMarkForOneDay {
	MealType mealType;
	String date;
	
	
	public Date getSqlDate() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(date); 
        return new java.sql.Date(utilDate.getTime());
	}


	public MealType getMealType() {
		return mealType;
	}


	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
