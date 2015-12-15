package com.yyang.project.ecommerce.products.review.core.events.detail;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ReviewDetailEvent {
	
	private String id;
	
	private Long time;
	
	private String productReviews;
	
	private Long productLevel;
	
	private String serviceReviews;
	
	private Long serviceLevel;
	
	private String deliveryReviews;
	
	private Long deliveryLevel;
	
	public ReviewDetailEvent(Review review) {
		this.id = review.getId();
		this.time = review.getTime();
		this.productReviews = review.getProductReviews();
		this.productLevel = review.getProductLevel();
		this.serviceReviews = review.getServiceReviews();
		this.serviceLevel = review.getServiceLevel();
		this.deliveryReviews = review.getDeliveryReviews();
		this.deliveryLevel = review.getDeliveryLevel();
	}

}
