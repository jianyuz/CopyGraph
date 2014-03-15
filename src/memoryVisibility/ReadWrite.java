package memoryVisibility;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class ReadWrite {
	public static class MutableInteger {
		private final long value;
		public MutableInteger(long value){
			this.value = value;
		}
		public long get() { return value; }
		//public void set(long value) { this.value = value; }
	}
	
	//static final MutableInteger mi = new MutableInteger();
	static final AtomicReference<MutableInteger> mi = new AtomicReference<MutableInteger>();
	
	public static class Reader extends Thread{
		volatile boolean running = true;
		public void run(){
			while(running){
				MutableInteger integer = mi.get();
				if(integer != null)
				System.out.println("read " + integer.get());
				//Thread.yield();
			}
		}
		
		public void terminate(){
			running = false;
		}
	}
	
	public static class Writer extends Thread{
		volatile boolean running = true;
		public void run(){
			Random r = new Random(10);
			while(running){
				long next = r.nextLong();
				System.out.println("write " + next);
				mi.set(new MutableInteger(next));
				
				//Thread.yield();
			}
		}
		
		public void terminate(){
			running = false;
		}
	}
	public static void main(String[] args){
		Writer writer = new Writer();
		
		Reader reader = new Reader();
		
		writer.start();
		
		reader.start();
		
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.terminate();
		reader.terminate();
		
	}
	
}
