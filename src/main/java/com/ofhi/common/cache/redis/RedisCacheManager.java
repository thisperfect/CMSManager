package com.ofhi.common.cache.redis;

import java.io.Serializable;

import com.ofhi.common.Constant;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author 
 * @Date
 */
public class RedisCacheManager implements CacheManager, Serializable {
	
    private  static Logger log = LoggerFactory.getLogger(RedisCacheManager.class);

    private  RedisTemplate<Object, Object> redisTemplate;
    
	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisCacheManager() {}
    public RedisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

	@Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Cache name cannot be null or empty.");
        }
        log.debug("redis cache manager get cache name is :{}", name);
        Cache cache = (Cache) redisTemplate.opsForValue().get(name);
        if (cache == null) {
            cache = new RedisCache<>(redisTemplate);
            redisTemplate.opsForValue().set(Constant.shiro_cache_prefix + name, cache);
        }
        return cache;
    }

}
