package com.yyang.project.ecommerce.products.review.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyang.project.ecommerce.products.review.core.events.create.CreateReviewEvent;
import com.yyang.project.ecommerce.products.review.core.events.detail.ProductReviewsEvent;
import com.yyang.project.ecommerce.products.review.core.events.request.RequestProductEvent;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service.RedisProductSerivce;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service.RedisReviewService;
import com.yyang.project.ecommerce.products.review.rest.controller.entity.ReviewModel;

@Service
public class ProductReviewsService {
	
	@Autowired
	RedisProductSerivce productService;
	
	@Autowired
	RedisReviewService reviewService;
	
	public ProductReviewsEvent getReviewsByProduct(String productId) {
		
		ProductReviewsEvent productReviewsEvent = productService.getProductReviews(new RequestProductEvent(productId)).get();
		
		
		return productReviewsEvent;
	}
	
	public void reviewProduct(ReviewModel reviewModel) {
		CreateReviewEvent createEvent = new CreateReviewEvent(reviewModel);
		reviewService.reviewProduct(createEvent);
	}

}
