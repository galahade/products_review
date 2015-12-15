package com.yyang.project.ecommerce.products.review.core.events.create;

import java.util.Optional;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;
import com.yyang.project.ecommerce.products.review.rest.controller.entity.ReviewModel;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateReviewEvent {
	
	private Optional<String> login;
	private Optional<String> productId;
	private Optional<String> productReviewContent;
	private Optional<Long> productReviewLevel;
	private Optional<String> serviceReviewContent;
	private Optional<Long> serviceReviewLevel;
	private Optional<String> deliveryReviewContent;
	private Optional<Long> deliveryReviewLevel;
	
	public CreateReviewEvent(ReviewModel reviewModel) {
		login = Optional.of(reviewModel.getLogin());
		productId = Optional.of(reviewModel.getProductId());
		productReviewContent = Optional.of(reviewModel.getProductReviewContent());
		productReviewLevel = Optional.of(reviewModel.getProductReviewLevel());
		serviceReviewContent = Optional.of(reviewModel.getServiceReviewContent());
		serviceReviewLevel = Optional.of(reviewModel.getServiceReviewLevel());
		deliveryReviewContent = Optional.of(reviewModel.getDeliveryReviewContent());
		deliveryReviewLevel = Optional.of(reviewModel.getDeliveryReviewLevel());
	}
	
	public Review getRedisReview() {
		Review review = new Review();
		review.setLogin(login.get());
		review.setProductId(productId.get());
		review.setProductReviews(productReviewContent.get());
		review.setProductLevel(productReviewLevel.get());
		review.setServiceReviews(serviceReviewContent.get());
		review.setServiceLevel(serviceReviewLevel.get());
		review.setDeliveryReviews(deliveryReviewContent.get());
		review.setDeliveryLevel(deliveryReviewLevel.get());
		
		return review;
	}

}
