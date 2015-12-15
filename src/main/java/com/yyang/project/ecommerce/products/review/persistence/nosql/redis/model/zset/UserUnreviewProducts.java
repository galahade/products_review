package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

public class UserUnreviewProducts extends UserProducts{
	
	public static final String REDIS_KEY_PREFIX = "userUnreviewProducts:";

	public UserUnreviewProducts(String value, Long score) {
		super(value, score);
	}
	
	public String redisPrefixKey() {
		return REDIS_KEY_PREFIX;
	}

}
