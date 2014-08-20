package webcrawler;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger implements UncaughtExceptionHandler {
	
	public static void main(String[] args){
		new ThreadPoolExecutor(0, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(), new RejectedExecutionHandler(){

			@Override
			public void rejectedExecution(Runnable r,
					ThreadPoolExecutor executor) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		new ThreadPoolExecutor(0, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
		int numOfProcessors = Runtime.getRuntime().availableProcessors();
		
		System.out.println(numOfProcessors);
		MyLogger logger = new MyLogger();
		Thread.currentThread().setUncaughtExceptionHandler(logger);
		int i = 10 /0;
		System.out.println(i);
		
		
	}
	
	public void uncaughtException(Thread t, Throwable e){
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "thread terminated with Exception " + t.getName(), e);
		System.out.println("my exception " + e);
	}

}
