
public class ReverseInt {
	public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        boolean neg = false;
        if(x < 0){//when mark negative also reverse the number.
           x = -x;
           neg = true;
        }
        
        int a = x;
        
        int res = 0;
        
        while(a > 0){
            res = res * 10; //multiply by 10 first.
            int digit = a % 10;
            a = a/10;
            res += digit;
        }
        
        return neg?-res:res; //afterward put negative sign back.
        
    }
}
