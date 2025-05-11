package com.messapp.messapp.entities;

import java.util.List;

import com.messapp.messapp.enums.MessType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="person", 
			uniqueConstraints = @UniqueConstraint(columnNames= {"person_id" ,"mess_type"})
)
public class PersonEntity {
	@Id
	@Column(name="person_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	@Column(unique=true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "mess_type")
	private MessType messType;
	
	@OneToMany(mappedBy= "person" , fetch=FetchType.LAZY)
	private List<LeavesEntity> leaves;
	
	
	@OneToMany(mappedBy= "person" , fetch=FetchType.LAZY)
	private List<AttendanceEntity> personAttendance;
	
	@OneToMany(mappedBy= "person" , fetch=FetchType.LAZY)
	private List<InvestmentEntity> investments;

	@OneToOne(mappedBy="person" , fetch=FetchType.LAZY)
	private BillingEntity billing;
	
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttendanceEntity> getPersonAttendance() {
		return personAttendance;
	}

	public void setPersonAttendance(List<AttendanceEntity> personAttendance) {
		this.personAttendance = personAttendance;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public MessType getMessType() {
		return messType;
	}

	public void setMessType(MessType messType) {
		this.messType = messType;
	}

	public List<LeavesEntity> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeavesEntity> leaves) {
		this.leaves = leaves;
	}

	public List<InvestmentEntity> getInvestments() {
		return investments;
	}

	public void setInvestments(List<InvestmentEntity> investments) {
		this.investments = investments;
	}

	public BillingEntity getBilling() {
		return billing;
	}

	public void setBilling(BillingEntity billing) {
		this.billing = billing;
	}
	
	
}
