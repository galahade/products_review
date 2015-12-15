package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.HASHRepository;
import com.yyang.project.infrastructure.exceptions.application.CodeException;

@Component
public class ReviewRepository extends HASHRepository<Review> {

	
	public Optional<Review> get(String id) {
		Assert.notNull(id);
		Review user = new Review(id);
		try {
			BeanUtils.populate(user, template.<String, Object>opsForHash().entries(user.redisKey()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CodeException(e);
		}
		return Optional.of(user);
	}
	
	public void delete(String id) {
		Review user = new Review(id);
		template.delete(user.redisKey());
	}
}
