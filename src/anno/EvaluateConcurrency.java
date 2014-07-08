package anno;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvaluateConcurrency {

	
	
	public static long evaluatePerformance(Executor executor, int concurrency, final Runnable job) throws InterruptedException{
		final CountDownLatch readyLatch = new CountDownLatch(5);
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(5);
		
		
		for(int i=0; i< concurrency; i++){
			executor.execute(new Runnable(){

				@Override
				public void run() {
					readyLatch.countDown();
					try {
						startLatch.await();
						job.run();
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}finally{
						endLatch.countDown();
					}
					
				}
				
			});
		}
		//latches order should be the same.
		readyLatch.await();
		long startTime = System.nanoTime();
		startLatch.countDown();
		endLatch.await();
		return System.nanoTime() - startTime;
	}
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService executor = Executors.newFixedThreadPool(5);
		long perf = evaluatePerformance(executor, 5, new Runnable(){

			@Override
			public void run() {
				for(int i=0; i< 10000; i++){
					
				}
			}
			
		});
		System.out.println("generate perf in milliseconds " + perf/(1000.0*1000));
	}
}
