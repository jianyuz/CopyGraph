import java.util.Stack;


public class MaximumBinRectangle {

	
	/**
	 * doesn't pass speed test.
	 * O(n 3)
	 * 
	 * O(n2) space.
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(matrix == null) return 0;
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        
        //let base(i,j) mark the number of continuous 1s at row i, bottom right element at element(i,j).
        int rows = matrix.length;
        int cols =matrix[0].length;
        int[][] base = new int[rows][cols];
        
        int count;
        //populate base matrix
        for(int i=0; i< rows; i++){
            count = 0;
            for(int j=0; j< cols; j++){
                if(matrix[i][j] == '1'){
                    count ++;
                    base[i][j] = count;
                }else{
                    count = 0;
                }
                
            }
        }
        
        int max = Integer.MIN_VALUE;
        int bWidth; //base width;
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                
                bWidth = Integer.MAX_VALUE;
                for(int k= i; k>=0; k--){
                    
                    bWidth = Math.min(bWidth, base[k][j]); //incrementally increase the height from i to 0.
                    max = Math.max(max, bWidth * (i -k +1));
                }        
            }
        }
        
        return max;
	}
	
	/**
	 * use the max rect in histogram algorithm
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maximalRectangle1(char[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //build height array vertically 
        //treat each row and its above as a histogram.
        
        if(matrix == null) return 0;
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int[] heights = new int[cols + 1]; //needs additional cols as guardians.
        heights[cols] = -1;
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                //accumulate height of 1 as histogram bar.
                if(matrix[i][j] == '1'){
                    heights[j]++;
                }else{//==0 reset count
                    heights[j]= 0;
                }
            }
            max = Math.max(max, maxRectInHist(heights));
        }
        
        
        return max;
    }
    
    private static int maxRectInHist(int[] heights){
        if(heights == null || heights.length == 0) return 0;
    
        int index = 0;
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        
        while(index < heights.length){
            if(stack.isEmpty() || heights[stack.peek()] <= heights[index]){
                stack.push(index++);
            }else{
                int heightIndex = stack.pop();
                //find out the left edge of the rectangle, bypass equal height bars.
                while(!stack.isEmpty() && heights[stack.peek()] == heights[heightIndex]) stack.pop();
                int leftEdge = stack.isEmpty()? -1: stack.peek();
                max = Math.max(max, heights[heightIndex] * (index - leftEdge -1));
            }
        }
        return max;
    }
	
	
	/**
	 * Given n non-negative integers representing the histogram's 
	 * bar height where the width of each bar is 1, find the area of largest rectangle in the histogram
	 * O(n2)
	 * can't pass the speed test.
	 * @param height
	 * @return
	 */
	public int largestRectangleArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(height == null || height.length == 0) return 0;
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i< height.length; i++){
            int h = Integer.MAX_VALUE;
            for(int k = i; k >=0; k--){
                h = Math.min(h, height[k]);
                max = Math.max(max, h * (i-k +1));
            }
        }
        
        return max;
    }
	
	
	public static int largestRectangleArea1(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(height == null || height.length == 0) return 0;
        
        //for each bar, consider the case it maybe the bar contained in the largest rectangle
        //just need to find out width of such rectangle.
        //keep a stack to keep track of increasing height of bars.
        //stop add height into it if encounters a lower bar.
        //for the bar at the top of stack, for it to include in the largest rectangle
        //the right edge of the rectangle ends right at current index.
        //the next element in the stack marks its left edge of the rectangle.
        //because we don't add any bar index with height larger than it into the stack
        
        //summary, we use stack and current index to keep track of left boundary and 
        //right boundary of the max rectangle that includes the element stack.peek();
        //each element is pushed in once and popu once. in total O(2*n) plug array copy O(n).
        //space O(n).
        Stack<Integer> stack = new Stack<Integer>();
        
        int index =0;
        int max = Integer.MIN_VALUE;
        
        int [] h = new int[ height.length + 1];
        System.arraycopy(height, 0, h, 0, height.length);
        h[height.length] = -1; //add sentinel to make sure real stack item is emptied.
        
        while(index < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[index]){
                stack.push(index);
                index ++;
            }else{
                int heightIndex = stack.pop();
                //this is the index of height bar of the max rectangle for it to be included in.
                //anything after has height lower
                while(!stack.isEmpty() && h[stack.peek()] == h[heightIndex]) stack.pop();
                //pop off any thing equal to height index. they form the smaller max rectangle for sure.
                int lBarIndex = stack.isEmpty()? -1: stack.peek(); //-1 is the senital. 
                
                //left edige of the max rectangle
                max = Math.max(max, h[heightIndex] * (index - lBarIndex -1)); 
            }
        }
        return max;
    }
	 
	 public static void main(String[] args){
			int[] heights = new int[] {4,2};
			System.out.println(largestRectangleArea1(heights));
			
			char[][] matrix = new char[][]{
					{'0','0'}
			};
			System.out.println(maximalRectangle1(matrix));
		}
}
