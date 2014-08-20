package puzzle;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

public class PutTakeTest extends TestCase {
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	
	private final CyclicBarrier barrier;
	private final BoundBuffer<Integer> bb;
	private final int nTrials, nPairs;
	private final BarrierTimer timer;
	
	public static void main(String[] args){
		new PutTakeTest(10, 10, 100000).test();
		pool.shutdown();
	}
	
	public PutTakeTest(int capacity, int npairs, int ntrials){
		this.bb = new BoundBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.timer = new BarrierTimer();
		this.barrier = new CyclicBarrier(2 * this.nPairs + 1, timer );
		//concumer producer plus main.
	}
	
	void test() {
		timer.clear();
		try{
		for(int i=0; i< nPairs ; i++){
			pool.execute(new Producer());
			pool.execute(new Consumer());
			
		}
		System.out.println("parties waiting "  + barrier.getNumberWaiting());
		barrier.await();
		barrier.await();
		System.out.println("performance " + (timer.getTime() / (this.nTrials * this.nPairs)));
		assertEquals(putSum.get(), takeSum.get());
		System.out.println("done");
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	
	class Producer implements Runnable{

		@Override
		public void run() {
			try {
				int seed = (this.hashCode() ^ (int) System.nanoTime());
				int sum = 0;
				barrier.await();
				for (int i = 0; i < nTrials; i++) {
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	
	}
	class Consumer implements Runnable{

		@Override
		public void run() {
			try {
				barrier.await();
				int sum = 0;
				for (int i = 0; i < nTrials; i++) {
					sum += bb.take();
				}
				takeSum.addAndGet(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException();
			}
			
		}
		
	}
	
	
	static int xorShift(int y) {
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}
}
