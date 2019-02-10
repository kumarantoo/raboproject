package com.rabo.customer.batch.utils;

public class CustomerException extends Exception {

	int erroCode;
	String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustomerException(int errorCode,String message){
		this.erroCode=errorCode;
		this.message=message;
	}
}
