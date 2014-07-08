package webcrawler;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger implements UncaughtExceptionHandler {
	
	public static void main(String[] args){
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
