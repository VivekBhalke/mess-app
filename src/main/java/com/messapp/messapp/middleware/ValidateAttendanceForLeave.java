package com.messapp.messapp.middleware;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.serviceImplementation.AttendanceServiceImpl;

@Component
public class ValidateAttendanceForLeave {
	
	@Autowired
	private AttendanceServiceImpl attendanceService;
	
	public void validateAttendance(PersonEntity person , Date date, LeaveType leavetype) throws ApiException
	{
		List<AttendanceEntity> attendance = attendanceService.findByPersonAndDate(person, date);
		if(attendance.size() > 0)
		{
			for(var attendanceEntity : attendance)
			{
				if((attendanceEntity.getMealType() == MealType.MORNING ) && (leavetype == LeaveType.MORNING))
				{
					ApiResponse<String> response = new ApiResponse();
					response.setData(null);
					response.setMessage("YOU HAVE MARKED ATTENDED");
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					ApiException exception = new ApiException();
					exception.setResponse(response);
					throw exception;
				}
				if((attendanceEntity.getMealType() == MealType.EVENING ) && (leavetype == LeaveType.EVENING))
				{
					ApiResponse<String> response = new ApiResponse();
					response.setData(null);
					response.setMessage("YOU HAVE MARKED ATTENDED");
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					ApiException exception = new ApiException();
					exception.setResponse(response);
					throw exception;
				}
			}
		}
	}
}
