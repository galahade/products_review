package com.yyang.project.infrastructure.exceptions.application;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

@SuppressWarnings("serial")
public class POexception extends ApplicationException {
	
	private static final String hintMessage = "There is an error happen : \\n Action: %s. \\n Object: %s";
	
	protected PredicateException predicateE;
	protected ObjectException objectE;
	
	public POexception(PredicateException predicateE, ObjectException objectE) {
		this.predicateE = predicateE;
		this.objectE = objectE;
	}
	
	@Override
	public String toString() {
		return String.format(hintMessage, predicateE.toString(), objectE.toString());
	}

}
