package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.ProductReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.ZSETRepository;

@Component
public class ProductReviewsRepository extends ZSETRepository<ProductReviews> {

	@Override
	protected void getResultsFromRedis(String keySuffix, Page<ProductReviews> page) {
		Set<TypedTuple<String>> redisPageResults = template.opsForZSet().rangeWithScores(ProductReviews.REDIS_KEY_PREFIX + keySuffix, page.getBeginNumber(), page.getEndNumber());
		redisPageResults.forEach((item) -> {
			ProductReviews productReviews = new ProductReviews(item.getValue(), item.getScore().longValue());
			page.addItem(productReviews);
		});
	}

}
