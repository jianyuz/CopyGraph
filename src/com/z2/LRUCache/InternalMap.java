package com.z2.LRUCache;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InternalMap <K, V>{
	private final ConcurrentHashMap<K, V> internalMap = new 
			ConcurrentHashMap<K, V> ();
	
	public boolean containsKey(Object key){
		return internalMap.containsKey(key);
	}
	
	public V get(Object key)
	{
		return internalMap.get(key);
	}
	
	public Iterator<Map.Entry<K, V>> iterator(){
		return internalMap.entrySet().iterator();
	}
	
	public void put(K key, V value){
		internalMap.put(key, value);
	}
	
	public V getAndPut(K key, V value){
		return internalMap.put(key,  value);
	}
	
	public V remove(Object key){
		return internalMap.remove(key);
	}
	
	public int size(){
		return internalMap.size();
	}

	public void clear(){
		internalMap.clear();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
