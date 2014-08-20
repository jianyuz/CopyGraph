package puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;

/**
 * abstract runnable future calls the abstract oncompletion on progress mathods.
 * 
 * @author zhouzhou
 *
 * @param <V>
 */
public abstract class BackgroundTask<V> implements RunnableFuture<V>{
   private final FutureTask<V> computation = new Computation();
	//defaine future task to execute computation.
	private class Computation extends FutureTask<V>{

		public Computation() {
			super(new Callable<V>(){

				@Override
				public V call() throws Exception {
					// TODO Auto-generated method stub
					return BackgroundTask.this.compute();
				}});
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected final void done(){
			GuiExecutor.instance().execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					V value = null;
					Throwable thrown = null;
					boolean cancelled = false;
					
					try{
						value = get();}
					catch(ExecutionException e){
						thrown = e.getCause();
					} catch(CancellationException e){
						cancelled = true;
					}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						onCompletion(value, thrown, cancelled);
					}
				}
				
			});
		}
		
	}
	protected void setProgress(final int current, final int max){
		GuiExecutor.instance().equals(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				onProgress(current, max);
			}
			
		});
	}
	
	protected void onProgress(int current, int max){}
	
	protected abstract V compute();
	
	protected void onCompletion(V result, Throwable t, boolean cancelled){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		computation.run();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return computation.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return computation.isCancelled();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return computation.isDone();
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return computation.get();
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return computation.get(timeout, unit);
	}

	
	public static void main(String[] args){
		final ExecutorService backgroundExec = Executors.newFixedThreadPool(1);
		JButton startButton = new JButton();
		final JButton cancelButton = new JButton();
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				class CancelListener implements ActionListener{
					BackgroundTask<?> task;

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(task != null)
							task.cancel(true);
					} 
				}
				final CancelListener listener = new CancelListener();
				listener.task = new BackgroundTask<Void>(){

					@Override
					protected Void compute() {
						// TODO Auto-generated method stub
						while(moreWork() && !isCancelled()){
							doWork();
						}
						return null;
					}
					
					private boolean moreWork(){
						return true;
					}
					
					private void doWork(){
						
					}
					
					@Override
					protected void onCompletion(Void v, Throwable t, boolean cancelled){
						cancelButton.removeActionListener(listener);
						cancelButton.setEnabled(false);
					}
					
				};
				
				cancelButton.addActionListener(listener);
				
				backgroundExec.execute(listener.task);
			}});
	}
}
