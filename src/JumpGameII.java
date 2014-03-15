
public class JumpGameII {

	/**
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

	 * the following solution doesn't pass the large test set.
	 * @param A
	 * @return
	 */
	public int jump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        return btJump(A.length -1, A);
    }
    
    /**
     * backtrack style dynamic programming.
     * find all the elements that can jump to the current index.
     * find the minJump among them.
     */
    public int btJump(int index, int[] A){
        if(index == 0){
            return 0;
        }
        
        int minJump = Integer.MAX_VALUE;
        for(int i= index - 1; i>=0; i--){
            if(i + A[i] >= index){
                minJump = Math.min(minJump, btJump(i, A) + 1);
            }
        }
        return minJump;
    }
    
    /**
     * pure dynamic programming.
     * didn't pass large dataset testing either.
     */
    
    public int jump1(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        //min num of steps to reach position i.
        int[] minJump = new int[A.length];
        minJump[0] = 0;
        
        for(int i=1; i< A.length; i++){
            int iMinJump = Integer.MAX_VALUE;
            for(int j=0; j< i; j++){
                if(j + A[j] >= i){
                    iMinJump = Math.min(iMinJump, minJump[j] + 1);
                }
            }
            minJump[i] = iMinJump;
        }
        
        return minJump[A.length -1];
    }
    
    /**
     * still doesn't pass the large dataset testing.
     * this approach uses progressiv minJump step calculation.
     * @param A
     * @return
     */
    public int jump2(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        //min num of steps to reach position i.
        int[] minJump = new int[A.length];
        minJump[0] = 0;
        for(int i = 1; i< A.length; i++){
            minJump[i] = Integer.MAX_VALUE;
        }
        
        for(int i=0; i< A.length; i++){
            int range = Math.min(i + A[i], A.length -1); //make sure it doesn't go out of boundary.
            
            for(int j= i+1 ; j<= range; j++){
                if(minJump[i] +1 < minJump[j]){
                    minJump[j] = minJump[i] + 1;
                }
            }
        }
        
        return minJump[A.length -1];
    }
    
    /**
     * Greedy algorithm solution.
     * O(N) performance.
     * @param A
     * @return
     */
    public int jump3(int[] A) {     
        int currentRange = 0, nextRange = 0, steps = 0;
        
        //use greedy algorithm.
        //find the max range can reach from existing range.
        //if go beyond range boundary, add one step to answer.
        
        for(int i = 0; i< A.length; i++){
            if( i > currentRange){
                currentRange = nextRange;
                steps ++;
            }   
            
            nextRange = Math.max(nextRange, i + A[i]);
        }
        return steps;
    }
    
}
