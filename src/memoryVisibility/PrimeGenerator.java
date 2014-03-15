package memoryVisibility;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator implements Runnable{

	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean cancelled = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BigInteger p = BigInteger.ONE;
		while(!cancelled){
			p = p.nextProbablePrime();
			synchronized(this){
				primes.add(p);
			}
		}
		
	}

	public void cancel(){
		cancelled = true;
	}
	public synchronized List<BigInteger> getPrimes(){
		return new ArrayList<BigInteger>(primes);
	}
	
	public static List<BigInteger> secondOfPrimes() throws InterruptedException{
		PrimeGenerator pg = new PrimeGenerator();
		new Thread(pg).start();
		try{
			Thread.sleep(1000);
		}finally{
			pg.cancel();
		}
		return pg.getPrimes();
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		List<BigInteger> lisP = secondOfPrimes();
		System.out.println(lisP.toString());
	}
}
