package com.yyang.project.ecommerce.products.review.rest.controller.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ReviewerModel extends UserModel {
	
	private long reviews;
	private long purchaseLevel;
	
}
