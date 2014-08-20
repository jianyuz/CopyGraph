package webcrawler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundExecutor {
	private Executor exec;
	private Semaphore sem;

	BoundExecutor(){
		this.exec = Executors.newCachedThreadPool();
		this.sem = new Semaphore(2);
	}
	
	public void submit(final Runnable r) throws InterruptedException{
		sem.acquire();
		try{
			exec.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						r.run();
					}finally{
						sem.release();
					}
				}});
		}catch(RejectedExecutionException e){
			sem.release();
		}
	}
	
//	public void shutdown(){
//		exec.
//	}
	
	public static void main(String[] args) throws InterruptedException{
		BoundExecutor exec = new BoundExecutor();
		for(int i = 1; i< 10; i++){
			exec.submit(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					
				}});
		}
		
		
	}
}
