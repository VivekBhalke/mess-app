package com.messapp.messapp.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="investment")
public class InvestmentEntity {
	@Id
	@Column(name="investment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long investmentId;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonEntity person;
	
	@Column
	private Date date;
	
	@Column(name="amount_of_investment")
	private Double amountOfInvestment;

	public Long getInvestmentId() {
		return investmentId;
	}

	public void setInvestmentId(Long investmentId) {
		this.investmentId = investmentId;
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

	public Double getAmountOfInvestment() {
		return amountOfInvestment;
	}

	public void setAmountOfInvestment(Double amountOfInvestment) {
		this.amountOfInvestment = amountOfInvestment;
	}
	
	
	
}
