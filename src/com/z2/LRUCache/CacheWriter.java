package com.z2.LRUCache;

public interface CacheWriter<K, V> {

	public void writeEntry(Entry<K, V> entry);

}
