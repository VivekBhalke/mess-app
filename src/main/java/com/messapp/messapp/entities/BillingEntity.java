package com.messapp.messapp.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="billing")
public class BillingEntity {
	@Id
	@Column(name="billing_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billingId;
	
	@OneToOne
	@JoinColumn(name="person_id")
	private PersonEntity person;
	
	@Column
	private Date date;
	
	@Column(name="amount_with_admin")
	private Double amountWithAdmin;

	public Long getBillingId() {
		return billingId;
	}

	public void setBillingId(Long billingId) {
		this.billingId = billingId;
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

	public Double getAmountWithAdmin() {
		return amountWithAdmin;
	}

	public void setAmountWithAdmin(Double amountWithAdmin) {
		this.amountWithAdmin = amountWithAdmin;
	}
	
	
}
