package com.yyang.project.ecommerce.products.review.core.events.detail;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ReviewUserDetailEvent {

	private UserDetailEvent userDetail;
	private ReviewDetailEvent reviewDetail;
	
	public ReviewUserDetailEvent(UserDetailEvent userDetail, ReviewDetailEvent reviewDetail) {
		this.userDetail = userDetail;
		this.reviewDetail = reviewDetail;
	}
}
