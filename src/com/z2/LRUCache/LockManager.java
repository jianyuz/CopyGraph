package com.z2.LRUCache;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * a mechanism to manage locks for a collection of objects.
 * lock replace synchronized
 * condition replace object monitor method (wait, notify and notify all)
 * condition for thread to suspend execution until condition is true.
 * condition is bound to a lock.
 * await and signal.
 * implementation
 * renetrantLocak
 * can acquire the same lock multiple times.
 * 
 * @author zhouzhou
 *
 */
public class LockManager<K> {

	//map to keep track of key and corresponding locks.
	private final ConcurrentHashMap<K, ReentrantLock> locks = 
			new ConcurrentHashMap<K, ReentrantLock>();
	
	//lock factory.
	private final LockFactory lockFactory = new LockFactory();
	
	private static class LockManagerHolder{
		public static final LockManager instance = new LockManager();
	}
	LockManager(){
		
	}
	
	public static LockManager getInstance(){
		return LockManagerHolder.instance;
	}
	
	public void lock(K key){
		ReentrantLock lock = lockFactory.getLock();
		while(true){
			ReentrantLock oldLock = locks.putIfAbsent(key, lock);
			if(oldLock == null){
				return; //lock successfully.
			}
			oldLock.lock();
			//we got the lock, we the item may have been removed.
			//release the lock.
			//go into loop to reuse lock in the pool.
			lockFactory.release(oldLock);
		}
	}
	
	public void unLock(K key){
		ReentrantLock lock = locks.remove(key);
		lockFactory.release(lock);
	}
	
	/**
	 * A factory for the reentrantLock
	 * @author zhouzhou
	 *
	 */
	private static final class LockFactory{
		private static final int CAPACITY = 100;
		private static final ArrayList<ReentrantLock> LOCKS = new ArrayList<ReentrantLock>(CAPACITY);
		
		private ReentrantLock getLock(){
			ReentrantLock lock = null;
			synchronized(LOCKS){
				if(!LOCKS.isEmpty()){
					lock =  LOCKS.remove(0);
				}
			}
			//create a new one
			ReentrantLock res = (lock !=null)? lock: new ReentrantLock();
			res.lock();
			return res;
		}
		
		private void release(ReentrantLock lock){
			lock.unlock();
			//add it to the resovoir
			synchronized(LOCKS){
				if(LOCKS.size() < CAPACITY){
					LOCKS.add(lock);
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
