package memoryVisibility;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriterWithStop {

	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	private boolean isShutDown;
	private int count;
	
	public LogWriterWithStop(Writer writer){
		this.queue = new LinkedBlockingQueue<String> (100);
		this.logger = new LoggerThread();
		isShutDown = false;
		count = 0;
	}
	
	public void start(){
		this.logger.start();
	}
	
	public void stop(){
		synchronized(this){
			isShutDown = true;
		}
	}
	
	public void log(String msg) throws InterruptedException{
		synchronized (this) {
			if (!isShutDown) {

				count++;
			} else {
				throw new IllegalStateException();
			}
		}
		this.queue.put(msg);
	}
	
	private class LoggerThread extends Thread{
		private PrintWriter writer;
		
		public LoggerThread(){
			this.writer = new PrintWriter(System.out);
		}
		
		public void run() {

			try {
				while (true) {
					synchronized (this) {
						if (isShutDown && count == 0) {
							break;
						}
					}
					String msg = queue.take();
					synchronized (this) {
						count--;
					}
					writer.write(msg);
				}
			} catch (InterruptedException e) {
			} finally {
				writer.close();
			}

		}
	}
	
}
