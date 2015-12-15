package com.yyang.project.infrastructure.exceptions.application;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

public class DataException extends ApplicationException {

	private static final long serialVersionUID = -7114722433857047891L;
	
	private static final String hintMessage = "Data exception :  %s.";
	
	private final String errorMessage;
	
	public DataException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return String.format(hintMessage, errorMessage );
	}

}
