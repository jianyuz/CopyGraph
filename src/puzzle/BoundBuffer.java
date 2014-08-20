package puzzle;

import java.util.concurrent.Semaphore;

public class BoundBuffer<E> {

	private final E[] items;
	private final Semaphore availableItems;
	private final Semaphore availableSpace;
	private int putPos;
	private int takePos;
	
	public BoundBuffer(int size){
		items = (E[])new Object[size];
		availableItems = new Semaphore(0);
		availableSpace = new Semaphore(items.length);
		putPos = takePos = 0;
	}
	
	public boolean isEmpty(){
		return availableItems.availablePermits() == 0;
	}
	
	public boolean isFull(){
		return availableSpace.availablePermits() == 0;
	}
	
	public void put(E x) throws InterruptedException{
		availableSpace.acquire();
		doInsert(x);
		System.out.println("put element " + x);
		availableItems.release();
	}
	
	public E take() throws InterruptedException{
		availableItems.acquire();
		
		E e= doTake();
		System.out.println("take element " + e);
		availableSpace.release();
		return e;
	}
	
	public synchronized void doInsert(E e){
		items[putPos] = e;
		int pos = ++ putPos;
		putPos = (pos == items.length)? 0: pos;
	}
	
	public synchronized E doTake(){
		E e = items[takePos];
		items[takePos] = null;
		int pos = ++ takePos;
		takePos = (pos == items.length)? 0: pos;
		
		return e;
	}
}
