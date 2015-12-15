package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserBuyProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.ZSETRepository;

@Component
public class UserBuyProductsRepository extends ZSETRepository<UserBuyProducts> {

	@Override
	protected void getResultsFromRedis(String keySuffix, Page<UserBuyProducts> page) {
		Set<TypedTuple<String>> redisPageResults = template.opsForZSet().rangeWithScores(UserBuyProducts.REDIS_KEY_PREFIX + keySuffix, page.getBeginNumber(), page.getEndNumber());
		redisPageResults.forEach((item) -> {
			UserBuyProducts productReviews = new UserBuyProducts(item.getValue(), item.getScore().longValue());
			page.addItem(productReviews);
		});
	}

}
