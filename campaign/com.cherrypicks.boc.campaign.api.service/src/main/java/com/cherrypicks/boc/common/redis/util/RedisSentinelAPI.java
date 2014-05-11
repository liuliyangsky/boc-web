package com.cherrypicks.boc.common.redis.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;

import com.cherrypicks.boc.common.exception.InvalidArgumentException;

/**
 * Redis操作接口
 * 
 * @author kelvin.tie
 */
public class RedisSentinelAPI {

	private static Log logger = LogFactory.getLog(RedisSentinelAPI.class);

	//private static JedisPool pool = null;
	private static JedisSentinelPool pool=null;
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

		/*Set<String> sentinels = new HashSet<String>();
		sentinels.add("172.168.11.46:26379");
		sentinels.add("172.168.11.58:26379");
		pool = new JedisSentinelPool("mymaster", sentinels, config);*/
		//pool = new JedisPool(config, PropertiesUtil.getProperty("redis0.host"), Integer.valueOf(PropertiesUtil.getProperty("redis0.port")));
	}
	
	public static final int DB_DEFAULT = 5;
	public static final int DB_SHARE = 6;

	public enum Db {
		DEFAULT(DB_DEFAULT), // 默认数据库
		SHARE(DB_SHARE), // Share数据库
		;
		private int db;

		private Db(int db) {
			this.db = db;
		}

		public int toIntValue() {
			return this.db;
		}

		public static Db toDb(int db) {
			if (DB_DEFAULT == db) {
				return Db.DEFAULT;
			} else if (DB_SHARE == db) {
				return Db.SHARE;
			} else {
				throw new InvalidArgumentException("未知类型[" + db + "].");
			}
		}
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
	public static JedisSentinelPool getPool(String key) {
		// int idx = key.hashCode() % 4;
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisSentinelPool pool, Jedis redis) {
		if (redis != null) {
			redis.select(DB_DEFAULT);
			pool.returnResource(redis);
		}
	}

	public static boolean set(String key, String value) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
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

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
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

	public static boolean hset(String key, String field, String value) {
		return hset(Db.DEFAULT, key, field, value);
	}

	public static boolean hset(RedisSentinelAPI.Db db, String key, String field, String value) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			jedis.hset(key, field, value);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return true;
	}

	public static String hget(String key, String field) {
		return hget(Db.DEFAULT, key, field);
	}

	public static String hget(RedisSentinelAPI.Db db, String key, String field) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		String value = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			value = jedis.hget(key, field);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return value;
	}

	public static boolean hdel(String key, String field) {
		return hdel(Db.DEFAULT, key, field);
	}
	public static boolean hdel(RedisSentinelAPI.Db db,String key, String field) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		long value = 0;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());
			
			value = jedis.hdel(key, field);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return (value > 0);
	}

	public static boolean hsetByFieldIndex(String key, String field, String value) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(field);
			jedis = pool.getResource();

			jedis.hset(key, field, value);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return true;
	}

	public static String hgetByFieldIndex(String key, String field) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		String value = null;

		try {

			pool = getPool(field);
			jedis = pool.getResource();

			value = jedis.hget(key, field);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return value;
	}

	/**
	 * 有序集合添加 function
	 * @param db
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public static boolean zadd(RedisSentinelAPI.Db db, String key, double score, String value) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			return jedis.zadd(key, score, value) > 0;

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return false;
	}
	
	/**
	 * 有序集合刪除 function
	 * @param db
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public static boolean zrem(RedisSentinelAPI.Db db,String key, String... field) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		long value = 0;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			value = jedis.zrem(key, field);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return (value > 0);
	}

	public static Set<String> zrevrange(String key, int start, int end) {
		return zrevrange(Db.DEFAULT, key, start, end);
	}

	public static Set<String> zrevrange(RedisSentinelAPI.Db db, String key, int start, int end) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();

			jedis.select(db.toIntValue());

			return jedis.zrevrange(key, start, end);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return null;
	}

	public static boolean batchHset(String key, Map<String, String> hashDataMap) {
		return batchHset(Db.DEFAULT, key, hashDataMap);
	}

	/**
	 * 批量insert hash
	 * 
	 * @param key
	 * @param hashDataMap
	 *            k->field , v->value
	 */
	public static boolean batchHset(RedisSentinelAPI.Db db, String key, Map<String, String> hashDataMap) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();

			jedis.select(db.toIntValue());

			Pipeline pipeline = jedis.pipelined();

			for (Map.Entry<String, String> data : hashDataMap.entrySet()) {
				pipeline.hset(key, data.getKey(), data.getValue());
			}

			pipeline.sync();

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return true;
	}
	
	/**
	 * 批量insert Sorted Sets
	 * 
	 * @param key
	 * @param hashDataMap
	 *            k->field , v->value
	 */
	public static boolean batchZadd(RedisSentinelAPI.Db db, String key, Map<Double, String> hashDataMap) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();

			jedis.select(db.toIntValue());

			Pipeline pipeline = jedis.pipelined();

			pipeline.zadd(key,hashDataMap);

			pipeline.sync();

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return true;
	}
	
	
	public static List<String> hvals(String key) {
		return hvals(Db.DEFAULT, key);
	}

	public static List<String> hvals(RedisSentinelAPI.Db db, String key) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;
		List<String> result = null;
		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			result = jedis.hvals(key);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return result;
	}
	
	public class Page {
		private int start;
		private int size;

		public Page(int start, int size) {
			start--;

			if (start < 0) {
				start = 0;
			}

			size = start + size - 1;

			if (size == 0) { // redis 0,0 是取第一个
				start = 0;
				size = 0;
			}

			if (size <0) { // redis 0,0 是取第一个
				start = Integer.MAX_VALUE;
				size = Integer.MAX_VALUE;
			}
			

			this.start = start;
			this.size = size;
		}

		public int getStart() {
			return start;
		}

		public int getSize() {
			return size;
		}
	}

	public Page getPage(int mysqlStart, int mysqlSize) {
		return new Page(mysqlStart, mysqlSize);
	}

	public static void main(String[] args) {
	/*	Page p = new RedisAPI().getPage(0, 1);
		System.out.println(p.getStart() + "," + p.getSize());*/
		
		
		//Set<String> sentinels = new HashSet<String>();
		//sentinels.add("172.168.11.46:26379");
		//final JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
		Set<String> sentinels = new HashSet<String>();
		sentinels.add("172.168.11.46:26379");
		sentinels.add("172.168.11.58:26379");
		pool = new JedisSentinelPool("mymaster", sentinels){
			//TODO 重寫方法釋放連接,要不然 切換一次后,切換不回來
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void initPool(final GenericObjectPool.Config poolConfig,PoolableObjectFactory factory) {    
			    if (this.internalPool != null) {
			        try {
			            closeInternalPool();
			        } catch (Exception e) {             
			        }
			    }

			    this.internalPool = new GenericObjectPool(factory, poolConfig);
			}

			public void destroy() {
			    closeInternalPool();
			}

			protected void closeInternalPool() {
			    try {
			        internalPool.close();
			    } catch (Exception e) {
			        throw new JedisException("Could not destroy the pool", e);
			    }
			}
		};
		Thread t = new Thread() {
			public void run() {
				while (true) {
					Jedis j = null;
						try {
							j= pool.getResource();
							System.out.println("ping response: " +pool.getCurrentHostMaster());
							try {	
								System.out.println("ping response: " + j.ping());
								System.out.println("ping response: " + j.getClient().getHost());
								pool.returnResource(j);
							} catch (Exception e) {
								throw e;	
							}
						} catch (Exception e) {
							System.out.println("Caught exception: " + e.getMessage());
							if (j != null) pool.returnBrokenResource(j);
						}
						try { Thread.sleep(5000); } catch (Exception e) {}
				}
			}
		};
		t.start();
		while (true) {
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}

	public static List<String> hgetAll(String identity) {
		return  hgetAll(Db.DEFAULT, identity);
	}
	/**
	 * 刪除Redis Key
	 * @param db
	 * @param key
	 * @return
	 */
	public static boolean delKey(RedisSentinelAPI.Db db,String key) {
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		long value = 0;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			
			jedis.select(db.toIntValue());
			
			value = jedis.del(key);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return (value > 0);
	}
	//more.li 根据field值获取所有的key对应的value
	public static List<String> hgetAll(RedisSentinelAPI.Db db, String key) {

		JedisSentinelPool pool = null;
		Jedis jedis = null;

		List<String> value = null;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			value = jedis.hvals(key);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return value;
	}
	//more.li 有序集合的长度
	public static long zcard(RedisSentinelAPI.Db db, String key){
		JedisSentinelPool pool = null;
		Jedis jedis = null;

		long value = 0;

		try {

			pool = getPool(key);
			jedis = pool.getResource();
			jedis.select(db.toIntValue());

			value = jedis.zcard(key);

		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			logger.error(e.getMessage(), e);
		} finally {
			returnResource(pool, jedis);
		}

		return value;
	}

}
