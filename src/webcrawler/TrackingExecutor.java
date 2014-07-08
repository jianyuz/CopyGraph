package webcrawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService{

	private final ExecutorService exec;
	
	public TrackingExecutor(ExecutorService e){
		this.exec = e;
	}
	
	@Override
	public void shutdown() {
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return exec.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return exec.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return exec.awaitTermination(timeout, unit);
	}

	private final Set<Runnable> taskCancelled = Collections.synchronizedSet(new HashSet<Runnable>());
	
	@Override
	/**
	 * task may have been completed in cancelled queue.
	 * so the task needs to be idempotent.
	 */
	final Throwable t;
	
	public void execute(final Runnable command) {
		
		
		//wrap around the runnable
		exec.execute(new Runnable(){
			
			@Override
			public void run() {
				try{
					while(!Thread.currentThread().isInterrupted())
						command.run();
				}catch(Throwable e){
					//untrusted code
					t = e;
				}finally{
					if(isShutdown() && Thread.currentThread().isInterrupted()){
						taskCancelled.add(command);
					}
					threadExited(this, t);
				}
				
			}
			
		});
		
		
	}
	
	public void threadExited(Runnable r, Throwable t){
		
	}
	
	public List<Runnable> getTaskCancelled(){
		if(!isTerminated()){
			throw new IllegalStateException("should be terminated");
		}
		return new ArrayList<Runnable>(taskCancelled);
	}

}
