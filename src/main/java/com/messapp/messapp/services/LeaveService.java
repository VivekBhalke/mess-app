package com.messapp.messapp.services;

import java.sql.Date;
import java.util.List;

import com.messapp.messapp.dto.LeaveInputWithSqlDates;
import com.messapp.messapp.entities.LeavesEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.exception.ApiException;

public interface LeaveService {
	/**
	 * Description : 
	 * gets the leave for the person on the given date
	 * there can be at most 2 leave entity cause for one day we can have leave for morning and evening
	 * @param person : PersonEntity
	 * @param date : java sql date object
	 * @return List of LeavesEntity
	 */
	public List<LeavesEntity> getLeaveByDate(PersonEntity person , Date date);
	
	public void putLeaveForOneDay(PersonEntity person , Date date , LeaveType leavetype , String reason) throws ApiException;
	
	
	public void putLeaveForMoreThanOneDay(PersonEntity person,  List<LeaveInputWithSqlDates> leaveInputs)  throws ApiException;
}
