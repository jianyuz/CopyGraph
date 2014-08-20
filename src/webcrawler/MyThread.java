package webcrawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread{

	private static volatile boolean debugLifeCycle = false;
	private static AtomicInteger created = new AtomicInteger(0);
	private static AtomicInteger alive = new AtomicInteger(0);
	
	private final Logger logger = Logger.getAnonymousLogger();
	
	public MyThread(Runnable r){
		this(r, "default Name");
	}
	
	public MyThread(Runnable r, String name){
		super(r, name + created.incrementAndGet());
		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.log(Level.SEVERE, "uncaught exception in " + getName(), e);
			}
		});
	}
	
	public void run(){
		boolean debug = debugLifeCycle;
		if(debug)
			logger.log(Level.FINE, "created " + getName());
		
		try{
			alive.incrementAndGet();
			super.run();
		}finally{
			alive.decrementAndGet();
			if(debug) logger.log(Level.FINE, "exiting " + getName());
		}
	}
	
	public static int getThreadCreated(){
		return created.get();
	}
	
	public static int getThreadAlive(){
		return alive.get();
	}
	
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		if(exec instanceof ThreadPoolExecutor){
			((ThreadPoolExecutor) exec).setCorePoolSize(4);
		}
		
		Executors.unconfigurableExecutorService(exec);
	}
}
