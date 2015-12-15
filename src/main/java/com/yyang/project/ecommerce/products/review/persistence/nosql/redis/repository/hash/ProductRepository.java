package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Product;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.HASHRepository;
import com.yyang.project.infrastructure.exceptions.application.CodeException;

@Component
public class ProductRepository extends HASHRepository<Product> {

	@Override
	public Optional<Product> get(String id) {
		Assert.notNull(id);
		Product product = new Product(id);
		try {
			BeanUtils.populate(product, template.<String, Object>opsForHash().entries(product.redisKey()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CodeException(e);
		}
		return Optional.of(product);
	}

	@Override
	public void delete(String id) {
		Product product = new Product(id);
		template.delete(product.redisKey());
	}
	
	public Long generateId() {
		return template.opsForValue().increment("product:id:", 1);
	}

}
