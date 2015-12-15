package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class Product extends HASHBaseModel {
	
	public static final String REDIS_KEY_PREFIX = "product:";
	
	private String ownerId;
	
	public Product(String id) {
		super(id);
	}

	@Override
	public String redisKey() {
		return REDIS_KEY_PREFIX + this.getId();
	}

}
