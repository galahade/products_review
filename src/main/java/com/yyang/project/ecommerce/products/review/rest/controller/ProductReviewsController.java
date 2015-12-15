package com.yyang.project.ecommerce.products.review.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yyang.project.ecommerce.products.review.core.events.detail.ProductReviewsEvent;
import com.yyang.project.ecommerce.products.review.core.service.ProductReviewsService;

import lombok.Getter;

@RestController
@RequestMapping("/rest/product/{productId}/review")
public class ProductReviewsController {
	
	@Autowired
	ProductReviewsService prService;

	@RequestMapping(method = RequestMethod.GET)
	public ProductResource getAccountInfo(@PathVariable(value = "productId") String productId) {
		ProductReviewsEvent reviewsByProduct = prService.getReviewsByProduct(productId);
		return new ProductResource(reviewsByProduct);
	}

	class ProductResource extends ResourceSupport {
		@Getter
		private final ProductReviewsEvent productReviews;

		public ProductResource(ProductReviewsEvent productReviews) {
			this.productReviews = productReviews;
			this.add(new Link("/rest/product/"+productReviews.getProductDetail().getId(),"product-link"));
		}

	}

}
