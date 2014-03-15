package memoryVisibility;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CacheTest3 {

	private ConcurrentHashMap<String, Future<String>> cache = new ConcurrentHashMap<String, Future<String>>();
	private final Computable<String, String> service;
	
	public CacheTest3(LengthService service){
		this.service = service;
	}
	
	public String retrieveValue(final String key) throws InterruptedException{
		while (true) {//add loop since it may be cancelled.
			Future<String> future = cache.get(key);
			if (future == null) {
				FutureTask<String> newFuture = new FutureTask<String>(
						new Callable<String>() {
							@Override
							public String call() throws Exception {
								return CacheTest3.this.service.compute(key);
							}

						});

				future = cache.putIfAbsent(key, newFuture);
				if (future == null) {
					future = newFuture;
					newFuture.run();
				}
			}

			String value = null;
			try {
				value = future.get();
				return value;
			} catch (CancellationException ce) {
				cache.remove(key); // avoid cache pollution.
			} catch (ExecutionException e) {
				// process exception
			}
		}
	}
}
