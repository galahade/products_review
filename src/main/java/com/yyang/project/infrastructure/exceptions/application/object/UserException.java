package com.yyang.project.infrastructure.exceptions.application.object;

import com.yyang.project.infrastructure.exceptions.application.ObjectException;

public class UserException extends ObjectException {

	private static final long serialVersionUID = 8608471082937771016L;
	
	protected String loginName;
	
	public UserException(String loginName) {
		this.loginName = loginName;
	}
	
	public UserException(String loginName, Exception e) {
		super(e);
		this.loginName = loginName;
	}

}
