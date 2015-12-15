package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.service;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash.UserRepository;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.tool.LockTool;
import com.yyang.project.infrastructure.exceptions.application.DataException;
import com.yyang.project.infrastructure.exceptions.application.predicate.LockException;

@Service
public class RedisUserSerivce {
	
	public static final String USER_LOCK_SUFFIX = "user:";
	
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private RedisOperations<String, String> connection;
	
	public Optional<Map<String, String>> getAllUsersgetAllUsersLoginAndId() {
		return Optional.of(userRepository.getAllUsers());
	}
	
	public Optional<User> getUserById(String id) {
		return userRepository.get(id);
	}
	
	public Optional<User> addUser(User user) {
		Optional<UUID> lockResult = LockTool.acquiteTimeoutLock(connection, USER_LOCK_SUFFIX, ChronoUnit.SECONDS , 10L, TimeUnit.SECONDS, 1L);
		if(!lockResult.isPresent()) {
			throw new LockException("Fail to get Redis Lock.");
		}
		try{
			if (userRepository.exist(user.getLogin())) {
				throw new DataException("User already exist in Redis.");
			}
			
			return userRepository.add(user);
		
		} finally{
			LockTool.releaseLock(connection, USER_LOCK_SUFFIX, lockResult);
		}
		
	}
	
}
