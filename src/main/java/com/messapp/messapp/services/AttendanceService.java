package com.messapp.messapp.services;

import java.sql.Date;
import java.util.List;

import com.messapp.messapp.dto.AttendanceShowUserDTO;
import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.PersonEntity;

public interface AttendanceService {
	public void save(AttendanceEntity attendance);
	
	public List<AttendanceEntity> findByPersonAndDate(PersonEntity person , Date date);
	
	
	public List<AttendanceShowUserDTO> getAllAttendane(PersonEntity person);
	
	public List<AttendanceShowUserDTO> getAttendanceForOneMonth(PersonEntity person , int month , long year);
	
	
	public List<AttendanceShowUserDTO> getAttendanceForOneYear(PersonEntity person , long year);
	
	public List<AttendanceShowUserDTO> getAttendanceForOneDay(PersonEntity person , Date date);
	
}
