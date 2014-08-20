package puzzle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import anno.Test;
import junit.framework.TestCase;

public class TestingThreadFactory extends TestCase implements ThreadFactory {

	public final AtomicInteger count = new AtomicInteger(0);
	private final ThreadFactory factory = Executors.defaultThreadFactory();
	@Override
	public Thread newThread(Runnable r) {
		count.incrementAndGet();
		return factory.newThread(r);
	}

	@Test
	public static void testPoolExpansion() throws InterruptedException{
		int MAX = 10;
		ThreadFactory factory = new TestingThreadFactory();
		ExecutorService exec = Executors.newFixedThreadPool(MAX, factory);
		
		
		for(int i=0; i< 10 * MAX; i++){
			exec.submit(new Runnable(){

				@Override
				public void run() {
					try{
						Thread.sleep(Long.MAX_VALUE);
					}catch(InterruptedException e){
						Thread.currentThread().interrupt();
					}
				}});
		}
		
		for(int i=0; i< 20 && ((TestingThreadFactory)factory).count.get() < MAX; i++){
			Thread.sleep(100);
		}
		
		assertEquals( MAX, ((TestingThreadFactory)factory).count.get());
		exec.shutdownNow();//must shutdown immediately.
	}
}
