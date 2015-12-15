package com.yyang.project.ecommerce.products.review.core.events.detail;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserDetailEvent {
	
	private String id;

	private String login;
	
	private Long reviews = 0L;
	
	private Long purchaseLevel = 0L;
	
	private Long saleLevel = 0L;
	
	public UserDetailEvent(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.reviews = user.getReviews();
		this.purchaseLevel = user.getPurchaseLevel();
		this.saleLevel = user.getSaleLevel();
	}

}
