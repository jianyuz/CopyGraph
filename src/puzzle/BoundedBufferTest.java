package puzzle;

import anno.Test;
import junit.framework.TestCase;

public class BoundedBufferTest extends TestCase{

	public BoundedBufferTest(){
		
	}
	
	@Test
	public void testIsEmptyWhenConstructed() {
		BoundBuffer<Integer> bb = new BoundBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

    @Test
	public void testIsFullAfterPuts() throws InterruptedException {
		BoundBuffer<Integer> bb = new BoundBuffer<Integer>(10);
		for (int i = 0; i < 10; i++)
			bb.put(i);
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
    
    @Test
    public void testTakeWhenEmpty() throws InterruptedException{
    	final BoundBuffer<Integer> bb = new BoundBuffer<Integer>(10);
    	Thread t = new Thread(){
    		@Override
    		public void run(){
    			try {
					bb.take();
					fail();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
    			
    		}
    	};
    	
    	try{
    	t.start();
    	Thread.sleep(5000);
    	t.interrupt();
    	t.join(5000);
    	assertFalse(t.isAlive());//t has died.
    	}catch(Exception e)
    	{
    		fail();
    	}
    }
}