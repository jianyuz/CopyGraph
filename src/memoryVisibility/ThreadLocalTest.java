package memoryVisibility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalTest {
	
	public static void main(String[] args){
		new Thread(new Task()).start();
		new Thread(new Task()).start();
	}
	
	
	
}

class ThreadLocalDateFormat{
	private static final ThreadLocal<SimpleDateFormat> holder = new ThreadLocal<SimpleDateFormat>(){

		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    
			System.out.println("thread " + Thread.currentThread().getName() + " create the date format");
			return dateFormat;
		}
		
	};
	
	public static SimpleDateFormat getDateFormat(){
		return holder.get();
	}
	
}

class Task implements Runnable{
	public void run(){
		for(int i = 0; i< 2; i++){
			System.out.println("thread " + Thread.currentThread().getName() + " print " + 
					ThreadLocalDateFormat.getDateFormat().format(new Date()));
		}
	}
}

