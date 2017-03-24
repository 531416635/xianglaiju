package com.yao.utils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcacheUtils {

	private static CacheManager cacheManager = CacheManager.create();
	
	private static Cache cache =  cacheManager.getCache("myehcache");

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		EhcacheUtils.cache = cache;
	}

	/*
	 * 通过名称从缓存中获取数据
	 */
	public static Object getCache(String cacheKey) {
		net.sf.ehcache.Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getObjectValue();
	}

	/*
	 * 将对象添加到缓存中
	 */
	public static void putCache(String cacheKey, Object result) {
		Element element = new Element(cacheKey, result);
		cache.put(element);
	}
}
