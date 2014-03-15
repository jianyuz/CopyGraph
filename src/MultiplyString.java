/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.


 * @author zhouzhou
 *
 */
public class MultiplyString {
	
	public static void main(String[] args){
		System.out.println(multiply("0", "0"));
	}
	public static String multiply(String num1, String num2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int size = 3;//can't be too big overflow.
        int base = (int)Math.pow(10, size);//math. pow return double.
        
        int l1 = num1.length();
        int l2 = num2.length();
        
        //calculate size of the num array for num1 and num2.
        //init the array.
        //exact division version with modulo
        //array size + 1.
        int arraySize1 = l1/size + ((l1%size > 0)? 1: 0);
        int arraySize2 = l2/size + ((l2%size > 0)? 1: 0);
        
        int[] numArray1 = new int[arraySize1];
        int[] numArray2 = new int[arraySize2];
        
        parseNumPart(num1, numArray1, l1, size);
        parseNumPart(num2, numArray2, l2, size);
        
        int[] res = new int[numArray1.length + numArray2.length]; //highest order.
        
        //multiply the num parts.
        
        for(int i=0; i< numArray1.length; i++){
            for(int j=0; j< numArray2.length; j++){
                int mul = numArray1[i] * numArray2[j];
                res[i +j] += mul;
            }
        }
        
        //calculate overflow.
        int carry = 0;
        for(int i=0; i< res.length; i++){
            res[i] += carry;
            
            if(res[i] > base){
                carry = res[i] / base; //not minus base.
                res[i] -= carry * base;
            }else{
                carry = 0;
            }
        }
        
        if(carry > 0){
            //size increases. need a larger array.
            int[] res1 = new int[res.length +1];
            System.arraycopy(res, 0, res1, 0, res.length); //don't miss the fourth param.
            res1[res.length] = carry;
            res = res1;
        }
        
        StringBuilder sb = new StringBuilder();
        int count = res.length -1;
        while(count >0 && res[count] == 0) count --; //remove leading 0. keep the last 0.
        //convert the number array back to String
        for(int i= count; i>=0; i--){
            if(i == count ){
                sb.append(res[i]); //don't pad 0 for first number.
                continue;
            }
            String part = "" + res[i];
            while(part.length() < size){ //pad to size.
                //pad with 0 at front.
                part = "0" + part;
            }
            sb.append(part);
            
        }
        
        return sb.toString();
    }
    
	/**
	 * parse num part.
	 * from higher order to lower order.
	 * use substring to get number segment.
	 * @param num
	 * @param numArray
	 * @param len
	 * @param size
	 */
    public static void parseNumPart(String num, int[] numArray, int len, int size){
        String segment = null;
        int count = 0;
        for(int i = 0; i< numArray.length; i++){
            int start = Math.max(0, len- (count+1) * size); //parse segment from end to start.
            int end = len - count * size;
            segment = num.substring(start, end);
            numArray[i] = Integer.parseInt(segment);
            count ++;
        }    
    }
    
    /**
     * a better solution
     * no need to parse the number segment.
     * multiply them digit by digit.
     * 
     * need to handle the leading 0 issue.
     * and possible it is 0 so we need to complement it.
     * 
     * @param a
     * @param b
     * @return
     */
    public String multiply1(String a, String b) {
        int m = a.length();
        int n = b.length();
        
        int[] res = new int[m + n];
        
        for(int i= m-1; i>=0; i--){
            for(int j= n-1; j>=0; j--){
                res[i+j + 1] +=  (a.charAt(i) - '0') * (b.charAt(j) - '0');
            }
        }
        
        int carry = 0;
        for(int i=m+n-1; i>=0; i--){
            res[i] += carry;
            carry = res[i] /10;
            res[i] = res[i] % 10;
        }
        
        String ans = "";
        for(int i=0; i< m+n; i++){
            if(res[i] != 0 || !ans.isEmpty()) ans += res[i];
        }
    
        return ans.isEmpty()? "0": ans;
    }
    
    /**
     * best answer 
     * calculate carry together with res calculation
     * time complexity m*n.
     * @param a
     * @param b
     * @return
     */
    public String multiply2(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[] rl = new int[m + n];
        for(int i = n - 1; i >= 0; i--){
            int carry = 0;
            for(int j = m - 1; j >= 0; j--){
                int tmp = (b.charAt(i) - '0') * (a.charAt(j) - '0') + carry + rl[i + j + 1];
                rl[i + j + 1] = tmp % 10;
                carry = tmp / 10;
            }
            rl[i] = carry; //after all j accumulation
            //j == 0, carry it over to rl[i];
        }
        String ans = "";
        for(int x : rl)
            if(x != 0 || !ans.isEmpty()) ans += x;
        return (ans.isEmpty()) ? "0" : ans;
    }
}
