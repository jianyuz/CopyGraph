package com.z2.SpiralMatrix;

public class SpiralMatrix {

	/**
	 * onion peel.
	 * until size smaller than 0
	 * take care of case of m or n = 1
	 * no need to do four rounds of printing.
	 * 
	 * reduce the matrix size each round by 2.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] matrix = new int[][]{
				{ 1,  2,  3,  4},
				{ 5,  6,  7,  8},
				{ 9, 10, 11, 12},
				{13, 14, 15, 16},
				{17, 18, 19, 20}
		};
		
		printSpiral(matrix, matrix[0].length, matrix.length, 0);
		
		System.out.println();
		int [][] matrix1 = new int[][]{
				{ 1,  2,  3,  4}
		};
		
		printSpiral(matrix1, matrix1[0].length, matrix1.length, 0);
		System.out.println();
		int [][] matrix2 = new int[][]{
				{1},
				{2},
				{3},
				{4}
		};
		
		printSpiral(matrix2, matrix2[0].length, matrix2.length, 0);
		System.out.println();
		
		int [][] matrix3 = new int[][]{
				{ 1,  2,  3,  4, 5},
				{ 5,  6,  7,  8, 6},
				{ 9, 10, 11, 12, 7},
				{13, 14, 15, 16, 8},
				{17, 18, 19, 20, 9}
		};
	
		
		printSpiral(matrix3, matrix3[0].length, matrix3.length, 0);
	}
	
	public static void printSpiral(int[][] matrix, int m, int n, int level){
		if(matrix == null){
			throw new IllegalArgumentException();
		}
		//stop it if m or n is smaller or equal to 0
		if( m <= 0 || n <=0){
			return;
		}
		
		/**
		 * when m or n == 1
		 * only need to print one line.
		 * not four loops
		 * print all the items on the line.
		 * remember to return.
		 * 
		 */
		if( m== 1){
			for(int i=0; i< n; i++){
				System.out.print(matrix[level + i][level] + " " );
			}
			return;
		}
		if (n == 1){
			for(int i=0; i< m; i++){
				System.out.print(matrix[level][level + i] + " " );
			}
			return;
		}
		
		// n fixed, m changes
		for(int i= 0; i< m-1; i++){
			System.out.print(matrix[level][level + i] + " ");
		}
		// m fixed n changes.
		for(int i= 0; i< n-1; i++){
			System.out.print(matrix[i+level][level+m-1] + " ");
		}
		
		//n fixed m changed.
		for(int i= m-1; i>=1; i--){
			System.out.print(matrix[level+n-1][i+level] + " ");
		}
		
		for(int i= n-1; i>= 1; i--){
			System.out.print(matrix[i+level][level] + " ");
		}
		
		printSpiral(matrix, m-2, n-2  , ++level); //change the value of level first before recursive call.
		
	}

}
