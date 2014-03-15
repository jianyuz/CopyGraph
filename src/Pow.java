
public class Pow {

	/**
	 * the solution is too slow for large test.
	 * @param x
	 * @param n
	 * @return
	 */
	public double pow1(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(n == 0) return 1d;
        
        boolean isNeg = (n < 0)? true: false;
        if(isNeg){
            n = -1 * n;
        }
        
        double mul=x;
        for(int i=2; i<=n; i++){
            mul *= x;
        }
        
        return isNeg? (1d/mul): mul;
    }
	
	
	/**
	 * lgn performance.
	 * @param x
	 * @param n
	 * @return
	 */
	public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //lgn performance, try to keep on dividing it by 2.
        
        if(n == 0) return 1d;
        if(n == 1) return x;
        
        boolean isNeg = (n < 0)? true: false;
        if(isNeg){
            n = -1 * n;
        }
        
        double res = doPow(x, n);
        
        return isNeg? (1d/res): res;
    }
    
    public double doPow(double x, int n){
        if(n == 0) return 1d;
        if(n == 1) return x;
        
        double residual = (n % 2 == 0)? 1d : x; 
        double half = doPow(x, n/2); //don't compute this twice.
        return half * half  * residual;
    }
    
    /**
     * try iterative solution.
     * 
     * a^b = a^(b/2) * a^(b/2) -- b is even

	 * a^b = a^(b/2) * a^(b/2) * a -- b is odd
     */
    
    public double pow3(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //lgn performance, try to keep on dividing it by 2.
        
        if(n == 0) return 1d;
        if(n == 1) return x;
        
        int m = Math.abs(n);
        
    
        double mul = 1d;
        
        while(m > 0){
            if((m & 1) == 1){
            //odd number
                mul *= x; //multi one additional x
            }    
            m >>= 1;
            x *= x;
        }
        
        return (n < 0) ? (1d/mul): mul;
    }
}
