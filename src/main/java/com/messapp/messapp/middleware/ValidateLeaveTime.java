package com.messapp.messapp.middleware;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.messapp.messapp.entities.AttendanceTimeEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.exception.EveningLeaveTimeGone;
import com.messapp.messapp.exception.LeaveDateInPastException;
import com.messapp.messapp.exception.MorningLeaveTimeGone;
import com.messapp.messapp.jpaRepositories.AttendanceTimeRepository;

@Component
public class ValidateLeaveTime {
	
	@Autowired
	public   AttendanceTimeRepository attendanceTimeRepository;
	
	/*
	 * throws LeaveDateInPastException if the date is of the past date
	 * throws MorningLeaveTimeGone if the date is todays and the morning time is gone
	 * throws EvenningLeaveTimeGone if the date is todays and the evening time is gone
	 * */
	public  boolean validateLeaveTime(LeaveType leavetype , Date date)
	{
		LocalDate inputDate = date.toLocalDate(); // assuming `date` is java.sql.Date
		LocalDate todayDate = LocalDate.now(ZoneId.systemDefault());

		System.out.println("Input: " + inputDate + ", Today: " + todayDate);

		if (inputDate.isBefore(todayDate)) {
		    throw new LeaveDateInPastException();
		}
		if (!inputDate.equals(todayDate)) {
		    return true;
		}
		System.out.println("last line run is this before run time excpetion");
		AttendanceTimeEntity attendanceTimeEntity = attendanceTimeRepository.findSingleRecord();
		LocalTime now = LocalTime.now();             // current time, e.g., 09:45
		int currentHour = now.getHour();
		if(leavetype == LeaveType.MORNING)
		{
			if(currentHour > attendanceTimeEntity.getMorningTime())
			{
				throw new MorningLeaveTimeGone();
			}
			else {
				return true;
			}
		}
		else if(leavetype == LeaveType.EVENING)
		{
			if(currentHour > attendanceTimeEntity.getEveningTime())
			{
				throw new EveningLeaveTimeGone();
			}
			else {
				return true;
			}
		}
		return true;
	}
}
