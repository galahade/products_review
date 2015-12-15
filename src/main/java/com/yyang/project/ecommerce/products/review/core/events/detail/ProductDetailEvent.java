package com.yyang.project.ecommerce.products.review.core.events.detail;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductDetailEvent {
	
	private String id;
	
	public ProductDetailEvent(String id) {
		this.id = id;
	}
	
}
