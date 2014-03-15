
public class IsNumPalindrome {

	public boolean isPalindrome(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(x < 0) return false;//minor number always false.
        if(x == 0) return true;//0 always true.
        
        x = Math.abs(x);//convert to positive.
        
        int numDigits = 0;
        
        int a = x;
        
        while(a > 0){
            a = a/10;
            numDigits ++;
        }//calculate number of digits.
        
        
        //peel highest digit off 
        //and peel the lowest digit off 
        //same time.
        //lowest digit 
        //%
        //highest digit
        a = x;
        while(a > 0){
            if(numDigits == 1) return true;//terminate condition for 1 digit number.
            int lowDigit = a % 10;//get low digit
            a = a/10; //remove low digit
            
            int highDigit = 0;//get high digits.
            if(a < 10){
                highDigit = a;
                if(highDigit != lowDigit) return false;
                else return true;
            }else{
                int base = (int)Math.pow(10, numDigits-2);
                highDigit = a / base;
                if(highDigit != lowDigit) return false;
                a = a % base; //update a and numDigits
                numDigits -= 2;
            }
            
        }
        return true;
        
    }
}
