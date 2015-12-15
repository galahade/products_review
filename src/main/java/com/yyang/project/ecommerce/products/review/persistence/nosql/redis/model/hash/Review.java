package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class Review extends HASHBaseModel {
	
public static final String REDIS_KEY_PREFIX = "review:";
	
	public Review(String id) {
		super(id);
	}
	
	private String uid;
	
	private String login;
	
	private String productId;
	
	private String pOwnerId;
	
	private Long time;
	
	private String productReviews;
	
	private Long productLevel = 0L;
	
	private String serviceReviews;
	
	private Long serviceLevel = 0L;
	
	private String deliveryReviews;
	
	private Long deliveryLevel = 0L;

	@Override
	public String redisKey() {
		return REDIS_KEY_PREFIX + this.getId();
	}

}
