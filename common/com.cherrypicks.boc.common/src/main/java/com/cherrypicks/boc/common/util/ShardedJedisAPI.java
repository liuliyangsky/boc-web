package com.cherrypicks.boc.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis操作接口一致性Hash实现，80%命中率，适合动态添加server
 * 
 * @author carlye
 */
public class ShardedJedisAPI {

	private static Log logger = LogFactory.getLog(ShardedJedisAPI.class);

	private static ShardedJedisPool pool = null;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxActive(-1);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(200);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWait(1000 * 100);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);

		pool = new ShardedJedisPool(config, getJedisShardInfos());
	}

	private static List<JedisShardInfo> getJedisShardInfos() {
		List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();

		// socket timeout 默认2秒
		jedisShardInfos.add(new JedisShardInfo("localhost", 6379));
		jedisShardInfos.add(new JedisShardInfo("localhost", 6380));
		jedisShardInfos.add(new JedisShardInfo("localhost", 6381));
		jedisShardInfos.add(new JedisShardInfo("localhost", 6382));

		return jedisShardInfos;
	}

	public static void init() {
		logger.info("init");
	}

	public static void destroy() {
		logger.info("destroy");
		pool.destroy();
	}

	/**
	 * 构建redis连接池
	 * 
	 * @return JedisPool
	 */
	public static ShardedJedisPool getPool() {
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(ShardedJedisPool pool, ShardedJedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	public static boolean set(String key, String value) {

		ShardedJedisPool pool = null;
		ShardedJedis jedis = null;

		try {
			pool = getPool();
			jedis = pool.getResource();

			jedis.set(key, value);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return true;
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;

		ShardedJedisPool pool = null;
		ShardedJedis jedis = null;

		try {

			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return value;
	}

}
