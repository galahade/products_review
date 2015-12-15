package com.yyang.project.infrastructure.exceptions.application;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

@SuppressWarnings("serial")
public class PredicateException extends ApplicationException {

	public PredicateException() {
	}
	
	public PredicateException(Throwable throwable) {
		super(throwable);
	}
}
