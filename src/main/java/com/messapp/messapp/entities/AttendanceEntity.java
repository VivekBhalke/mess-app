package com.messapp.messapp.entities;

import java.sql.Date;
import com.messapp.messapp.enums.MealType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Attendance entity  : 
 * it will have the attendance for each of the person for each date 
 * if he was present.
 */
@Entity
@Table(name="attendance_entity" , 
uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "date", "meal_type"} ))
public class AttendanceEntity {
	@Id
	@Column(name="attendance_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "meal_type")
	private MealType mealType;
	
	@Column
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonEntity person;

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}
	
	
}
