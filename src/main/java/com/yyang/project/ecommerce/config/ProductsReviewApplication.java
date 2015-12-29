package com.yyang.project.ecommerce.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.yyang.project.ecommerce"})
public class ProductsReviewApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsReviewApplication.class, args);
	}


}
