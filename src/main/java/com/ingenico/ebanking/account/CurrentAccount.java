package com.ingenico.ebanking.account;

/**
 * 
 * @author caydogdu
 *
 * This is a class for user current(checking) account
 * 	
 */
public class CurrentAccount extends AbstractAccount {

	public CurrentAccount(double balance, String name) {
		super(balance, name);
	}

	@Override
	public synchronized boolean deposit(double amount) {
		balance += amount;
		return true;
	}

	@Override
	public synchronized boolean withdraw(double amount) {
		// to prevent overdraw
		if(balance >= amount){
			balance -= amount;
			return true;
		}
		return false;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public String getName() {
		return name;
	}

}
