package com.ingenico.ebanking.exception;

/**
 * 
 * @author caydogdu
 * 
 * This is a error model for rest response
 */
public class ResponseError {

	private String errorCode;
	
	private String errorDescription;
	
	private String developerMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
