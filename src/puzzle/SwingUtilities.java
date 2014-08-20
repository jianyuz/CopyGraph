package puzzle;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class SwingUtilities {
	private static final ExecutorService  exec = Executors.newSingleThreadExecutor(new SwingThreadFactory() );

	private static volatile Thread swingThread;
	
	private static class SwingThreadFactory implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			swingThread = new Thread(r);
			return swingThread;
		}
		
	}
	
	public static boolean isEventDispatchThread(){
		return Thread.currentThread() == swingThread;
	}
	
	public static void invokeLater(Runnable r){
		exec.execute(r);
	}
	
	public static void invokeAndWait(Runnable c) throws InvocationTargetException, InterruptedException{
		Future<?> f = exec.submit(c);
		try{
			f.get();
		}catch(ExecutionException e){
			throw new InvocationTargetException(e);
		}
		
	}
}
