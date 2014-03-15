package com.z2.t18;

public class UniquePath {

	/**
	 * unique path from 1, 1 to 7.3
	 * can be treated as combinatorial problem
	 * but what if there is obstacle in middle.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(uniquePath(6, 2, 6, 2));
		System.out.println(uniquePathDP(7, 3));
		System.out.println(uniquePath(7, 3));
	}
	
	public static int uniquePath(int row, int col, int m, int n){
		if(row == 0 && col == 0){
			return 1;
		}
		
		if(row < 0 || col < 0){
			return 0;
		}
		
		return uniquePath(row-1, col, m, n) + uniquePath(row, col-1, m, n);
	}
	
	//memoization
	
	public static int uniquePath(int m, int n){
	
		int[][] matrix = new int[m][n];
		
		return uniquePath(m-1,n-1, matrix);
	}
	
	public static int uniquePath(int row, int col, int[][] matrix){
		if(row == 0 && col == 0){
			return 1;
		}
		
		if(row <0  || col <0){
			return 0;
		}
		
		if(col < 1){
			matrix[row-1][col] = uniquePath(row-1, col,  matrix);
			return matrix[row-1][col];
		}
		if(row < 1 ){
			matrix[row][col-1]= uniquePath(row, col-1,  matrix);
			return matrix[row][col-1];
		}
		
		if(matrix[row-1][col] == 0){
			matrix[row-1][col] = uniquePath(row-1, col,  matrix);
		}
		
		if(matrix[row][col-1] == 0){
			matrix[row][col-1] = uniquePath(row, col-1,  matrix);
		}
		
		
		
		return matrix[row-1][col] + matrix[row] [col-1];
	}
	
	
	//O(m*n)
	public static int uniquePathDP(int m, int n){
		int[][] matrix = new int[m][n];
		
		for(int i=0; i< m; i++){
			matrix[i][n-1] = 1;
		}
		
		for(int i=0; i< n; i++){
			matrix[m-1][i] = 1;
		}
		
		for(int i=m-2; i >= 0; i--){
			for(int j=n-2; j>=0; j--){
				matrix[i][j] = matrix[i+1][j] + matrix[i][j+1];
			}
		}
		
		return matrix[0][0];
	}

}
