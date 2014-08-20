
public class BiggestPrimeInArray {
	
	public static void main(String[] args){
		int[] a = new int[] {2,5, 8, 10, 15, 28, 24, 31, 7};
		System.out.println("biggest prime: " + largestPrime(a));
		System.out.println("biggest prime: " + largestPrime1(a));
	}
	/**
	 * find the biggest prime number in Array.
	 * O(range of sqrt(N) * n)
	 * 
	 */
	
	public static int largestPrime(int [] a){
		if(a == null || a.length == 0) return 0;
		int maxPrime = Integer.MIN_VALUE;
		for(int i=0; i< a.length; i++){
			if(isPrime(a[i]) && a[i] > maxPrime ){
				maxPrime = a[i];
			}
		}
		return maxPrime;
	}
	
	private static boolean isPrime(int a){
		for(int i= 2; i< Math.sqrt(a) + 1; i++){
			if(a %i == 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * progressively remove non prime only primes are left.
	 * but the bases are the primes.
	 * sum of n/p p <= sqrtn 
	 * 
	 * (n loglogn)
	 * @param a
	 * @return
	 */
	public static int largestPrime1(int[] a){
		if(a == null || a.length == 0) return 0;
		int max = Integer.MIN_VALUE;
		for(int i=0; i< a.length; i++){
			if(a[i] > max){
				max = a[i];
			}
		}
		//find max number in the array.
		
		
		//find prime number in the range.
		boolean[] isPrime = new boolean[max +1];
		
		for(int i=1; i<=max; i++)
			isPrime[i] = true;
		
		for(int i = 2; i<= Math.sqrt(max); i++){
			if(isPrime[i]){
				for(int j = i*i; j <= max ; j=j+i) //then it is square is not prime a times 3 a times 4 .. are not prime.
				isPrime[j] = false;
			}
		}
		
		
		int maxPrime = Integer.MIN_VALUE;
		for(int i = 0; i< a.length; i++){
			if(isPrime[a[i]] && a[i] > maxPrime){
				maxPrime = a[i];
			}
		}
		return maxPrime;
	}

}
