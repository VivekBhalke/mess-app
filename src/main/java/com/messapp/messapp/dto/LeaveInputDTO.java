package com.messapp.messapp.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.messapp.messapp.enums.LeaveType;








public class LeaveInputDTO {

	private LeaveType leaveType;
	
	private String date;
	
	private String reason;
	
	public java.sql.Date getSqlDate() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(date); 
        return new java.sql.Date(utilDate.getTime());
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
