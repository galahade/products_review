package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.hash;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash.User;
import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.repository.HASHRepository;
import com.yyang.project.infrastructure.exceptions.application.CodeException;

@Component
public class UserRepository extends HASHRepository<User> {
	
	public Map<String, String> getAllUsers() {
		Map<String, String> entries = template.<String, String>opsForHash().entries(User.REDIS_KEY_PREFIX);
		return entries;
	}
	
	public Optional<User> add(User model) {
		model.setId(UUID.randomUUID().toString());
		template.executePipelined(new SessionCallback<Void>() {
			@Override@SuppressWarnings({ "rawtypes", "unchecked" })
			public Void execute(RedisOperations operations) throws DataAccessException {
				//add item to HASH users: key is login name, value is user id.
				operations.opsForHash().put(User.REDIS_KEY_PREFIX, model.getLogin(), model.getId());
				operations.opsForHash().putAll(model.redisKey(), model.toMap().get());
				return null;
			}
		});
		return Optional.of(model);
	}
	
	public String getIdByLoginName(String loginName) {
		//TO-DO catch NullPointException to throw CreateUserException
		String userId = Optional.of(template.<String, String>opsForHash().get(User.REDIS_KEY_PREFIX, loginName)).get();
		return userId;
	}
	
	public Boolean exist(String loginName) {
		Object result = template.opsForHash().get(User.REDIS_KEY_PREFIX, loginName);
		if(result != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public Optional<User> get(String id) {
		Assert.notNull(id);
		User user = new User(UUID.fromString(id));
		try {
			BeanUtils.populate(user, template.<String, Object>opsForHash().entries(user.redisKey()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CodeException(e);
		}
		return Optional.of(user);
	}
	
	public void delete(String id) {
		User user = new User(UUID.fromString(id));
		template.delete(user.redisKey());
	}

}
