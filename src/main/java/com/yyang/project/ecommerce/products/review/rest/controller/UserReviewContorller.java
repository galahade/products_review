package com.yyang.project.ecommerce.products.review.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yyang.project.ecommerce.products.review.core.service.ProductReviewsService;
import com.yyang.project.ecommerce.products.review.rest.controller.entity.ReviewModel;

import lombok.Getter;

@RestController
@RequestMapping("/rest/user/{login}/review")
public class UserReviewContorller {
	
	@Autowired
	ProductReviewsService prService;
	
	@RequestMapping(method = RequestMethod.POST)
	public CreateResource writeReview(@RequestBody ReviewModel review, @PathVariable(value = "login") String login) {
		review.setLogin(login);
		prService.reviewProduct(review);
		return new CreateResource(true);
	}
	
	class CreateResource extends ResourceSupport {
		
		@Getter
		private final Boolean result;

		public CreateResource(Boolean result) {
			this.result = result;
			this.add(new Link("/rest/review/", "product-link"));
		}

	}

}
