package com.z2.LRUCache;

import java.util.ArrayList;

public class Configuration <K, V> {

	protected ArrayList<CacheEntryListenerRegistration<? super K, ? super V>>
		cacheEntryListenerRegistrations;
	protected CacheLoader <K, ? extends V> cacheLoader;
	protected CacheWriter<? super K, ? super V> cacheWriter;
	protected ExpirtyPolicy<? super K, ? super V> expiryPolicy;
	protected boolean isStatisticsEnabled;
	protected boolean isReadThrough;
	protected boolean isWriteThrough;
	protected boolean isStoredByValue;
	
	public Configuration(){
		this.cacheEntryListenerRegistrations = new ArrayList<CacheEntryListenerRegistration<? super K, ? super V>
		 ();
		this.cacheLoader = null;
		this.cacheWriter = null;
		this.expiryPolicy = new ExpiryPolicy.Default<K, V>();
		this.isReadThrough = false;
		this.isWriteThrough = false;
		this.isStoredByValue = false;
		this.isStatisticsEnabled = false;
	}
	
	public CacheLoader getCacheLoader(){
		return this.cacheLoader;
	}
	
	
	public CacheWriter<? super K, ? super V> getCacheWriter() {
		return cacheWriter;
	}

	public void setCacheWriter(CacheWriter<? super K, ? super V> cacheWriter) {
		this.cacheWriter = cacheWriter;
	}

	public ExpirtyPolicy<? super K, ? super V> getExpiryPolicy() {
		return expiryPolicy;
	}

	public void setExpiryPolicy(ExpirtyPolicy<? super K, ? super V> expiryPolicy) {
		this.expiryPolicy = expiryPolicy;
	}

	public boolean isStatisticsEnabled() {
		return isStatisticsEnabled;
	}

	public void setStatisticsEnabled(boolean isStatisticsEnabled) {
		this.isStatisticsEnabled = isStatisticsEnabled;
	}

	public boolean isReadThrough() {
		return isReadThrough;
	}

	public void setReadThrough(boolean isReadThrough) {
		this.isReadThrough = isReadThrough;
	}

	public boolean isWriteThrough() {
		return isWriteThrough;
	}

	public void setWriteThrough(boolean isWriteThrough) {
		this.isWriteThrough = isWriteThrough;
	}

	public boolean isStoredByValue() {
		return isStoredByValue;
	}

	public void setStoredByValue(boolean isStoredByValue) {
		this.isStoredByValue = isStoredByValue;
	}

	public void setCacheLoader(CacheLoader<K, ? extends V> cacheLoader) {
		this.cacheLoader = cacheLoader;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public CacheWriter getCacheWriter() {
		// TODO Auto-generated method stub
		return null;
	}

}
