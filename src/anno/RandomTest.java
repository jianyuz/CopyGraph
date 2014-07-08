package anno;

import java.util.Random;

public class RandomTest {

	private static final Random rnd = new Random();
	// Common but deeply flawed!
	static int random(int n) {
		return Math.abs(rnd.nextInt()) % n;
	}
	
	static int rrandom(int n){
		return rnd.nextInt(n);
	}
	
	public static void main(String[] args){
		int n = 2 * (Integer.MAX_VALUE/3);
		int count = 0;
		for(int i=0; i< 10000000; i++){
			if(rrandom(n) < n/2){
				count ++;
			}
		}
		
		System.out.println("lesser half " + count);
	}
	
	
}
