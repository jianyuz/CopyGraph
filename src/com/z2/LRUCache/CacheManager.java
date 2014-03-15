package com.z2.LRUCache;

import java.util.HashMap;

public class CacheManager {

	private final HashMap<String, LRUCache<?, ?>>
		caches = new HashMap<String, LRUCache<?, ?>>();
	
	private final String name;
	
	private final ClassLoader classLoader;
	
	public CacheManager(String name, ClassLoader classLoader){
		this.name = name;
		this.classLoader = classLoader;
	}
	
	public <K, V> LRUCache<K, V> configureCache(String cacheName, 
			Configuration<K, V> configuration){
		synchronized(caches){
			LRUCache<?, ?> cache = caches.get(cacheName);
			if(cache == null){
				cache = new LRUCache(cacheName);
				caches.put(cache.getName(), cache);
			}else{
				
			}
			return (LRUCache<K, V>) cache;
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
