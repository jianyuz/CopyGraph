package com.z2.LRUCache;

public interface CacheLoader<K, V> {
	
	Entry<K, V> load(K key);
	
}
