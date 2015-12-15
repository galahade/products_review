package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.exception;

import com.yyang.project.infrastructure.exceptions.ApplicationException;

public class BuyProductException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	
	private static final String hintMessage = "Buyer:%s can't buy product:%s which belong to user:%s.";
	
	private String userId;
	private String ownerId;
	private String productId;
	
	public BuyProductException(String userId, String ownerId, String productId) {
		this.userId = userId;
		this.ownerId = ownerId;
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return String.format(hintMessage, userId, ownerId, productId);
	}

}
