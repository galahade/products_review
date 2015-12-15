package com.yyang.project.ecommerce.products.review.core.events.request;

import lombok.Getter;

@Getter
public class RequestProductEvent {

	private String id;
	
	public RequestProductEvent(String id) {
		this.id = id;
	}
}
