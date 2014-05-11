package com.cherrypicks.boc.common.redis.util;


/**
 * 
 * 所有操作Redis的key必须从此获取
 * 
 * @author carlye
 */
public class RedisKey {

	public static final String PREFIX = "Boc-App-";

	/*自定義緩存*/
	public static String getTestKey() {
		return PREFIX + "Test-list";
	}

}
