package com.z2.LRUCache;

public class CachedValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private Object internalValue;
	private long creationTime;
	private long accessTime;
	private long modificationTime;
	
	private long accessCount;
	private long modificationCount;

	private long expiryTime;

	public CachedValue(Object internalValue, long creationTime, long expiryTime){
		this.internalValue = internalValue;
		this.creationTime = creationTime;
		this.modificationTime = creationTime;
		this.accessTime = creationTime;
		this.accessCount = 0;
		this.modificationCount = 0;
		this.expiryTime = expiryTime;
	}

	public Object getInternalValue() {
		return internalValue;
	}

	public void setInternalValue(Object internalValue) {
		this.internalValue = internalValue;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public long getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}

	public long getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(long modificationTime) {
		this.modificationTime = modificationTime;
	}

	public long getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(long accessCount) {
		this.accessCount = accessCount;
	}

	public long getModificationCount() {
		return modificationCount;
	}

	public void setModificationCount(long modificationCount) {
		this.modificationCount = modificationCount;
	}

	public long getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}
	
	/**
	 * Determine if cache entry with this value has expired.
	 * @param time
	 * @return
	 */
	public boolean isExpiredAt(long time){
		return expiryTime > -1 && expiryTime <= time;
	}
	
	public Object getInternalValue(long accessTime){
		this.accessTime = accessTime;
		this.accessCount ++;
		return internalValue;
	}
	
	public void setInternalValue(Object internalValue, long modificationTime){
		this.modificationTime = modificationTime;
		this.modificationCount ++;
        this.internalValue = internalValue;
	}
}
