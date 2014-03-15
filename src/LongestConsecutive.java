import java.util.BitSet;
import java.util.HashSet;

/**
 * find longest consecutive sequence in int array.
 * @author zhouzhou
 *
 */

public class LongestConsecutive {
	
	public static void main(String[] args){
		int[] input = new int[]{100, 4, 6, 3, 2, 1};
		System.out.println(longestConsecutive(input));
		input = new int[]{2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645};
		System.out.println(longestConsecutive1(input));
		//System.out.println(longestConsecutive(input));
	}

	/**
	 * not good, doesn't suite with long range ints.
	 * @param num
	 * @return
	 */
	public static int longestConsecutive(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
 
        if(num == null || num.length == 0) return 0;
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i= 0; i< num.length; i++){
            if(num[i] > max){
                max = num[i];
            }
            if(num[i] < min){
                min = num[i];
            }
        }
        
        int range = max - min +1;
        BitSet bs = new BitSet(range);
        System.out.println("range " + range);
        
        for(int i=0; i< num.length; i++){
            bs.set(num[i] - min);
        }
        
        int maxCount = 0;
        int start = -1;
        int i = 0;
        
        System.out.println("bs size " + bs.size());
        while(i < bs.size() && !bs.get(i)){
            i++;
        }
        if(i < bs.size()){
            start = i;
            maxCount = 1;
        }
        
        //System.out.println("start " + start + " max " + maxCount);
        for(int j=i+1; j< bs.size() && j >=1; j++){
            if(bs.get(j) != bs.get(j-1)){
                if(bs.get(j) && start == -1){
                    start = j;
                }
                if(!bs.get(j) && start != -1){
                    if(maxCount < j - start){
                        maxCount = j - start;
                    }
                    start = -1;
                }
                //System.out.println("start " + start + " max " + maxCount);
                
            }
        }
        return maxCount;
    }
	
	public static int longestConsecutive1(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
 
        if(num == null || num.length == 0) return 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0; i< num.length; i++){
            set.add(num[i]);
        }
        
        int maxCount = 1;
        int start, end;
        int number;
        while(!set.isEmpty()){
            number = set.iterator().next();
            start = end = number;
            set.remove(number);
            while(set.contains(start -1)){
                set.remove(start -1);
                start --;
            }
            
            while(set.contains(end +1)){
                set.remove(end + 1);
                end ++;
            }
            if(maxCount < end - start + 1){
                maxCount = end - start + 1;
            }
        }
        
        
        return maxCount;
    }
}
