package com.messapp.messapp.exception;

public class InsufficientFundsException extends RuntimeException{
	private Double amountToBePaid;

	public InsufficientFundsException(Double amountToBePaid) {
		super();
		this.amountToBePaid = amountToBePaid;
	}
	
}
