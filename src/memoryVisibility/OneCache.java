package memoryVisibility;

import java.math.BigInteger;
import java.util.Arrays;

public class OneCache {
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public OneCache(BigInteger number, BigInteger[] factors){
		this.lastFactors = Arrays.copyOf(factors, factors.length);
		this.lastNumber = number;
	}
	
	public BigInteger[] getFactors(BigInteger number){
		if(lastNumber == null || !lastNumber.equals(number)){
			return null;
		}else{
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}

class Holder {
	private int n;

	public Holder(int n) {
		this.n = n;
	}

	public void assertSanity() {
		if (n != n) //first read may be different from next read.
			throw new AssertionError("This statement is false.");
	}
}