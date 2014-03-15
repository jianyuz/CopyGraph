package memoryVisibility;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class TaskExecutorServer1 {

	ExecutorService executorService = Executors.newFixedThreadPool(5);

	public void processRequest() {

		try {

			ServerSocket ss = new ServerSocket(80);// port 80

			while (!executorService.isShutdown()) {
				try {
					final Socket socket = ss.accept();

					Runnable task = new Handlerme(socket);
					executorService.execute(task);
					
				} catch (RejectedExecutionException e) {
					if(executorService.isShutdown()){
						System.out.println("rejected submission");
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void stop(){
		executorService.shutdown();
	}
	
	
	
}

class Handlerme implements Runnable{

	private Socket socket;
	
	public Handlerme(final Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		process();
	}
	
	private void process (){
		
	}
	
}

