package com.z2.countPaths;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * m * n grid only move right or down. use combinatorial to calculate all the
 * possible paths. the following uses dynamica programming to backtrac O(m*n)
 * space O(m*n) run time
 * 
 * @author zhouzhou
 * 
 */
public class CountPaths {

	public static int countPaths(int m, int n) {
		int[][] matrix = new int[m][n];

		for (int i = 0; i < matrix.length - 1; i++) {
			for (int j = 0; j < matrix[0].length - 1; j++) {
				matrix[i][j] = 0;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0] = 1;
		}
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[0][j] = 1;
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (i == 1 && j == 1) {
					matrix[i][j] = 0; // block
				} else {
					matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
				}
			}
		}
		return matrix[m - 1][n - 1];
	}

	public static ArrayList<Point> current_path = new ArrayList<Point>();

	public static boolean getPaths(int x, int y) {
		Point p = new Point(x, y);
		current_path.add(p);
		if (0 == x && 0 == y)
			return true; // current_path
		boolean success = false;
		if (x >= 1 && is_free(x - 1, y)) { // Try right
			success = getPaths(x - 1, y); // Free! Go right
		}
		if (!success && y >= 1 && is_free(x, y - 1)) { // Try down
			success = getPaths(x, y - 1); // Free! Go down
		}
		if (!success) {
			current_path.remove(p); // Wrong way!
		}
		return success;
	}

	public static boolean is_free(int x, int y){
		return true;
	}
	
	/** directly back tracking using the same thought as in dynamica programming
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int countPath1(int m, int n){
		if(m == 0 && n >=0  ){
			return 1;
		}
		if(n==0 && m >=0){
			return 1;
		}
		if(m < 0 || n < 0){
			return 0;
		}
		return countPath1(m-1, n) + countPath1(m, n-1);
	}
	
	public static void main(String[] args) {
		System.out.println(countPaths(3, 3));
		System.out.println(countPath1(4, 5));
		System.out.println(getPaths(5, 6));
		System.out.println(Arrays.deepToString(current_path.toArray(new Point[0] )));
	}

}
