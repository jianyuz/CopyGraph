package anno;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ObjectPool {

	private final Object[] pooledObjs;
	private final boolean[] used;
	private final Semaphore sem;
	
	public static void main(String[] args) throws InterruptedException{
		final ObjectPool pool = new ObjectPool(10);
		final CountDownLatch latch = new CountDownLatch(7);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 15; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Object o = pool.acquire();
						Thread.sleep(2000);//can't use wait. wait match with notify.
						pool.release(o);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						latch.countDown();
					}
				}
			});
		}
		latch.await();
		executor.shutdown();
		System.out.println("done");
		
	}
	public ObjectPool(int num){
		sem = new Semaphore(num, true); //make sure it is fair.
		pooledObjs = new Object[num];
		used = new boolean[num];
		for(int i=0; i< num; i++){
			pooledObjs[i] = new Object();
		}
	}
	
	public Object acquire() throws InterruptedException{
		sem.acquire();
		return getUnusedObject();
		
	}
	
	public void release(Object o){
		if(putbackObject(o)){
			sem.release();
		}
	}
	
	private synchronized Object getUnusedObject(){
		for(int i=0; i< pooledObjs.length; i++){
			if(!used[i]){
				used[i] = true;
				System.out.println("aquired " + i);
				return pooledObjs[i];
			}
		}
		return null;
	}
	
	private synchronized boolean putbackObject(Object o){
		for(int i=0; i< pooledObjs.length; i++){
			if(o == pooledObjs[i]){
				if(used[i]){
					used[i] = false;
					System.out.println("returned " + i);
					return true;
				}else{
					System.out.println("inconsistent " + i);
					return false;
				}
			}
		}
		System.out.println("no match");
		return false;
	}
	
	
}
