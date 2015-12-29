package com.yyang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
import com.yyang.project.ecommerce.config.ProductsReviewApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsReviewApplication.class)
public class ClusterTest {
	
	@Autowired
	RedisClusterClient redisClusterClient;
	
	@Test
	public void getRedisKey() {
		StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
		RedisAdvancedClusterCommands<String,String> commands = connection.sync();
		String last = commands.get("__last__");
		System.out.println("The Last is:"+last);
	}
	
	@Test
	public void testCluster() {
		StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
		RedisAdvancedClusterCommands<String,String> commands = connection.sync();
		int last = 0;
		while (true) {
			try{
			String lastValue = commands.get("__last__");
			if(null != lastValue) {
				last = Integer.parseInt(lastValue);
			}
			break;
			} catch(Exception e) {
				e.printStackTrace(); 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					e.printStackTrace();
				}
			}
		}
		
		for(int i = last+1; i < 1000000000; i++) {
			while (Thread.currentThread().isInterrupted()) {
				throw new RuntimeException();
			}
			try{
				commands.set(String.format("foo%d", i), String.valueOf(i));
				System.out.println(commands.get(String.format("foo%d", i)));
				commands.set("__last__", String.valueOf(i));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	@Test
	public void testConsistencyTester() {
		ConsistencyTester tester = new ConsistencyTester(redisClusterClient);
		tester.test();
	}

}
