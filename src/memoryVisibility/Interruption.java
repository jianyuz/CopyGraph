package memoryVisibility;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Interruption {
	public static void main(String[] args){
		new Thread(new TaskRunnable()).start();
	}
	public static class TaskRunnable implements Runnable {
		BlockingQueue<Task> queue = new LinkedBlockingQueue<Task>();

		public void run() {
			try {
				processTask(queue.take());
			} catch (InterruptedException e) {
				// restore interrupted status
				Thread.currentThread().interrupt();
			}
		}
		
		void processTask(Task task){
			System.out.println("do task");
		}
	}
	
	

}
