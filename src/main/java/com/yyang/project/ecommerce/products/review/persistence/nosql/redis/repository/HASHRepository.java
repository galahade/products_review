package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.HASHBaseModel;

public abstract class HASHRepository<T extends HASHBaseModel> {
	
	@Resource
	protected StringRedisTemplate template;
	
	public Optional<T> add(T model) {
		Assert.notNull(model.getId());
		template.opsForHash().putAll(model.redisKey(), model.toMap().get());
		return Optional.of(model);
	}
	public  void update(T model) {
		template.opsForHash().putAll(model.redisKey(), model.toMap().get());
	}
	public abstract Optional<T> get(String id) ;
	
	public abstract void delete(String id) ;
}
