package memoryVisibility;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
	FutureTask<ProdInfo> futureTask = new FutureTask<ProdInfo>(new Callable<ProdInfo>(){

		@Override
		public ProdInfo call() throws DataLoadException {
			// TODO Auto-generated method stub
			return null;
		}
		
	});
	
	Thread thread = new Thread(futureTask);
	
	public void start(){
		thread.start();
	}
	
	public ProdInfo retrieveResult() throws DataLoadException{
		ProdInfo res = null;
		try {
			res = futureTask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			Throwable c = e.getCause();
			if(c instanceof DataLoadException){
				throw (DataLoadException)c;
			}else{
				throw launderThrowable(c);
			}
		}
		return res;
	}
	
	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException)
		return (RuntimeException) t;
		else if (t instanceof Error)
		throw (Error) t;
		else
		throw new IllegalStateException("Not unchecked", t);
		}

}

class ProdInfo{
	
}

class DataLoadException extends Exception{
	
}