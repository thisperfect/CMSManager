package com.ofhi.common.cache.shiro;


import com.ofhi.common.util.RedisUtil;
import com.ofhi.common.util.SerializeUtils;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;



/**
 * 针对自定义的ShiroSession的Redis CRUD操作，通过isChanged标识符，确定是否需要调用Update方法
 * 通过配置securityManager在属性cacheManager查找从缓存中查找Session是否存在，如果找不到才调用下面方法
 * Shiro内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如Realm）是否实现了CacheManagerAware并自动注入相应的CacheManager。
 */
public class CachingShiroSessionDao extends CachingSessionDAO {


    private static final Logger logger = LoggerFactory.getLogger(CachingShiroSessionDao.class);

    // 保存到Redis中key的前缀 prefix+sessionId
    @Setter
    private String prefix = "";

    // 设置会话的过期时间
    @Setter
    private int seconds = 0;

    /**
     * 重写CachingSessionDAO中readSession方法，如果Session中没有登陆信息就调用doReadSession方法从Redis中重读
     */
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session session = getCachedSession(sessionId);
        if (session == null
                || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
            session = this.doReadSession(sessionId);
            if (session == null) {
                throw new UnknownSessionException(String.format("There is no session with id [%s]",sessionId));
            } else {
                // 缓存
                cache(session, session.getId());
            }
        }
        return session;
    }

    /**
     * 根据会话ID获取会话
     *
     * @param sessionId 会话ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            String key = prefix + sessionId;
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                session = SerializeUtils.deserializeFromString(value);
                logger.debug("sessionId {} 被读取 ttl {}: ", sessionId, jedis.ttl(key));
                // 重置Redis中缓存过期时间
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            logger.warn("读取Session失败", e);
        } finally {
            RedisUtil.close(jedis);
        }

        return session;
    }

    public Session doReadSessionWithoutExpire(Serializable sessionId) {
        Session session = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            String key = prefix + sessionId;
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                session = SerializeUtils.deserializeFromString(value);
            }
        } catch (Exception e) {
            logger.warn("读取Session失败", e);
        } finally {
            RedisUtil.close(jedis);
        }

        return session;
    }

    /**
     * 如DefaultSessionManager在创建完session后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
     * 返回会话ID；主要此处返回的ID.equals(session.getId())；
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 创建一个Id并设置给Session
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            // session由Redis缓存失效决定，这里只是简单标识
            session.setTimeout(seconds);
            jedis.setex(prefix + sessionId, seconds, SerializeUtils.serializeToString((ShiroSession) session));
            logger.debug("sessionId {} name {} 被创建", sessionId, session.getClass().getName());
        } catch (Exception e) {
            logger.warn("创建Session失败", e);
        } finally {
            RedisUtil.close(jedis);
        }
        return sessionId;
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     */
    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
        } catch (Exception e) {
            logger.error("ValidatingSession error");
        }

        Jedis jedis = null;
        try {
            if (session instanceof ShiroSession) {
                // 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
                ShiroSession ss = (ShiroSession) session;
                if (!ss.isChanged()) {
                    return;
                }
                Transaction tx = null;
                try {
                    jedis = RedisUtil.getJedis();
                    // 开启事务
                    tx = jedis.multi();
                    ss.setChanged(false);
                    tx.setex(prefix + session.getId(), seconds, SerializeUtils.serializeToString(ss));
                    logger.debug("sessionId {} name {} 被更新", session.getId(), session.getClass().getName());
                    // 执行事务
                    tx.exec();
                } catch (Exception e) {
                    if (tx != null) {
                        // 取消执行事务
                        tx.discard();
                    }
                    throw e;
                }

            } else if (session instanceof Serializable) {
                jedis = RedisUtil.getJedis();
                jedis.setex(prefix + session.getId(), seconds, SerializeUtils.serializeToString((Serializable) session));
                logger.debug("sessionId {} name {} 作为非ShiroSession对象被更新, ", session.getId(), session.getClass().getName());
            } else {
                logger.warn("sessionId {} name {} 不能被序列化 更新失败", session.getId(), session.getClass().getName());
            }
        } catch (Exception e) {
            logger.warn("更新Session失败", e);
        } finally {
            RedisUtil.close(jedis);
        }
    }

    /**
     * 删除会话；当会话过期/会话停止（如用户退出时）会调用
     */
    @Override
    protected void doDelete(Session session) {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            jedis.del(prefix + session.getId());
            logger.debug("Session {} 被删除", session.getId());
        } catch (Exception e) {
            logger.warn("修改Session失败", e);
        } finally {
            RedisUtil.close(jedis);
        }
    }

    /**
     * 删除cache中缓存的Session
     */
    public void uncache(Serializable sessionId) {
        Session session = this.readSession(sessionId);
        super.uncache(session);
        logger.debug("取消session {} 的缓存", sessionId);
    }

    /**
     * 获取当前所有活跃用户，如果用户量多此方法影响性能
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            Set<String> keys = jedis.keys(prefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
                return null;
            }
            List<String> valueList = jedis.mget(keys.toArray(new String[0]));
            return SerializeUtils.deserializeFromStringController(valueList);
        } catch (Exception e) {
            logger.warn("统计Session信息失败", e);
        } finally {
            RedisUtil.close(jedis);
        }
        return null;
    }
}
