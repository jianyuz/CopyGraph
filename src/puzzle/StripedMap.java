package puzzle;

/*
 * lock striping
 * buckets store linked list
 * each is a node with next point
 * also keeps key and value.
 * modularization to determine the bucket location.
 * hash modular decide whick lock to synchronize.
 * 
 */
public class StripedMap {

	private static final int numOfLocks = 16;
	private final Node[] buckets;
	private final Object[] locks;
	
	public StripedMap(int numOfBuckets){
		buckets = new Node[numOfBuckets];
		locks = new Object[numOfLocks];
		for(int i=0; i< numOfLocks; i++){
			locks[i] = new Object();
		}
	}
	
	private final int hash(Object key){
		return Math.abs(key.hashCode()%buckets.length);
	}
	public Object get(Object key){
		int hash = hash(key);
		synchronized(locks[hash%numOfLocks]){
			for(Node m = buckets[hash]; m != null ; m=m.next){
				if(m.key == key){
					return m;
				}
			}
		}//keep keys in the node entity.
		
		return null;
	}
	
	public void clear(){
		for(int i=0; i< buckets.length; i++){
			synchronized(locks[i%numOfLocks]){
				buckets[i] = null;
			}
		}
	}
	static class Node{
		Node next;
		Object key;
		Object value;
	}
}
