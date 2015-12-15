package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;


public class UserTest {
	
User user;
	
	@Before
	public void setup() {
		user = new User(UUID.randomUUID());
		user.setPurchaseLevel(5L);;
		user.setSaleLevel(10L);
		user.setLogin("yyang");
		user.setReviews(1000L);
		
	}
	
	@Test
	public void toMapTest() {
		Optional<Map<String, String>> result = user.toMap();
		assertNotEquals(result, Optional.empty());
		result.get().forEach((k,v) -> System.out.println(String.format("User property is : %s, value is %s", k, v)));
		Map<String, String> resultMap = result.get();
		assertNull(resultMap.get("class"));
		assertEquals("yyang", resultMap.get("login"));
		assertEquals(1000L, Long.parseLong(resultMap.get("reviews")));
		assertEquals(5L, Long.parseLong(resultMap.get("purchaseLevel")));
		assertEquals(10L, Long.parseLong(resultMap.get("saleLevel")));
	}

}
