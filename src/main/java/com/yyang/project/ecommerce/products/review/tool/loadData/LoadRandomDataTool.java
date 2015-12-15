package com.yyang.project.ecommerce.products.review.tool.loadData;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Product;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.Review;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service.RedisProductSerivce;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service.RedisReviewService;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service.RedisUserSerivce;

@Service
public class LoadRandomDataTool {

	@Autowired
	RedisUserSerivce userService;

	@Autowired
	RedisReviewService reviewService;

	@Autowired
	RedisProductSerivce productService;

	// First,just need run once in the very beginning.
	public void loadTenRandomUser() {
		for (int count = 0; count < 10; count++) {
			User user = new User(UUID.randomUUID());
			user.setPurchaseLevel(RandomUtils.nextLong(1L, 100L));
			user.setSaleLevel(RandomUtils.nextLong(1L, 100L));
			user.setLogin(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(5, 10)));
			user.setReviews(RandomUtils.nextLong(1L, 10000L));
			userService.addUser(user);
			System.out.println(user.getId());
		}
	}

	// Second.
	public void loadProductData(int count) {
		Map<String, String> allUsers = userService.getAllUsersgetAllUsersLoginAndId().get();
		for (int i = 0; i < count; i++) {
			Product product = new Product(productService.newProductId());
			product.setOwnerId(getRandomUserFromAllUser(allUsers).getValue());
			productService.addProduct(product);
		}
	}

	// Third
	public void loadBuyProductData(int times) {
		Map<String, String> allUsers = userService.getAllUsersgetAllUsersLoginAndId().get();
		while (times-- != 0) {
			Entry<String, String> buyerEntry = getRandomUserFromAllUser(allUsers);
			Entry<String, String> sellerEntry = getRandomUserFromAllUser(allUsers);

			while (sellerEntry.getValue().equals(buyerEntry.getValue())) {
				sellerEntry = getRandomUserFromAllUser(allUsers);
			}
			User seller = new User(UUID.fromString(sellerEntry.getValue()));
			User buyer = new User(UUID.fromString(buyerEntry.getValue()));
			List<Product> allProductsForUser = productService.getAllProductsForUser(seller).get();
			Product product = allProductsForUser.get(RandomUtils.nextInt(0, allProductsForUser.size()));

			productService.buyProduct(product, buyer);
		}
	}

	// fourth
	public void loadReviewsData() {
		Map<String, String> allUsers = userService.getAllUsersgetAllUsersLoginAndId().get();
		allUsers.forEach((k, v) -> {
			User user = userService.getUserById(v).get();
			List<Product> products = productService.getUserUnreviewProducts(user).get();
			products.forEach((product) -> {
				Review review = new Review(UUID.randomUUID().toString());
				review.setDeliveryLevel(RandomUtils.nextLong(1L, 6L));
				review.setProductLevel(RandomUtils.nextLong(1L, 6L));
				review.setServiceLevel(RandomUtils.nextLong(1L, 6L));
				review.setDeliveryReviews(RandomStringUtils.randomAscii(40));
				review.setProductReviews(RandomStringUtils.randomAscii(40));
				review.setServiceReviews(RandomStringUtils.randomAscii(40));
				reviewService.reviewProduct(user, product, review);
			});

		});
	}

	@SuppressWarnings("unchecked")
	private Entry<String, String> getRandomUserFromAllUser(Map<String, String> allUsers) {
		Set<Entry<String, String>> usersSet = allUsers.entrySet();
		Object[] userArray = usersSet.toArray();
		return (Entry<String, String>) userArray[RandomUtils.nextInt(0, allUsers.size())];
	}

}
