package webcrawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyLogger());
		return t;
	}
	
	public static void main(String[] args){
		ExecutorService executor = Executors.newFixedThreadPool(1, new MyThreadFactory());
		executor.submit(new MyTask());
		executor.submit(new MyTask1());
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				System.out.println("shutting down");
			}
		});
		executor.shutdown();
	}
	
	static class MyTask1 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("I am done");
		}
		
	}
	
	static class MyTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
		
			throw new RuntimeException("illegal");
		}
		
	}

}
