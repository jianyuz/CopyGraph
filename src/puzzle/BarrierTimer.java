package puzzle;

public class BarrierTimer implements Runnable{

	private long startTime, endTime;
	private boolean started;
	
	public synchronized void clear(){
		started = false;
	}
	
	public synchronized void run(){
		long t = System.nanoTime();
		if(!started){
			started = true;
			startTime = t;
		}else{
			endTime = t;
		}
	}
	
	public synchronized long getTime(){
		return endTime - startTime;
	}
}
