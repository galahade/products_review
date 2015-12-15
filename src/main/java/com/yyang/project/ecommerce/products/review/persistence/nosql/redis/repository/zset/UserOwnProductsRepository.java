package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserOwnProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.ZSETRepository;

@Component
public class UserOwnProductsRepository extends ZSETRepository<UserOwnProducts> {

	@Override
	protected void getResultsFromRedis(String keySuffix, Page<UserOwnProducts> page) {
		
		Set<TypedTuple<String>> redisPageResults = template.opsForZSet().rangeWithScores(UserOwnProducts.REDIS_KEY_PREFIX + keySuffix, page.getBeginNumber(), page.getEndNumber());
		redisPageResults.forEach((item) -> {
			UserOwnProducts productReviews = new UserOwnProducts(item.getValue(), item.getScore().longValue());
			page.addItem(productReviews);
		});
	}

}
