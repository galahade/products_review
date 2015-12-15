package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yyang.project.ecommerce.products.review.core.Page;
import com.yyang.project.ecommerce.products.review.core.events.detail.ProductDetailEvent;
import com.yyang.project.ecommerce.products.review.core.events.detail.ProductReviewsEvent;
import com.yyang.project.ecommerce.products.review.core.events.detail.ReviewDetailEvent;
import com.yyang.project.ecommerce.products.review.core.events.detail.ReviewUserDetailEvent;
import com.yyang.project.ecommerce.products.review.core.events.detail.UserDetailEvent;
import com.yyang.project.ecommerce.products.review.core.events.request.RequestProductEvent;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.exception.BuyProductException;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Product;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.ProductReviews;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserBuyProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserOwnProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset.UserUnreviewProducts;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.ProductRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.ReviewRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.UserRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.ProductReviewsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.UserBuyProductsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.UserOwnProductsRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.zset.UserUnreviewProductsRepository;

@Service
public class RedisProductSerivce {
	
	@Resource
	private UserRepository userRepository;

	@Resource
	private ReviewRepository reviewRepository;
	
	@Resource
	private ProductRepository productRepository;
	
	@Resource
	public ProductReviewsRepository proRevRepository;
	
	@Resource
	public UserOwnProductsRepository userOwnProRepository;
	
	@Resource
	public UserBuyProductsRepository userBuyProRepository;
	
	@Resource
	public UserUnreviewProductsRepository userUnreviewProRepository;
	
	public Optional<ProductReviewsEvent> getProductReviews(RequestProductEvent rProductEvent) {
		
		ProductReviewsEvent resultEvent = new ProductReviewsEvent(new ProductDetailEvent(rProductEvent.getId()));
		
		Page<ProductReviews> productReviewsPage = new Page<ProductReviews>(1, 10);
		
		productReviewsPage = proRevRepository.getItems(rProductEvent.getId(), productReviewsPage);
		
		productReviewsPage.getResults().forEach((productReview) -> {
			Review review = reviewRepository.get(productReview.getReviewId()).get();
			User user = userRepository.get(review.getUid()).get();
			resultEvent.addReviewUserDetail(new ReviewUserDetailEvent(new UserDetailEvent(user), new ReviewDetailEvent(review)));
		});
		
		return Optional.of(resultEvent);
	}
	
	
	public Optional<List<Product>> getUserUnreviewProducts(User user) {
		Page<UserUnreviewProducts> userProductpage = new Page<UserUnreviewProducts>(1,-1);
		userProductpage = userUnreviewProRepository.getItems(user.getId(), userProductpage);
		List<Product> products = new ArrayList<Product>();
		userProductpage.getResults().forEach((userProduct) -> {
			products.add(productRepository.get(userProduct.getProductId()).get());
		});
		return Optional.of(products);
	}
	
	public Optional<List<Product>> getUserBuyAllProducts(User user) {
		Page<UserBuyProducts> userProductpage = new Page<UserBuyProducts>(1,-1);
		userProductpage = userBuyProRepository.getItems(user.getId(), userProductpage);
		List<Product> products = new ArrayList<Product>();
		userProductpage.getResults().forEach((userProduct) -> {
			products.add(productRepository.get(userProduct.getProductId()).get());
		});
		return Optional.of(products);
	}
	
	public Optional<List<Product>> getAllProductsForUser(User user) {
		Page<UserOwnProducts> userProductpage = new Page<UserOwnProducts>();
		userProductpage = userOwnProRepository.getItems(user.getId(), userProductpage);
		List<Product> products = new ArrayList<Product>();
		userProductpage.getResults().forEach((userProduct) -> {
			products.add(productRepository.get(userProduct.getProductId()).get());
		});
		return Optional.of(products);
	}
	
	public void addProduct(Product product) {
		productRepository.add(product);
		UserOwnProducts up = new UserOwnProducts(product.getId(), Instant.now().getEpochSecond());
		userOwnProRepository.addItem(up, product.getOwnerId());
	}
	
	public String newProductId() {
		return productRepository.generateId().toString();
	}
	
	public void buyProduct(Product product, User user) {
		if(product.getOwnerId().equals(user.getId())) {
			throw new BuyProductException(user.getId(), product.getOwnerId(), product.getId());
		}
		UserBuyProducts up = new UserBuyProducts(product.getId(), Instant.now().getEpochSecond());
		userBuyProRepository.addItem(up, user.getId());
		UserUnreviewProducts uup = new UserUnreviewProducts(product.getId(), Instant.now().getEpochSecond());
		userUnreviewProRepository.addItem(uup, user.getId());
	}
	
	
}
