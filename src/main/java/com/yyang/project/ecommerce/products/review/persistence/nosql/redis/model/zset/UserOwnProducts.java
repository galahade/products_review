package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

public class UserOwnProducts extends UserProducts{
	
	public static final String REDIS_KEY_PREFIX = "userOwnProducts:";

	public UserOwnProducts(String value, Long score) {
		super(value, score);
	}
	
	
	public String redisPrefixKey() {
		return REDIS_KEY_PREFIX;
	}

}
