package com.yyang.project.infrastructure.exceptions.application;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

public class CodeException extends ApplicationException {

	private static final long serialVersionUID = -7849614355242726862L;
	
	public CodeException(Exception e) {
		super(e);
	}

}
