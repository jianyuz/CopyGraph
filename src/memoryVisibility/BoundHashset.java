package memoryVisibility;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundHashset<T> {
	Set<T> set = new HashSet<T>();
	Semaphore sem = new Semaphore(100, true);

	public boolean addResource(T t) throws InterruptedException{
		sem.acquire();
		boolean added = false;
		try{
		added = set.add(t);
		return added;
		}finally{
			if(!added){
				sem.release();
			}
		}
		
	}
	
	public boolean removeResource(T t){
		boolean removed = set.remove(t);
		if(removed){
			sem.release();
		}
		return removed;
	}
}
