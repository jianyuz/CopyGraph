package memoryVisibility;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BrokenPrimeGenearator {

	public static void main (String[] args) throws InterruptedException{
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>();
		PrimeProducer producer = new PrimeProducer(primes);
		producer.start();
		
		try{
			for(int i=0; i< 10; i++){
				primes.take();
			}
		}finally{
			producer.cancel();//not cancellable if the queue is stuck on put.
			
		}
	}
	
	public Task getNextTask(BlockingQueue<Task> queue){
		boolean isInterrupted = false;
		
		try{
			while(true){
				try{
					return queue.take();
				}catch(InterruptedException e){
					isInterrupted = true;//can't reset flag here. forever loop.
					//retry.
				}
			}
		}finally{
			if(isInterrupted){
				Thread.currentThread().interrupt();//before return,
				//set interrupt status.
				
			}
		}
	}
	
	private static ScheduledExecutorService ses;
	
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws Throwable{
		class MyTask implements Runnable{
			private volatile Throwable t;
			@Override
			public void run() {
				try{
					r.run();
				}catch(Throwable t){
					this.t = t;
				}
			}
			
			void rethrow() throws Throwable{
				if(t!= null){
					throw t;
				}
			}
		}
		
		MyTask task = new MyTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		ses.schedule(new Runnable(){
			public void run(){
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));//join to wait for thread to die then check throwable.
		task.rethrow();
	}
	
}

class PrimeProducer extends Thread{
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;
	PrimeProducer(BlockingQueue<BigInteger> queue){
		this.queue = queue;
	}
	public void run(){
		try{
			BigInteger p = BigInteger.ONE;
			while(!cancelled){
				queue.put(p = p.nextProbablePrime());
			}
		}catch(InterruptedException e){
			
		}
	}
	
	public void cancel(){
		cancelled = true;
	}
}


class PrimeProducer1 extends Thread{
	private final BlockingQueue<BigInteger> queue;
	PrimeProducer1(BlockingQueue<BigInteger> queue){
		this.queue = queue;
	}
	public void run(){
		try{
			BigInteger p = BigInteger.ONE;
			while(!Thread.currentThread().isInterrupted()){
				queue.put(p = p.nextProbablePrime());
			}
		}catch(InterruptedException e){
			//we are done. no need to compute primes any more.
		}
	}
	
	public void cancel(){
		this.interrupt();//interrupt itself.
		
	}
}


