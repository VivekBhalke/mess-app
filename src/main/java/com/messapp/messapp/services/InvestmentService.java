package com.messapp.messapp.services;

import java.sql.Date;

import com.messapp.messapp.entities.InvestmentEntity;
import com.messapp.messapp.entities.PersonEntity;

public interface InvestmentService {

	public InvestmentEntity getTheLatestInvestmentEntity(PersonEntity person);
	
	public double getTotalInvestmentForAndAfter(PersonEntity person , Date date);
	
	public double getTotalInvestmentFor(PersonEntity person);
}
