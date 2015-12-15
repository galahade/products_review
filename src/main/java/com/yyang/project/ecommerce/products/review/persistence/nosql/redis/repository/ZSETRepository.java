package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.ZSETBaseModel;

public abstract class ZSETRepository<T extends ZSETBaseModel<?, ?>> {
	

	@Resource
	protected StringRedisTemplate template;
	
	public Page<T> getItems(String keySuffix, Page<T> page) {
		Assert.notNull(page.getPageCount());
		Assert.notNull(page.getPageNumber());
		page.revertResults();
		
		getResultsFromRedis(keySuffix, page);

		return page;
	}
	
	public void addItem(T model, String suffixKey) {
		template.opsForZSet().add(redisKey(model, suffixKey), model.getValue().toString(), model.getScore().doubleValue());
	}
	
	public void deleteItem(T model, String suffixKey) {
		template.opsForZSet().remove(redisKey(model, suffixKey), model.getValue());
	}
	
	public Boolean exist(T model, String suffixKey) {
		Long result = template.opsForZSet().rank(redisKey(model, suffixKey), model.getValue());
		if(result != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private String redisKey(T model, String suffixKey) {
		return model.redisPrefixKey() + suffixKey;
	}
	
	protected abstract void getResultsFromRedis(String keySuffix, Page<T> page) ;
}
