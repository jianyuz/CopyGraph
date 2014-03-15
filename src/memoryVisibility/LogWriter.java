package memoryVisibility;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriter {

	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	
	public LogWriter(Writer writer){
		this.queue = new LinkedBlockingQueue<String> (100);
		this.logger = new LoggerThread();
	}
	
	public void start(){
		this.logger.start();
	}
	
	public void log(String msg) throws InterruptedException{
			this.queue.put(msg);
	}
	
	class LoggerThread extends Thread{
		private PrintWriter writer;
		
		public LoggerThread(){
			this.writer = new PrintWriter(System.out);
		}
		
		public void run() {

			try {
				while (true) {
					String msg = LogWriter.this.queue.take();
					writer.write(msg);
				}
			} catch (InterruptedException e) {
			} finally {
				writer.close();
			}

		}
	}
	
}
