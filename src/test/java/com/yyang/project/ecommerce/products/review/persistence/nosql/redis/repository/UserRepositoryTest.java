package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository;

import org.junit.After;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yyang.project.ecommerce.config.ProductsReviewApplication;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsReviewApplication.class)
public class UserRepositoryTest {
	
	@Autowired
	UserRepository repository;
	User user;
	
	@Before
	public void setup() {
		user = new User();
		user.setLogin("yyang");
		user.setPurchaseLevel(5L);
		user.setReviews(10L);
		user.setSaleLevel(4L);
	}
	
	@Test
	public void addGetUpdateDeleteTest() {
		
		user = repository.add(user).get();
		System.out.println(String.format("Redis key is : %s", user.redisKey()));
		User otherUser = repository.get(user.getId()).get();
		//test get a same User
		assertEquals(user.getId(), otherUser.getId());
		assertEquals(user.getLogin(), otherUser.getLogin());
		assertEquals(user.getPurchaseLevel(), otherUser.getPurchaseLevel());
		assertEquals(user.getReviews(), otherUser.getReviews());
		assertEquals(user.getSaleLevel(), user.getSaleLevel());
		
		//test Update a single value
		otherUser.setReviews(otherUser.getReviews()+1);
		repository.update(otherUser);
		assertNotEquals(user.getReviews(), otherUser.getReviews());
		assertEquals(user.getId(), otherUser.getId());
		assertEquals(user.getLogin(), otherUser.getLogin());
		assertEquals(user.getPurchaseLevel(), otherUser.getPurchaseLevel());
		assertEquals(user.getSaleLevel(), user.getSaleLevel());
		
		Map<String, String> allUsers = repository.getAllUsers();
		assertNotEquals(0, allUsers.size());
		assertTrue(allUsers.containsKey(user.getLogin()));
		
	}
	
	
	@After
	public void destory() {
		if(user.getId() != null) {
			repository.delete(user.getId());
		}
	}
	
	

}
