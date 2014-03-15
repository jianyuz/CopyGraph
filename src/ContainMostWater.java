
public class ContainMostWater {

	 public int maxArea(int[] height) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        
	        if(height == null || height.length == 0) return 0;
	        
	        //from current bar to count next bar.
	        //for each bar, min(cur, and start) * distance.
	        //until see one bar higher then the start or encounter the end.
	        // if heigher than current, move point to it.

	        int n = height.length;
	        
	        
	        int mVol = 0;
	        for( int i= 0; i< n; i++){
	            for(int j = i; j< n; j++){
	                int vol = Math.min(height[i],height[j]) * (j-i);
	                if(vol > mVol) mVol = vol;
	            }
	            
	        }
	        
	        return mVol;
	    }
	 
	 /**
	  * from end to find the furthest delimiting bar for each bar.
	  * need to run it from left to right
	  * and from right to left.
	  * @param height
	  * @return
	  */
	 public int maxArea1(int[] height) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        
	        if(height == null || height.length == 0) return 0;
	        
	        //from current bar to count next bar.
	        //for each bar, min(cur, and start) * distance.
	        //find the last bar greater or equal to it.

	        int n = height.length;
	        
	        
	        int mVol = 0;
	        for( int i= 0; i< n; i++){
	            for(int j = n-1; j >=0; j--){
	                if(height[j] >= height[i]){
	                    int vol = Math.min(height[i],height[j]) * (j-i);
	                    if(vol > mVol) mVol = vol;
	                    break;
	                }
	            }
	            
	        }
	        
	        for( int i= n-1; i >=0; i--){
	            for(int j = 0; j < n; j++){
	                if(height[j] >= height[i]){
	                    int vol = Math.min(height[i],height[j]) * (i-j);
	                    if(vol > mVol) mVol = vol;
	                    break;
	                }
	            }
	            
	        }
	        
	        return mVol;
	    }
	 
	 // two pointer solutions O(n) performance.
	 //move i and j to the middle.
	 //use the same reasoning.
	 public static int maxArea2(int[] height) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        if(height == null || height.length == 0) return 0;
	        int maxVol = 0;
	        
	        int left = 0;
	        int right = height.length - 1;
	        
	        while(left < right){
	            if(height[left] <= height[right]){
	                int curVol = height[left] * (right - left);
	                if(curVol > maxVol){
	                    maxVol = curVol;
	                }
	                left ++;
	            }else{
	                int curVol = height[right] * (right - left);
	                if(curVol > maxVol){
	                    maxVol = curVol;
	                }
	                right --;
	            }
	        }
	        
	        return maxVol;
	    }
}
