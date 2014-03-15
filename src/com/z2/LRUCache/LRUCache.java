package com.z2.LRUCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * FutureTask 
 * use with ExecutorService.
 * Executor.execut(future0.
 * 
 * http://javalandscape.blogspot.com/2009/01/cachingcaching-algorithms-and-caching.html
 * http://java-success.blogspot.com/2012/10/caching-data-in-java-and-lru-strategy.html
 * Cache Hit and cache miss.
 * cache miss
 * there is space, put in.
 * otherwise, replacement policy caching algorithm.
 * optimal replace policy,
 * it should replace the entry which is not used for the longest period of time.
 * 
 * LFU least frequency used.
 * LRU least recently used.
 * LRU improvement
 * LRU2 add to cache when second time it is accessed.
 * remove second most accesses.
 * 2Q two queues.
 * two LRU cache, access again, move them to second larger LRU cache.
 * Adaptive replacement Cache ARC
 * tow LRU L1 and L2 L2 have seen at least twice recently.
 * 
 * MRU most recently used.
 * FIFO keep a list.
 * second change.
 * CLOCK
 * 
 * extended time based expiration.
 * expire item based on interval
 * Sliding time based expiration.
 * idle in teh cache after last access time.
 * 
 * consideration:
 * cost, cost to get them high, keep in cache longer
 * size, put in more smaller items.
 * time, item are time sensitive.
 * 
 * Distributed Cache:
 * map key to server.
 * 
 * measuring cache.
 * hit ration.
 * 
 * 
 * storage cost and
 * retrieval cost.
 * cache invalidation. 
 * stored item needed to be updated.
 * 
 * key or value needs ot be strongly referenced.
 * 
 * @author zhouzhou
 *
 */
public final class LRUCache<K, V>{

	private String cacheName;
	private final InternalMap<Object, CachedValue> entries;
	private final LockManager<K> lockManager = new LockManager();
	private final Configuration configuration;
	
	private final ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	public LRUCache(String cacheName){
		this.cacheName = cacheName;
		entries = new InternalMap<Object, CachedValue>();
		this.configuration = new Configuration<K, V>();
	}
	
	protected void submit(FutureTask<?> task){
		executorService.submit(task);
	}
	
	public Future<V> load(K key){
		CacheLoader<K, ? extends V> cacheLoader = configuration.getCacheLoader();
		
		FutureTask<V> task = new FutureTask<V>(new CacheLoaderLoadCallable<K, V>(this, cacheLoader, key));
		submit(task);
		return task;
	}
	public String getName(){
		return cacheName;
	}
	
	private static class CacheLoaderLoadCallable<K, V> implements Callable<V>{
		
		private LRUCache<K, V> cache;
		private CacheLoader loader;
		private K key;

		CacheLoaderLoadCallable(LRUCache<K, V> cache, CacheLoader loader, K key ){
			this.cache = cache;
			this.loader = loader;
			this.key = key;
		}
		
		public V call() throws Exception{
			Entry entry = loader.load(key);
			cache.put(entry.getKey(), entry.getValue());
			return (V)entry.getValue();
		}
	}
	
	public void put(K key, V value){
		if(value == null){
			throw new NullPointerException("null value specified");
		}
		lockManager.lock(key);
		try{
			Entry<K, V> entry = new Entry<K, V>(key, value);
			writeCacheEntry(entry);
			long now = System.currentTimeMillis();
			CachedValue  cValue = entries.get(key);
			
			if(cValue == null){
				cValue = new CachedValue(value, now, now);
				entries.put((Object)key, cValue);
			}else{
				cValue.setInternalValue(value);
				cValue.setExpiryTime(now);
			}
			
		}finally{
			lockManager.unLock(key);
		}
		return;
	}
	
	/**
	 * remove an entry from the cache.
	 */
	public boolean remove (K key){
		boolean result = false;
		return result;
	}
	
	/**
	 * can load through cacheLoader if configured.
	 * event raised to dispatcher.
	 * 
	 * @param key
	 * @return
	 */
	private V getValue(K key){
		long now = System.currentTimeMillis();
		
		CachedValue cValue = null;
		V value = null;
		lockManager.lock(key);
		try{
			cValue = entries.get(key);
			boolean isExpired = cValue != null && cValue.isExpiredAt(now);
			if(cValue == null || isExpired){
			
				CacheLoader<K, ? extends V> cacheLoader = configuration
						.getCacheLoader();
				if (cacheLoader == null) {
					return null;
				}

				Entry<K, ? extends V> entry = cacheLoader.load(key);
				value = entry.getValue();
				
				//calculate expiry Time.
				long expiryTime = 0;
				cValue = new CachedValue(value, now, expiryTime);
				if (cValue.isExpiredAt(now)) {
					return null;
				} else {
					entries.put(key, cValue);

				}
			}else{
				value = (V)cValue.getInternalValue();
				//adjust expirtyTime for CValue
				long expiryTime = 0;
				cValue.setExpiryTime(expiryTime);
			}
			
			
		}finally{
			lockManager.unLock(key);
		}
		
		return value;
		
	}
	
	private void writeCacheEntry(Entry<K, V> entry){
		if(configuration.isWriteThrough()){
			configuration.getCacheWriter().write(entry);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
