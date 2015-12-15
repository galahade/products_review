package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class User extends HASHBaseModel {
	
	public static final String REDIS_KEY_PREFIX = "user:";
	
	public User(UUID id) {
		super(id.toString());
	}
	
	public User(String id) {
		super(id);
	}
	
	private String login;
	
	private Long reviews = 0L;
	
	private Long purchaseLevel = 0L;
	
	private Long saleLevel = 0L;

	@Override
	public String redisKey() {
		return REDIS_KEY_PREFIX + this.getId();
	}
}
