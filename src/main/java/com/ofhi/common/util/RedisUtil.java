package com.ofhi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\8\16 0016
 * Time: 10:21
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**30分钟默认超时超时时间，秒**/
    public static final int REDIS_TIME_OUT = 1800;

    private RedisUtil() {}

    //Redis服务器IP
    private static String ADDR = ProjectUtils.getProperty("redis.host", "localhost");

    //Redis的端口号
    private static int PORT = ProjectUtils.getInteger("redis.port", 6379);

    //访问密码
    //private static String AUTH = "admin";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取redis键值-object
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null && bytes.length > 0) {
                return SerializerUtil.deserializeObj(bytes);
            }
        } catch (Exception e) {
            logger.error("getObject获取redis键值异常:key={} cause:{}", key, e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 设置redis键值-object
     *
     * @param key
     * @param value
     * @param
     * @return
     */
    public static String set(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key.getBytes(), SerializerUtil.serializeObj(value));
        } catch (Exception e) {
            logger.error("setObject设置redis键值异常:key={}, cause:", key, e.getMessage());
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 设置redis 30分钟后消失
     * @param key
     * @param value
     * @return
     */
    public static String setDefaultTimeOut(String key, Object value) {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key.getBytes(), SerializerUtil.serializeObj(value));
            if ("OK".equals(result)) {
                jedis.expire(key.getBytes(), REDIS_TIME_OUT);
            }
            return result;
        } catch (Exception e) {
            logger.error("setDefaultTimeOut设置redis键值异常:key={} cause:{}", key, e.getMessage());
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置redis
     * @param key
     * @param value
     * @param expiretime 超时时间，单位：秒
     * @return
     */
    public static String set(String key, Object value, int expiretime) {
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key.getBytes(), SerializerUtil.serializeObj(value));
            if ("OK".equals(result)) {
                jedis.expire(key.getBytes(), expiretime);
            }
            return result;
        } catch (Exception e) {
            logger.error("setObject设置redis键值异常:key={} cause:{}", key, e.getMessage());
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 删除key
     */
    public static Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 检查key是否存在
     * @param key
     * @return 存在返回true，不存在返回false
     */
    public static Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 关闭资源
     * @param jedis
     */
    private static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
