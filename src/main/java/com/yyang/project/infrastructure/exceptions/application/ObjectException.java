package com.yyang.project.infrastructure.exceptions.application;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

@SuppressWarnings("serial")
public class ObjectException extends ApplicationException {

	public ObjectException() {
	}
	
	public ObjectException(Throwable throwable) {
		super(throwable);
	}
}