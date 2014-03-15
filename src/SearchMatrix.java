
public class SearchMatrix {
/*
	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	*/
	 public void setZeroes(int[][] matrix) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(matrix == null) return;
	        if(matrix.length == 0 || matrix[0].length == 0) return;
	        
	        //use first row and col to remember 
	        //which row and col needs to be set with 0s.
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        
	        //check if row 0 is 0.
	        boolean row0Zero = false;
	        for(int i=0; i<cols; i++){
	            if(matrix[0][i] == 0){
	                row0Zero = true;
	                break;
	            }
	        }
	        
	        boolean col0Zero = false;
	        for(int i=0; i<rows; i++){
	            if(matrix[i][0] == 0){
	                col0Zero = true;
	                break;
	            }
	        }
	        
	        //mark which rows cols got zeros.
	        for(int i=1; i< rows; i++){
	            for(int j=1; j<cols; j++){
	                if(matrix[i][j] == 0){
	                    matrix[0][j] = 0;
	                    matrix[i][0] = 0;
	                }
	            }
	        }
	        
	        for(int i=1; i<rows; i++){
	            for(int j=1; j<cols; j++){
	                if(matrix[0][j] == 0 || matrix[i][0] == 0){
	                    matrix[i][j] = 0;
	                }
	            }
	        }
	        
	        if(row0Zero){
	            for(int i=0; i<cols; i++){
	                matrix[0][i] = 0;
	            }
	        }
	        
	        if(col0Zero){
	            for(int i=0; i<rows; i++){
	                matrix[i][0] = 0;
	            }
	        }
	        
	    }
	/**
	 *  worst case go to opposite corner.
	 *  2 * n steps
	 *  O(n)
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(matrix == null) return false;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int i = 0;
        int j = cols -1; 
        
        //start from top right corner.
        while(i < rows && j >=0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] < target){
                i ++;
            }else{
                j--;
            }
        }
        
        return false;
    }
}
