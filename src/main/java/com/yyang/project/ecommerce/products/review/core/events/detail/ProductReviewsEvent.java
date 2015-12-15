package com.yyang.project.ecommerce.products.review.core.events.detail;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductReviewsEvent {

	private ProductDetailEvent productDetail;
	
	private List<ReviewUserDetailEvent> reviewUserDetails = new ArrayList<ReviewUserDetailEvent>();
	
	public ProductReviewsEvent(ProductDetailEvent productDetail) {
		this.productDetail = productDetail;
	}
	
	public void addReviewUserDetail(ReviewUserDetailEvent reviewUserDetail) {
		reviewUserDetails.add(reviewUserDetail);
	}
	
}
