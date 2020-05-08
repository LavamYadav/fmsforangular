package com.fms.exception;

public class InvalidAirportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidAirportException() {
	}
	
	public InvalidAirportException(String message){
		super(message);
	}

}
