package com.ingenico.ebanking.model;

/**
 * 
 * @author caydogdu
 *
 * This is model for rest service
 */
public class AccountModel {

	private double balance;
	
	private String name;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
