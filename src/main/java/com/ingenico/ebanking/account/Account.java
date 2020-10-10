package com.ingenico.ebanking.account;

/**
 * 
 * @author caydogdu
 *
 * This is a interface for accounts
 */
public interface Account {

	public boolean deposit(double amount); 

    public boolean withdraw(double amount);
	
	public double getBalance();

    public String getName();
	
}
