package com.yyang.project.infrastructure.exceptions.application.predicate;

import com.yyang.project.infrastructure.exceptions.application.PredicateException;


public class CreateException extends PredicateException {

	private static final long serialVersionUID = -3325018521223356237L;
	
	private String hintMessage = "There is an exception when create %s";
	private String failureMessage;
	
	public CreateException(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	@Override
	public String toString() {
		return String.format(hintMessage, failureMessage);
	}
}
