package com.yyang.test.lettuce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
import com.yyang.project.ecommerce.config.ProductsReviewApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsReviewApplication.class)
public class LettuceTest {
	
	@Autowired
	RedisClusterClient redisClusterClient;
	
	@Autowired
	RedisClient redisClient;
	
	@Test
	public void setClusterKey() {
		StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
		RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", "lettuce4.0");
		connection.close();
		redisClient.shutdown();
	}
	
	@Test
	public void setkey() {
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", "lettuce4.0");
		connection.close();
		redisClient.shutdown();
	}

}
