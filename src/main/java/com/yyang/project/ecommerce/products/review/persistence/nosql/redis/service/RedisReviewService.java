package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import com.yyang.project.ecommerce.products.review.core.events.create.CreateReviewEvent;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Product;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.PersonReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.ProductReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserUnreviewProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.ReviewRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.UserRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.PersonReviewsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.ProductReviewsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.UserUnreviewProductsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.tool.LockTool;
import com.yyang.project.infrastructure.exceptions.application.predicate.LockException;

@Service
public class RedisReviewService {

	@Resource
	private PersonReviewsRepository perRevRepository;

	@Resource
	private ReviewRepository reviewRepository;

	@Resource
	private ProductReviewsRepository proRevRepository;

	@Resource
	private RedisOperations<String, String> connection;

	@Resource
	private UserUnreviewProductsRepository userUnreviewProRepository;
	
	@Resource
	private UserRepository userRepository;

	public static final String REVIEW_LOCK_SUFFIX = "review:";
	
	public void reviewProduct(CreateReviewEvent createEvent) {
		
		String productId = createEvent.getProductId().get();
		String login = createEvent.getLogin().get();
		String userId = userRepository.getIdByLoginName(login);
		Review review = createEvent.getRedisReview();
		
		Optional<UUID> lockResult = LockTool.acquiteTimeoutLock(connection, REVIEW_LOCK_SUFFIX, ChronoUnit.SECONDS, 10L,
				TimeUnit.SECONDS, 1L);
		if (!lockResult.isPresent()) {
			throw new LockException("Fail to get Redis Lock.");
		}
		try {
			UserUnreviewProducts uup = new UserUnreviewProducts(productId, null);
			//if (userUnreviewProRepository.exist(uup, userId)) {
				Long now = Instant.now().getEpochSecond();
				if (review.getId() == null)
					review.setId(UUID.randomUUID().toString());
			//	review.setPOwnerId(product.getOwnerId());
				review.setLogin(login);
				review.setProductId(productId);
				review.setUid(userId);
				review.setTime(now);
				reviewRepository.add(review);
				PersonReviews perRev = new PersonReviews(review.getId(), now);
				perRevRepository.addItem(perRev, userId);

				ProductReviews proRev = new ProductReviews(review.getId(), now);
				proRevRepository.addItem(proRev, productId);

				userUnreviewProRepository.deleteItem(uup, userId);
		//	}
		} finally {
			LockTool.releaseLock(connection, REVIEW_LOCK_SUFFIX, lockResult);
		}
	}

	public void reviewProduct(User user, Product product, Review review) {
		Optional<UUID> lockResult = LockTool.acquiteTimeoutLock(connection, REVIEW_LOCK_SUFFIX, ChronoUnit.SECONDS, 10L,
				TimeUnit.SECONDS, 1L);
		if (!lockResult.isPresent()) {
			throw new LockException("Fail to get Redis Lock.");
		}
		try {
			UserUnreviewProducts uup = new UserUnreviewProducts(product.getId(), null);
			if (userUnreviewProRepository.exist(uup, user.getId())) {
				Long now = Instant.now().getEpochSecond();
				if (review.getId() == null)
					review.setId(UUID.randomUUID().toString());
				review.setPOwnerId(product.getOwnerId());
				review.setLogin(user.getLogin());
				review.setProductId(product.getId());
				review.setUid(user.getId());
				review.setTime(now);
				reviewRepository.add(review);
				PersonReviews perRev = new PersonReviews(review.getId(), now);
				perRevRepository.addItem(perRev, user.getId());

				ProductReviews proRev = new ProductReviews(review.getId(), now);
				proRevRepository.addItem(proRev, product.getId());

				userUnreviewProRepository.deleteItem(uup, user.getId());
			}
		} finally {
			LockTool.releaseLock(connection, REVIEW_LOCK_SUFFIX, lockResult);
		}
	}
}
