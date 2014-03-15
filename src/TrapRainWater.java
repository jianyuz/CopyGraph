import java.util.Stack;


public class TrapRainWater {
	public int trap(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if (A== null || A.length == 0) return 0;
        
        Stack<Item> s = new Stack<Item>(); //in stack, store array item's value and index.
        //value is used to calculate height difference,
        //index is used to calculate bottom span.
        
        int sum = 0;
        
        for(int i=1; i< A.length; i++){
            if(A[i] < A[i-1]) s.push(new Item(A[i-1], i-1)); //push left boundary in.
            else if(A[i] > A[i-1]){ //found a right boundary.
                int floor = A[i-1]; //use the left array item 's height as floor.
                int ceiling = A[i]; //init height to be right boundary's height.
                while(!s.isEmpty() && s.peek().value <= A[i]){ //calculate and pop left boundary
                	//if they are lower. update the new floor
                    Item item = s.pop();
                    ceiling = item.value;
                    sum +=  (ceiling - floor) * (i - item.index -1);
                    floor = item.value;
                }
                
                //if left boundary is higher, ceiling is the right boundary.
                //calculate trapped water volume and proceed.
                //we don't pop left boundary in the case.
                if(!s.isEmpty() && s.peek().value > A[i]){
                    ceiling = A[i];
                    sum += (ceiling - floor) * (i - s.peek().index -1);
                }
                
            }
            
        }
        
        return sum;
        
        
    }
    
    public class Item{
        int value;
        int index;
        public Item(int val, int i){
            value = val;
            index = i;
        }
        
    }
    
    public int trap1(int[] A) {
        if(A == null || A.length == 0) return 0;
        
        int sum = 0;
        
        //find the index with max height.
        int maxIndex = 0;
        int maxH = Integer.MIN_VALUE;
        for(int i=0; i<A.length; i++){
            if(A[i] > maxH){
                maxH = A[i];
                maxIndex = i;
            }
        }
        
        int h = 0;
        for(int i=0; i<maxIndex; i++){
            if(A[i] > h){ //for each left item find the max left height so far
                h = A[i]; //if this one if the max left height. it doesn't hold any water.
            }else{
                sum += h - A[i];
            }
        }
        
        h = 0;
        for(int i=A.length -1; i>maxIndex; i--){
            if(A[i] > h){
                h = A[i];
            }else{
                sum += h - A[i];
            }
        }
        return sum;
    }
    
    /**
     * use array to keep track of left right max for each item.
     * @param A
     * @return
     */
    public int trap2(int[] A) {
        if(A == null || A.length == 0) return 0;
        
        
        //for every item calculate the max left and max right
        //use it to calculate the trap water volume each each bar.
        int[] lMax = new int[A.length];
        int[] rMax = new int[A.length];
        
        int max = A[0];
        
        for(int i=0; i< A.length; i++){
            if(A[i] > max){
                max= A[i];
            }
            lMax[i] = max;
        }
        
        max = A[A.length -1];
        for(int i=A.length -1; i>=0; i--){
            if(A[i] > max){
                max = A[i];
            }
            rMax[i] = max;
        }
        
        int sum = 0;
        for(int i=0; i<A.length; i++){
            sum += Math.min(lMax[i], rMax[i]) - A[i];
        }
        return sum;
    }
}
