package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

public abstract class UserProducts extends ZSETBaseModel<String, Long> {

	public UserProducts(String value, Long score) {
		super(value, score);
	}
	
	public String getProductId() {
		return this.value;
	}
	
	public Long getTime() {
		return this.score;
	}
	
	public void setProductId(String productId) {
		this.value = productId;
	}
	
	public void setTime(Long time) {
		this.score = time;
	}

}
