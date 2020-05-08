package com.fms.exception;

public class FlightException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;

	public String getErrMsg() {
		return errorMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errorMsg = errMsg;
	}

	public FlightException(String errMsg) {

		this.errorMsg = errMsg;
	}


}
