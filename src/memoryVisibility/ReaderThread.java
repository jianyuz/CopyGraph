package memoryVisibility;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReaderThread  extends Thread{

	private final Socket socket;
	private final InputStream inputStream;
	
	public ReaderThread(Socket socket) throws IOException{
		this.socket = socket;
		this.inputStream = socket.getInputStream();
	}
	
	public void interrupt() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}finally{
			super.interrupt();
		}
	}
	
	public void run(){
		byte[] bf = new byte[512];
		try {
			while (true) {
				int count = inputStream.read(bf);
				if (count < 0) {
					return;
				} else {
					processBuff(bf, count);
				}
			}
		} catch (IOException e) {
			// interrupted or socke it broken.
		}
	}
		

	private void processBuff(byte[] bf, int count){
	}
}

interface CancelCallable<T> extends Callable<T>{
	void cancel();
	RunnableFuture<T> newTask();
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor{
	public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected<T> RunnableFuture<T> newTaskFor( Callable<T> callable){
		if(callable instanceof CancelCallable){
			return ((CancelCallable<T>) callable).newTask(); //customized cancel return a new task.
		}else{
			return super.newTaskFor(callable);
		}
	}
}

class SocketTask<T> implements CancelCallable<T>{
	private Socket socket;
	protected synchronized void setSocket(Socket socket){
		this.socket = socket;
	}
	
	public synchronized void cancel(){
		try{
			if(socket != null){
				socket.close();
			}
		}catch(IOException e){}
	}
	
	public RunnableFuture<T> newTask(){//customized task need new cancel.
		return new FutureTask<T>(this){
			@SuppressWarnings("finally")
			public boolean cancel(boolean mayInterruptIfRunning){
				try{
					SocketTask.this.cancel();
				}finally{
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}

	@Override
	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class Main{
	public static void main(String[] args){
		CustomThreadPoolExecutor exe = new CustomThreadPoolExecutor(1,
				1, 1, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
		Future<String> f = exe.submit(new SocketTask<String>());
		f.cancel(true);
	}
}