package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

public class UserBuyProducts extends UserProducts{
	
	public static final String REDIS_KEY_PREFIX = "userBuyProducts:";

	public UserBuyProducts(String value, Long score) {
		super(value, score);
	}
	
	public String redisPrefixKey() {
		return REDIS_KEY_PREFIX;
	}

}
