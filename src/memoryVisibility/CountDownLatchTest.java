package memoryVisibility;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException{
		int numOfThreads = 3;
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(numOfThreads);
		
		for(int i=0; i<numOfThreads; i++){
			Thread t = new Thread(){

				@Override
				public void run() {
					try {
						startGate.await();
						System.out.println(Thread.currentThread().getName() + " started! ");
						for(int j=0; j< 200000; j++){
							//do nothing.
						}
						endGate.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						System.out.println("Iam done!");
					}
					
				}
			
				
			};
			
			t.start();
		}
		startGate.countDown();
		endGate.await();
		System.out.println("all done");
		
		
		
	}

}
