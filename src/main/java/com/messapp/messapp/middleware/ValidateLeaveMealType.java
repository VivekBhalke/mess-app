package com.messapp.messapp.middleware;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.messapp.messapp.entities.LeavesEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.serviceImplementation.LeaveServiceImpl;

@Component
public class ValidateLeaveMealType {
	
	
	@Autowired
	private LeaveServiceImpl leaveService;
	
	
	public void validateLeaveMealType(PersonEntity person , Date date , MealType mealType) throws ApiException
	{
		List<LeavesEntity> leave = leaveService.getLeaveByDate(person, date);
		if(leave.size() > 0)
		{
			for(var leaveOfThatDay : leave)
			{
				if((leaveOfThatDay.getLeaveType() == LeaveType.EVENING) && (mealType == MealType.EVENING))
				{
					ApiResponse<String> response = new ApiResponse();
					response.setData(null);
					response.setMessage("YOU HAVE A LEAVE TODAY");
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					ApiException exception = new ApiException();
					exception.setResponse(response);
					throw exception;
				}
				else if((leaveOfThatDay.getLeaveType() == LeaveType.MORNING) && (mealType == MealType.MORNING))
				{
					ApiResponse<String> response = new ApiResponse();
					response.setData(null);
					response.setMessage("YOU HAVE A LEAVE TODAY");
					response.setStatusCode(HttpStatus.BAD_REQUEST);
					ApiException exception = new ApiException();
					exception.setResponse(response);
					throw exception;
				}
			}
			
		}
	}
}
