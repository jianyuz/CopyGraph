package memoryVisibility;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CacheTest2 {
	private ConcurrentHashMap<String, Future<String>> cache = new ConcurrentHashMap<String, Future<String>>();
	private final Computable<String, String> service;
	
	public CacheTest2(LengthService service){
		this.service = service;
	}
	
	public String retrieveValue(final String key) throws InterruptedException{
		Future<String> future = cache.get(key);
		
		if(future == null){
			FutureTask<String> newFuture = new FutureTask<String>(new Callable<String>(){
				@Override
				public String call() throws Exception {
					return CacheTest2.this.service.compute(key);
				}
				
			});
			future = newFuture;
			cache.put(key,  future);
			newFuture.run();
		}
		
		String value = null;
		try {
			value = future.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
		return value;
	}
}
