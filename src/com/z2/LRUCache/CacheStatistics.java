package com.z2.LRUCache;

import java.util.concurrent.atomic.AtomicLong;

public class CacheStatistics {

	private final AtomicLong cacheRemovals = new AtomicLong();
	private final AtomicLong cacheExpires = new AtomicLong();
	private final AtomicLong cachePuts = new AtomicLong();
	private final AtomicLong cacheMisses = new AtomicLong();
	private final AtomicLong cacheHits = new AtomicLong();
	private final AtomicLong cacheEvictions = new AtomicLong());
	
	public CacheStatistics(){
		
	}
	
	public void clear(){
		cachePuts.set(0);
		cacheMisses.set(0);
	}
	
	public long getCacheHits(){
		return cacheHits.longValue();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
