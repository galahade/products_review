package com.yyang.project.ecommerce.products.review.rest.controller.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductReviewsModel {
	
	private String productId;
	private SellerModel seller;
	private List<ReviewModel> reviews = new ArrayList<ReviewModel>();

}
