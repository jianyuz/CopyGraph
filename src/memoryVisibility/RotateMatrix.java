package memoryVisibility;

import java.util.Arrays;

public class RotateMatrix {

	public static void main(String[] args){
		//1 2 3   7 4 1
		//4 5 6   8 5 2
		//7 8 9   9 6 3
		//int[][] matrix = new int[][] {{1,2,3}, {4, 5, 6}, {7,8,9}};
		int[][] matrix = new int[][] {{1,2,3, 4}, {5, 6, 7,8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
		for(int i=0; i< matrix.length; i++)
			System.out.println(Arrays.toString(matrix[i]));
		System.out.println();
		rotate1(matrix);
		for(int i=0; i< matrix.length; i++)
			System.out.println(Arrays.toString(matrix[i]));
	}
	
	
	public static void rotate(int[][] matrix){
		int n = matrix.length;
		
		int limit = n/2;
		for(int level=0; level < limit; level++){
			for(int i=level; i<n-1-level; i++){
				int tmp = matrix[i][level];
				matrix[i][level]   = matrix[level][n-1-i];
				matrix[level][n-1-i] = matrix[n-1-i][n-level-1];
				matrix[n-i-1][n-level -1] = matrix[n-1-level][i];
				matrix[n-1-level][i] = tmp;
			}
		}
		
	}
	
	public static void rotate1(int[][] matrix){
		int n = matrix.length;
		
		//exchange elements across diagnal's
		for(int i=0; i< n; i++){
			for(int j=i+1; j<n; j++){
				swap(matrix, i, j, j, i);
			}
		}
		
		//exchange the lines across the middle row.
		for(int i=0; i< n/2; i++){
			for(int j=0; j< n; j++){
				swap(matrix, i, j, n-1-i, j);
			}
		}
		
	}
	
	public static void swap(int[][] matrix, int i, int j, int m, int n){
		int tmp = matrix[i][j];
		matrix[i][j] = matrix[m][n];
		matrix[m][n] = tmp;
	}
}
