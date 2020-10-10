package com.ingenico.ebanking.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ingenico.ebanking.exception.ResponseError;

/**
 * 
 * @author caydogdu
 *
 * This is a coomon response class for rest response
 * @param <T>
 */
@JsonInclude(value = Include.NON_NULL)
public class Response<T> {

	private boolean success;
	
	private T result;
	
	private ResponseError error;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}
	
}