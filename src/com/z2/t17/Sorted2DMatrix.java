package com.z2.t17;

public class Sorted2DMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 },
				{ 3, 6, 9, 6, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };

		int target = 14;

		SearchResult searchResult = stepwiseSearch(matrix, target);
		
		SearchResult searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
		
		target = 9;

		searchResult = stepwiseSearch(matrix, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
		
		target = 10;
		
		searchResult = stepwiseSearch(matrix, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
		target = 1;

		searchResult = stepwiseSearch(matrix, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
		target = 16;

		searchResult = stepwiseSearch(matrix, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
		target = 900;

		searchResult = stepwiseSearch(matrix, target);
		System.out.println("result found: " + searchResult.found + " target: "
				+ searchResult.targetRow + " " + searchResult.targetCol);
		searchResult1 = quadSearch(matrix, 0, 0, matrix.length-1, matrix.length-1, target);
		System.out.println("result1 found: " + searchResult1.found + " target: "
				+ searchResult1.targetRow + " " + searchResult1.targetCol);
	}

	/**
	 * stepwise way to search for the element start from top right corner or
	 * lower left corner. each step remove whole row or column maximumly runs 2n
	 * elements.
	 * 
	 * o(n) run time
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static SearchResult stepwiseSearch(int[][] matrix, int target) {
		
		SearchResult res = new Sorted2DMatrix().new SearchResult();
		res.found = false;
		int row = 0;
		int col = matrix.length - 1;
		
		if(target < matrix[0][0] || target > matrix[matrix.length-1][matrix.length-1]){
			return res;
		}

		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] > target) {
				--col;
			} else if (matrix[row][col] < target) {
				++row;
			} else {
				res.found = true;
				res.targetRow = row;
				res.targetCol = col;
				break;
			}
		}
		return res;

	}
	
	
	/**
	 * eliminate sub-matrices each time.
	 * keep 3 in each round.
	 * run time N to the power of 1.58
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static SearchResult quadSearch(int[][] matrix, int tr, int tc, int br, int bc, int target){
		
		SearchResult res = new Sorted2DMatrix().new SearchResult();
		
		if(tr > br || tc > bc){
			return res;
		}
		
		if(target < matrix[tr][tc] || target > matrix[br][bc]){
			return res;
		}
		
		if(tr == br && tc== bc){
			if(matrix[tr][tc] == target){
				res.found = true;
				res.targetRow = tr;
				res.targetCol = tc;
				
			}
			return res;
		}
		
		int row = tr + (br-tr)/2;
		int col = tc + (bc-tc)/2;
		
		SearchResult res1, res2, res3;
		res = new Sorted2DMatrix().new SearchResult();
		if(matrix[row][col] > target){
			res1 = quadSearch(matrix,tr, tc, row, col , target);
			res2 = quadSearch(matrix,tr, col+1, row, bc , target);
			res3 = quadSearch(matrix,row+1, tc, br, col , target);
			if(res1.found){res = res1;}
			if(res2.found){res = res2;}
			if(res3.found){res = res3;}
			return res;
		}else if(matrix[row][col] < target){
			res1 = quadSearch(matrix,row+1 , col+1, br, bc , target);
			res2 = quadSearch(matrix,tr, col+1, row, bc , target);
			res3 = quadSearch(matrix,row+1, tc, br, col , target);
			if(res1.found){res = res1;}
			if(res2.found){res = res2;}
			if(res3.found){res = res3;}
			return res;
		}else{
			res.found = true;
			res.targetCol = col;
			res.targetRow = row;
			return res;
		}
		
	}

	class SearchResult {
		boolean found;
		int targetRow = -1;
		int targetCol = -1;
	}
}
