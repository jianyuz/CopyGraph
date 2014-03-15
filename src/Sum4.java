import java.util.ArrayList;
import java.util.Arrays;

/**
 * similiar to sum3 except that add one more loop
 * O(n3)
 * @author zhouzhou
 *
 */
public class Sum4 {

	
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        if(num == null || num.length < 4) return res;
        
        int len = num.length;
        
        Arrays.sort(num);
        
        for(int i=0; i< len - 3; i++){
            int diff3 = target - num[i];
            if(i > 0 && num[i] == num[i-1]) continue;
            
            for(int j = i+1; j< len -2; j++){
                int diff2 = diff3 - num[j];
                if(j > i+1 && num[j] == num[j-1]) continue;
                
                //two pointers
                int m = j+1;
                int n = len-1;
                
                while(m < n){
                    int sum = num[m]+num[n];
                    if(sum < diff2){
                        m ++;
                    }else if(sum > diff2){
                        n--;
                    }else{
                        ArrayList<Integer> partRes = new ArrayList<Integer>();
                        partRes.add(num[i]);
                        partRes.add(num[j]);
                        partRes.add(num[m]);
                        partRes.add(num[n]);
                        res.add(partRes);
                        do{
                            m++;
                        }while(m < n && num[m] == num[m-1]);
                        
                        do{
                            n--;
                        }while(m < n && num[n] == num[n+1]);
                    }
                }
            }
        }
        return res;
    }
}
