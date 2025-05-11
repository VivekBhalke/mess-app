package com.messapp.messapp.services;

import com.messapp.messapp.entities.BillingEntity;
import com.messapp.messapp.entities.PersonEntity;

public interface BillingService {
	
	public BillingEntity getTheLatestBillingEntity(PersonEntity person);
	
	public void makeBill(PersonEntity person);
	
}
