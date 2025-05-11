package com.messapp.messapp.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.messapp.messapp.enums.LeaveStatus;
import com.messapp.messapp.enums.LeaveType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(name="leaves" , 
uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "date", "leave_type"} ))
public class LeavesEntity {
	@Id
	@Column(name="leave_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonEntity person;
	
	@Column
	private Date date;
	@Enumerated(EnumType.STRING)
	@Column(name = "leave_type")
	private LeaveType leaveType;
	
	@Column(name="reason")
	private String reason;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "leave_status")
	private LeaveStatus leaveStatus;
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
