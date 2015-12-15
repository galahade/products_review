package com.yyang.project.infrastructure.exceptions.application.predicate;

import com.yyang.project.infrastructure.exceptions.application.PredicateException;

public class LockException extends PredicateException {

	private static final long serialVersionUID = -8089231366470460818L;
	
	private String hintMessage;
	
	public LockException(String hintMessage) {
		this.hintMessage = hintMessage;
	}
	
	public LockException(Exception exception) {
		super(exception);
	}
	
	@Override
	public String getMessage() {
		return String.format(hintMessage);
	}

}
