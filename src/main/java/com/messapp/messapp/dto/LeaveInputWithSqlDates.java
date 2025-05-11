package com.messapp.messapp.dto;

import com.messapp.messapp.enums.LeaveType;

public class LeaveInputWithSqlDates {
	
		private LeaveType leaveType;
		
		private java.sql.Date date;
		
		private String reason;

		public LeaveType getLeaveType() {
			return leaveType;
		}

		public void setLeaveType(LeaveType leaveType) {
			this.leaveType = leaveType;
		}

		public java.sql.Date getDate() {
			return date;
		}

		public void setDate(java.sql.Date date) {
			this.date = date;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
		
		
		
		
	
}
