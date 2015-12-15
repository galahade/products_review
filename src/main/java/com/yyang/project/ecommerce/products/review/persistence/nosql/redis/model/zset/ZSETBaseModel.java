package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class ZSETBaseModel<V, S extends Number> implements BaseModel {
	
	public ZSETBaseModel (V value, S score) {
		this.value = value;
		this.score = score;
	}
	
	protected V value;
	
	protected S score;
	
	public abstract String redisPrefixKey();
	
	@Override
	public String redisKey() {
		throw new UnsupportedOperationException("Can't get Redis key by this method.");
	}
	
}
