package com.yyang.project.infrastructure.exceptions;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {
	
	public ApplicationException() {
		
	}
	
	public ApplicationException(Throwable throwable) {
		super(throwable);
	}
	

}
