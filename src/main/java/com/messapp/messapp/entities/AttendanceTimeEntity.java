package com.messapp.messapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="attendance_time")
public class AttendanceTimeEntity {
	
	@Id
	@Column(name="attendance_time_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceTimeId;
	
	@Column(name="morning_time")
	private Integer morningTime;
	
	@Column(name="evening_time")
	private Integer eveningTime;

	public Long getAttendanceTimeId() {
		return attendanceTimeId;
	}

	public void setAttendanceTimeId(Long attendanceTimeId) {
		this.attendanceTimeId = attendanceTimeId;
	}

	public Integer getMorningTime() {
		return morningTime;
	}

	public void setMorningTime(Integer morningTime) {
		this.morningTime = morningTime;
	}

	public Integer getEveningTime() {
		return eveningTime;
	}

	public void setEveningTime(Integer eveningTime) {
		this.eveningTime = eveningTime;
	}
	
	
	
}
