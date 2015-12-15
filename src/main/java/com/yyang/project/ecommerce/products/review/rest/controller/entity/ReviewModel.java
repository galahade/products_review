package com.yyang.project.ecommerce.products.review.rest.controller.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ReviewModel {
	
	private String id;
	private String login;
	private String productId;
	private String productReviewContent;
	private long productReviewLevel;
	private String serviceReviewContent;
	private long serviceReviewLevel;
	private String deliveryReviewContent;
	private long deliveryReviewLevel;
	
}
