package memoryVisibility;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutorServer {

	Executor executorService = Executors.newFixedThreadPool(5);

	public void processRequest() {

		try {

			ServerSocket ss = new ServerSocket(80);// port 80

			while (true) {
				final Socket socket = ss.accept();

				Runnable task = new Handler(socket);
				executorService.execute(task);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}

class Handler implements Runnable{

	private Socket socket;
	
	public Handler(final Socket socket){
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

class ThreadPerTaskExecutor implements Executor{
	public void execute(Runnable r){
		new Thread(r).start();
	}
}


class SerialTaskExecutor implements Executor{
	public void execute(Runnable r){
		r.run();
	}
}