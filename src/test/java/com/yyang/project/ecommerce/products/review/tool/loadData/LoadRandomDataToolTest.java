package com.yyang.project.ecommerce.products.review.tool.loadData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yyang.project.ecommerce.config.ProductsReviewApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsReviewApplication.class)
public class LoadRandomDataToolTest {
	
	@Autowired
	LoadRandomDataTool tool;
	
	@Test 
	public void loadTenUserTest() {
		tool.loadTenRandomUser();
	}
	
	@Test
	public void loadOneHundredProductTest() {
		tool.loadProductData(10000);
	}
	
	@Test
	public void loadBuyProductDataTest() {
		tool.loadBuyProductData(100);
	}
	
	@Test
	public void loadReviewsDataTest() {
		tool.loadReviewsData();
	}

}
