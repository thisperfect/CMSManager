package com.ofhi.common.cache.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author 
 * @Date 
 * @Description session redis实现
 */
public class RedisSessionDao extends AbstractSessionDAO {
	private static Logger log = LoggerFactory.getLogger(RedisSessionDao.class);
	
    private static final String sessionIdPrefix = "shiro-session-";
    private static final String sessionIdPrefix_keys = "shiro-session-*";

    @Value("${redis.timeout}")
    private long timeout;

    private RedisTemplate<Serializable, Session> redisTemplate;
    public void setRedisTemplate(RedisTemplate<Serializable, Session> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	@Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = sessionIdPrefix + UUID.randomUUID().toString();
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(sessionId, session, timeout, TimeUnit.SECONDS);
        log.debug("create shiro session ,sessionId is :{}", sessionId.toString());
        return sessionId;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.debug("read shiro session ,sessionId is :{}", sessionId.toString());
        return redisTemplate.opsForValue().get(sessionId);
    }


    @Override
    public void update(Session session) throws UnknownSessionException {
        log.debug("update shiro session ,sessionId is :{}", session.getId().toString());
        redisTemplate.opsForValue().set(session.getId(), session, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        log.debug("delete shiro session ,sessionId is :{}", session.getId().toString());
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Serializable> keys = redisTemplate.keys(sessionIdPrefix_keys);
        if (keys.size() == 0) {
            return Collections.emptySet();
        }
        List<Session> sessions = redisTemplate.opsForValue().multiGet(keys);
        return Collections.unmodifiableCollection(sessions);
    }
}
