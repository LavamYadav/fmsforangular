package com.fms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author gauti
 *
 * @param <T>
 * 
 */
@JsonInclude(Include.NON_NULL)
public class ResponseDTO<T> {

	private boolean status;
	private String message;
	private T data;

	public ResponseDTO() {
		super();
	}
	
	public ResponseDTO(boolean status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ResponseDTO(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
