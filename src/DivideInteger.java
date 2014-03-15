
public class DivideInteger {

	public static void main(String[] args){
		System.out.println(divide(-2147483648, -3));
	}
	public static int divide(int dividend, int divisor) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(dividend == 0) return 0;
        if(divisor == 0) return Integer.MAX_VALUE;
        
        //special boundary condition.
        
        //calculate the sign.
        //both of them can be negative.
        boolean positive = true;
        if(dividend < 0) positive = !positive;
        if(divisor < 0) positive = !positive;
        
        //convert the number into positive numers
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        //can't use multiplication,
        //count on bit operation >> and << double and divide
        //plus minus.
        
        int res = 0;
        while(a >= b){
            int pos = 0;
            while( (b << (pos + 1)) <=a) pos ++; //found highest multiple of b's we can substract.
            a -= (b << pos);
            res += 1 << pos; //add how many b we have substracted.
        }
        
        return positive ? res : -1 * res;
    }
	
	//here calculate the highest multiple's of b once.
	//descend it gradually.
	
	public int divide1(int dividend, int divisor) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(dividend == 0) return 0;
        if(divisor == 0) return Integer.MAX_VALUE;
        
        //special boundary condition.
        
        //calculate the sign.
        //both of them can be negative.
        boolean positive = true;
        if(dividend < 0) positive = !positive;
        if(divisor < 0) positive = !positive;
        
        //convert the number into positive numers
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        //can't use multiplication,
        //count on bit operation >> and << double and divide
        //plus minus.
        int pos = 0;
        while( (b << (pos + 1)) <=a) pos ++; //found highest multiple of b's we can substract.

        int res = 0;
        while(a >= b){
            while(a >= (b << pos)){
                a -= (b << pos);
                res += 1 << pos; //add how many b we have substracted.
            }
            pos --;
        }
        
        return positive ? res : -1 * res;
    }
}
