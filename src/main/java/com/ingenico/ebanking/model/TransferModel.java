package com.ingenico.ebanking.model;

/**
 * 
 * @author caydogdu
 *
 * This is a model for rest service
 */
public class TransferModel {

	private String nameFrom;
	
	private String nameTo;
	
	private double amount;

	public String getNameFrom() {
		return nameFrom;
	}

	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}

	public String getNameTo() {
		return nameTo;
	}

	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
