import java.math.BigInteger;


public class Sqrt {

	public static void main(String[] args){
		System.out.println(sqrt(2147395599));
	}
	
	public static int sqrt(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(x == 0) return 0;
        if(x == 1) return 1;
        
        int low = 1;
        int high = x/2+1;//for the case of 
        int mid;
        BigInteger midSquare;
        //find maximum element that square of it smaller or equals to x.
        //get rid of half of the array that value greater than x.
        while(low < high){ 
            mid = low + (high-low)/2 +1; //avoid getting into infinite loop.
            BigInteger midBig = BigInteger.valueOf(mid);
            midSquare = midBig.multiply(midBig);
            
            if( midSquare.compareTo(BigInteger.valueOf(x)) > 0){
                high = mid - 1; //maximum value is in lower half, exluding mid.
            }else{
                low = mid; //maxiumum value is in upper half including mid.
            }
        }
        return low; //maximum value pointed because high is 1 smaller than mid
        //mid square > x.
    }
	
	/**
	 * newton's method.
	 * use f(x) = x2 - n = 0
	 * f(x) derivative 2x
	 * 
	 * Xn+1= xn - F(x)/x2;
	 * 
	 * xn+1 = (xn + n/xn)/2
	 * http://blog.punkid.org/2008/02/28/compute-the-square-root-via-newtons-iteration/
	 */
	
	public int sqrt1(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(x == 0) return 0;
        if(x == 1) return 1;
        
        float eps = 0.001f;
        
        float guess = 1;
        float last;
        
        do{
            last = guess;
            guess = (guess + x/guess)/2;
        }while(Math.abs(last - guess) > eps); //make sure we don't have much room to improve.
        
        int res = (int) guess;
        if(res * res > x){
            res --;
        }
        return res;
    }
}
