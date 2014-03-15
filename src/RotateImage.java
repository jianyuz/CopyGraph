/**
 * You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

need 4 temp variabls.
do the rotation layer by layer

n2 time complexity.

 * @author zhouzhou
 *
 */
public class RotateImage {

	public void rotate(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(matrix == null || matrix.length == 0) return;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if(rows != cols) return;
        
        doRotate(matrix, 0, rows);
        
    }
    
    public void doRotate(int[][] m, int level, int n){
        int left = n - 2 * level;
        if(left == 0 || left == 1) return;//terminate condition.
        
        
        //rotate the elements on the frame.
        int start = level;
        int end = n - 1 - level;
        
        for(int i = start; i < end; i++){
            //rotate the four elements.
            int a = m[level][i];
            int b = m[i][n-1-level];
            int c = m[n-1-level][n-1-i];
            int d = m[n-1-i][level];
            
            m[level][i] = d;
            m[i][n-1-level] = a;
            m[n-1-level][n-1-i] = b;
            m[n-1-i][level] = c;
        }
        
        doRotate(m, level + 1, n);
    }
    
    /**
     * Iterative version.
     * @param m
     */
    public void rotate1(int[][] m) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(m == null || m.length == 0) return;
        
        int rows = m.length;
        int cols = m[0].length;
        
        if(rows != cols) return;
        
        int n = rows;
        
        for(int level = 0; (n - level * 2) > 1; level ++){
             //rotate the elements on the frame.
            int start = level;
            int end = n - 1 - level;
        
            for(int i = start; i < end; i++){
                //rotate the four elements.
                int a = m[level][i];
                int b = m[i][n-1-level];
                int c = m[n-1-level][n-1-i];
                int d = m[n-1-i][level];
            
                m[level][i] = d;
                m[i][n-1-level] = a;
                m[n-1-level][n-1-i] = b;
                m[n-1-i][level] = c;
            }
        }  
        
    }
    
    /**
     * iterative solutin using one temp variable.
     * @param m
     */
    public void rotate2(int[][] m) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(m == null || m.length == 0) return;
        
        int rows = m.length;
        int cols = m[0].length;
        
        if(rows != cols) return;
        
        int n = rows;
        
        for(int level = 0; (n - level * 2) > 1; level ++){
             //rotate the elements on the frame.
            int start = level;
            int end = n - 1 - level;
        
            for(int i = start; i < end; i++){
                //rotate the four elements.
                int temp = m[level][i];
                m[level][i] = m[n-1-i][level];
                m[n-1-i][level] = m[n-1-level][n-1-i];
                m[n-1-level][n-1-i] = m[i][n-1-level];
                m[i][n-1-level] = temp;
            }
        }  
        
    }
}
