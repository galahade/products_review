package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.exception;

import com.yyang.project.infrastructure.exceptions.application.POexception;
import com.yyang.project.infrastructure.exceptions.application.object.UserException;
import com.yyang.project.infrastructure.exceptions.application.predicate.CreateException;

public class CreateUserException extends POexception {

	public CreateUserException(CreateException predicateE, UserException objectE) {
		super(predicateE, objectE);
	}

	private static final long serialVersionUID = -5579352444346298457L;

}
