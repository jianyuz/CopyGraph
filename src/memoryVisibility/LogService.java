package memoryVisibility;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class LogService {
	private final ExecutorService service = Executors.newFixedThreadPool(1);
	PrintWriter writer;
	
	public void stop(){
		
		try {
			service.shutdown();
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			writer.close();
		}
	}
	
	public void log(String msg){
		try{
		service.execute(new LogTask(msg));
		}catch(RejectedExecutionException e){
			//ignored.
		}
	}
	
	class LogTask implements Runnable{
		
		
		String msg;
		
		public LogTask(String msg){
			this.msg = msg;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				writer.println(msg);
			}finally{
				
			}
		}
	}
}
