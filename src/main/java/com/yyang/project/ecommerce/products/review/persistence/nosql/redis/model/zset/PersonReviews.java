package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.zset;

public class PersonReviews extends ZSETBaseModel<String, Long>{

	public PersonReviews(String value, Long score) {
		super(value, score);
	}

	public static final String REDIS_KEY_PREFIX = "personReviews:";
	
	public String redisPrefixKey() {
		return REDIS_KEY_PREFIX;
	}
	
	public String getReviewId() {
		return this.value;
	}
	
	public Long getReviewTime() {
		return this.score;
	}
	
	public void setReviewId(String reviewId) {
		this.value = reviewId;
	}
	
	public void setReviewTime(Long time) {
		this.score = time;
	}
}
