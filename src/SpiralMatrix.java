
public class SpiralMatrix {

	
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(matrix == null) return null;
         ArrayList<Integer> res = new ArrayList<Integer>();
        
        if(matrix.length == 0 || matrix[0].length == 0) return res;
        
       
        int m = matrix.length;
        int n = matrix[0].length;
        

        int layer = 0;
        
        int sM = layer;
        int eM = m - 1 - layer;
        int sN = layer;
        int eN = n -1 - layer;
        
        while(sM <= eM && sN <= eN ){
            
            if(sM == eM && sN == eN){ //end conditions.
                res.add(matrix[sM][sN]);
                break;
            }else if(sM  == eM){
                for(int i= sN ; i <= eN; i++){
                    res.add(matrix[sM][i]);
                }
                break;
            }else if(sN == eN){
                for(int i= sM; i<= eM; i++){
                    res.add(matrix[i][sN]);
                }
                break;
            }
        
            for(int i= sN ; i < eN; i++){
                res.add(matrix[sM][i]);
            }
            
            for(int i= sM; i< eM; i++){
                res.add(matrix[i][eN]);
            }
            
            for(int i= eN; i> sN; i--){
                res.add(matrix[eM][i]);
            }
        
            for(int i= eM; i> sM; i--){
                res.add(matrix[i][sN]);
            }
            
            layer ++;
            sM = layer;
            eM = m - layer -1;
            sN = layer;
            eN = n - layer -1;
        }
        
        
        return res;
    }
	
	/**
	 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		// Start typing your Java solution below
		// DO NOT write main() function

		int[][] res = new int[n][n];

		if (n <= 0)
			return res;

		int layer = 0;
		int start = layer;
		int end = n - 1 - layer;

		int num = 1;
		while (start <= end && num <= n * n) { // the count of number is n2.
			if (start == end) {
				res[start][end] = num;
				break; // termination
			}

			for (int i = start; i < end; i++) {
				res[start][i] = num++;
			}

			for (int i = start; i < end; i++) {
				res[i][end] = num++;
			}

			for (int i = end; i > start; i--) {
				res[end][i] = num++;
			}

			for (int i = end; i > start; i--) {
				res[i][start] = num++;
			}

			layer++;
			start = layer;
			end = n - 1 - layer;
		}

		return res;
	}
}
