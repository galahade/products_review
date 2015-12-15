package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserUnreviewProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.ZSETRepository;

@Component
public class UserUnreviewProductsRepository extends ZSETRepository<UserUnreviewProducts> {

	@Override
	protected void getResultsFromRedis(String keySuffix, Page<UserUnreviewProducts> page) {
		Set<TypedTuple<String>> redisPageResults = template.opsForZSet().rangeWithScores(UserUnreviewProducts.REDIS_KEY_PREFIX + keySuffix, page.getBeginNumber(), page.getEndNumber());
		redisPageResults.forEach((item) -> {
			UserUnreviewProducts productReviews = new UserUnreviewProducts(item.getValue(), item.getScore().longValue());
			page.addItem(productReviews);
		});
	}

}
