package com.yyang.project.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.cluster.ClusterClientOptions;
import com.lambdaworks.redis.cluster.RedisClusterClient;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Bean
	RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("192.168.56.120");
		//connectionFactory.setPoolConfig(jedisPoolConfig());
		connectionFactory.setUsePool(true);
		return connectionFactory;
	}
	
	JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setBlockWhenExhausted(true);
		jedisPoolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		jedisPoolConfig.setMaxIdle(8);
		jedisPoolConfig.setMaxTotal(15);
		return jedisPoolConfig;
	}
	
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
	@Bean
	RedisClient redisClient() {
		return RedisClient.create("redis://192.168.56.110:6379/0");
	}
	
	@Bean
	RedisClusterClient redisClusterClient() {
		List<RedisURI> redisURIs = new ArrayList<RedisURI>();
        redisURIs.add(RedisURI.create("redis://192.168.56.11:6379"));
        redisURIs.add(RedisURI.create("redis://192.168.56.12:6379"));
        redisURIs.add(RedisURI.create("redis://192.168.56.13:6379"));
        redisURIs.add(RedisURI.create("redis://192.168.56.21:6379"));
        redisURIs.add(RedisURI.create("redis://192.168.56.22:6379"));
        redisURIs.add(RedisURI.create("redis://192.168.56.23:6379"));
        RedisClusterClient redisClient = RedisClusterClient.create(redisURIs);
        redisClient.setOptions(new ClusterClientOptions.Builder()
                .refreshClusterView(true)
                .refreshPeriod(1, TimeUnit.MINUTES)
                .build());

		return redisClient;
	}

}
