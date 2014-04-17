package memoryVisibility;

/**
 * 110 
 * 101
 * 
 * 100
 * 011
 * 
 * 1100
 * 1011
 * 
 * power of 2
 * n & n-1 = 0;
 * n > 0
 * @author zhouzhou
 *
 */
public class NumberWithSameOnes {

	public static void main(String[] args){
		System.out.println(countOnes(4));
		System.out.println(nextSmallestNumber(5));
		System.out.println(next1(5));
		System.out.println(nextSmallestNumber(-1));
		System.out.println(nextBiggestNumber(5));
		System.out.println(countOnes(31^14)); //different bits.
	}
	
	public static int nextSmallestNumber(int input){
		int count = countOnes(input);
		if(input == -1 || count == 0) return -1;
	    int tmp = input;
	    do{
	    	tmp = tmp + 1;
	    }while(countOnes1(tmp)!= count && tmp <= Integer.MAX_VALUE);
	    
	    if(countOnes(tmp) == count)
	    	return tmp;
	    else{
	    	return -1;
	    }
	}
	
	public static int next1(int input){
		
		int orig = input;
		int bits = 0;
		while((input & 1) != 1  ){
			input >>=1;
	    	bits ++;
		}
		
		while((input & 1) != 0){
			input >>=1;
			bits ++;
		}
		
		input |= 1;
		input = input << bits;
		
		int addOnes = countOnes(orig) - countOnes(input);
		for(int i=0; i< addOnes; i++){
			input |= 1<<i;
		}
		return input;
	}
	
	public static int nextBiggestNumber(int input){
		int count = countOnes(input);
		if(input == -1 || count == 0) return -1;
		int tmp = input;
		do{
			tmp = tmp -1;
		}while(countOnes1(tmp)!= count && tmp >= Integer.MIN_VALUE);
		if(countOnes(tmp) == count)
			return tmp;
		else{
			return -1;
		}
	}
	
	public static int countOnes(int input){
		int res = 0;
		
		int tmp = input;
		
		while(tmp > 0){
			if(tmp %2 == 1) res++;
			tmp = tmp >> 1;
		}
		
		return res;
	}
	
	public static int countOnes1(int input){
		int res = 0;
		int tmp = input;
		for(int i=0; i< 32; i++){
			if((tmp & 1) == 1) res ++;
			tmp = tmp >> 1;
		}
		return res;
	}
}
