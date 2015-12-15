package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.PersonReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.ProductReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.ZSETRepository;

@Component
public class PersonReviewsRepository extends ZSETRepository<PersonReviews> {

	@Override
	protected void getResultsFromRedis(String keySuffix, Page<PersonReviews> page) {
		Set<TypedTuple<String>> redisPageResults = template.opsForZSet().rangeWithScores(ProductReviews.REDIS_KEY_PREFIX + keySuffix, page.getBeginNumber(), page.getEndNumber());
		redisPageResults.forEach((item) -> {
			PersonReviews personReviews = new PersonReviews(item.getValue(), item.getScore().longValue());
			page.addItem(personReviews);
		});
	}
	
	

}

