package com.ofhi.common.cache.shiro;


import lombok.Setter;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * 管理Redis中的Session
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ShiroSessionRepository {

    private final Logger log = LoggerFactory.getLogger(ShiroSessionRepository.class);
    private static final String REDIS_SHIRO_SESSION = "shiro-session:";
    private static final int SESSION_VAL_TIME_SPAN = 1800;

    // 保存到Redis中key的前缀 prefix+sessionId
    @Setter
    private String redisShiroSessionPrefix = REDIS_SHIRO_SESSION;

    // 设置会话的过期时间
    @Setter
    private int redisShiroSessionTimeout = SESSION_VAL_TIME_SPAN;

    @Setter
    private RedisTemplate<String, Session> redisTemplate;

    public RedisTemplate<String, Session> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 保存session
     */
    public void saveSession( final Session session) {
        try {
            getRedisTemplate().opsForValue()
                    .set(buildRedisSessionKey(session.getId())
                            , session
                            , redisShiroSessionTimeout
                            , TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("save session to redis error");
        }
    }

    /**
     * 更新session
     */
    public void updateSession( final Session session) {
        try {
            getRedisTemplate().boundValueOps(
                    buildRedisSessionKey(session.getId()))
                    .set(session
                    , redisShiroSessionTimeout
                    , TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("update session error");
        }
    }


    /**
     * 刷新session
     */
    public void refreshSession( final Serializable sessionId) {
        getRedisTemplate().expire(
                buildRedisSessionKey(sessionId)
                , redisShiroSessionTimeout
                , TimeUnit.SECONDS);
    }


    /**
     * 删除session
     */
    public void deleteSession( final Serializable id) {
        try {
            getRedisTemplate().delete(buildRedisSessionKey(id));
        } catch (Exception e) {
            log.error("delete session error");
        }
    }


    /**
     * 获取session
     */
    public Session getSession( final Serializable id) {
        Session session = null;
        try {
            session = getRedisTemplate().boundValueOps(buildRedisSessionKey(id)).get();
        } catch (Exception e) {
            log.info("get session error");
        }
        return session;
    }

    /**
     * 通过sessionId获取sessionKey
     */
    private String buildRedisSessionKey( final Serializable sessionId) {
        return redisShiroSessionPrefix + sessionId;
    }
}
