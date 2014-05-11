package com.cherrypicks.boc.api.dao.redis;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.common.model.AbstractEntity;
import com.cherrypicks.boc.common.redis.util.RedisAPI;
import com.cherrypicks.boc.common.redis.util.RedisKey;
import com.cherrypicks.boc.common.util.Json;

public abstract class AbstractBaseDaoRedis<T extends AbstractEntity> extends Context {
	private Log logger = LogFactory.getLog(this.getClass());

	private Class<T> baseModelClass;
	private String identity;

	@Override
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		logger.info("init");
		this.baseModelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.identity = RedisKey.PREFIX + baseModelClass.getSimpleName();
	}

	@Override
	@PreDestroy
	public void destroy() {
		logger.info("destroy");
	}

	public boolean add(T object) {
		String value = Json.toJson(object);
		return RedisAPI.hset(identity, String.valueOf(object.getId()), value);
	}

	public boolean add(Object key, T object) {
		String value = Json.toJson(object);
		return RedisAPI.hset(identity, String.valueOf(key), value);
	}

	public boolean del(T object) {
		return RedisAPI.hdel(identity, String.valueOf(object.getId()));
	}

	public boolean del(Object key) {
		return RedisAPI.hdel(identity, String.valueOf(key));
	}

	public boolean update(T object) {
		return add(object);
	}

	public boolean update(String key, T object) {
		return add(key, object);
	}

	public T get(Object key) {
		String data = RedisAPI.hget(identity, String.valueOf(key));
		return Json.toObject(data, this.baseModelClass);
	}

	public boolean addList(Object key, List<T> list) {
		String value = Json.toJson(list);
		return RedisAPI.hset(identity, String.valueOf(key), value);
	}

	public List<T> getList(Object key) {
		String data = RedisAPI.hget(identity, String.valueOf(key));
		return Json.toListObject(data, this.baseModelClass);
	}
	
	public List<T> getAllList(){
		List<T> t= new ArrayList<T>();
		List<String> data = RedisAPI.hgetAll(identity);
		for (String string : data) {
			t.add(Json.toObject(string, this.baseModelClass));
		}
		return t;
	}

	protected String getIdentity() {
		return identity;
	}
}
