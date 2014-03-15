package com.z2.LRUCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class StringReverser {

	ExecutorService executor = Executors.newFixedThreadPool(1);
	SlowStringReverser reverser = new SlowStringReverser();

	void doReverse(final String target) throws InterruptedException{
		FutureTask<String> future = new FutureTask<String>(
				new Callable<String>(){

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						return reverser.reverseString(target);
					}
					
				}
				
				) ;
		
		executor.execute(future);
		
		while(!future.isDone()){
			System.out.println("Task not yet completed.");
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException ie)
            {
                System.out.println("Will check after 1/2 sec.");
            }
            
		}
		
		try{
			System.out.println("result " + future.get());
		}catch(ExecutionException ex){
			
		}
		executor.shutdown();
		return;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringReverser msr = new StringReverser();
        try
        {
            msr.doReverse("foobar");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

	}

}

class SlowStringReverser
{
    StringBuffer orgString;

    StringBuffer reversedString;

    SlowStringReverser(String orgStr)
    {
        orgString = new StringBuffer(orgStr);
    }

    SlowStringReverser()
    {

    }

    public String reverseString(String str)
    {
        orgString = new StringBuffer(str);
        reversedString = new StringBuffer();
        for (int i = (orgString.length() - 1); i >= 0; i--)
        {

            reversedString.append(orgString.charAt(i));
            System.out.println("Reversing one character per second."
                    + reversedString);
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ie)
            {
            }
        }
        return reversedString.toString();
    }
}
