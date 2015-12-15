package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.tool;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

public class LockTool {

	public static final String LOCK_KEY = "lock:";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Optional<UUID> acquiteTimeoutLock(RedisOperations connection, String lockName,
			ChronoUnit acquiteTimeUnit, Long acquiteTimeout, TimeUnit lockTimeUnit, Long lockTimeout) {
		UUID uuid = UUID.randomUUID();
		String lockFullName = LOCK_KEY + lockName;
		Instant end = Instant.now().plus(acquiteTimeout, acquiteTimeUnit);
		while (Instant.now().isBefore(end)) {
			if (connection.opsForValue().setIfAbsent(lockFullName, uuid.toString())) {
				connection.expire(lockFullName, lockTimeout, lockTimeUnit);
				return Optional.of(uuid);
			} else if (connection.getExpire(lockFullName) == -1) {
				connection.expire(lockFullName, lockTimeout, lockTimeUnit);
			}
			Thread.yield();
		}
		return Optional.empty();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Optional<UUID> acquireLock(RedisOperations connection, String lockName, ChronoUnit timeUnit,
			Long timeout) {
		UUID uuid = UUID.randomUUID();
		Instant end = Instant.now().plus(timeout, timeUnit);
		while (Instant.now().isBefore(end)) {
			if (connection.opsForValue().setIfAbsent(LOCK_KEY + lockName, uuid.toString())) {
				return Optional.of(uuid);
			}
		}
		return Optional.empty();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean releaseLock(RedisOperations connection, String lockName, Optional<UUID> identifier) {
		Boolean result = (Boolean) connection.execute(new SessionCallback<Boolean>() {
			@Override
			public Boolean execute(RedisOperations operations) throws DataAccessException {
				String lockKey = LOCK_KEY + lockName;
				while (true) {
					try {
						connection.watch(lockKey);
						Optional<Object> lockValue = Optional.of(connection.opsForValue().get(lockKey));
						if (lockValue.get().toString().equals(identifier.get().toString())) {
							connection.multi();
							connection.delete(lockKey);
							List results = connection.exec();
							if (results == null) {
								throw new EmptyResultDataAccessException(String.format(
										"Transaction return null. So Lock key has been changed when try to release lock %s.",
										lockKey), 1);
							}
							return true;
						}
						connection.unwatch();
						break;
					} catch (NullPointerException e) {
						return true;
					}
				}
				return false;
			}
		});

		return result;
	}

}
