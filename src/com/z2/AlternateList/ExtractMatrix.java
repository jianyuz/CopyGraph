package com.z2.AlternateList;
/**
 * 7 Answers
Write a code to extract individual blocks from a given matrix....
Eg: if we have a 4x4 matrix you need to extract 2x2 independent matrices and store them in 4 different arrays...
Given matrix: 
1 2 3 4 
5 6 7 8 
9 10 11 12
13 14 15 16
For the above matrix the output should be as follows..
Array1: 1 2 5 6 
Array2: 3 4 7 8
Array3: 9 10 13 14
Array4: 11 12 15 16
 * @author zhouzhou
 *
 */
public class ExtractMatrix {

	public static void main(String[] args){
		int[][] mat = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}
		};
		
		int[][] res = extractMatrix(mat);
		
		for(int i=0; i< res.length; i++){
			for( int j =0; j< res[0].length; j++){
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static int[][] extractMatrix(int[][] mat){
		if(mat == null) {
			throw new IllegalArgumentException("invalid input");
		}
		int lenX = mat.length;
		int lenY = mat[0].length;
		if(lenX != lenY){
			throw new IllegalArgumentException("invalid input");
		}
		
		if(lenX <= 1 || lenX%2 == 1){
			throw new IllegalArgumentException("invalid input");
		}
		int[][] res = new int[4][lenX/2 * lenX/2];
		//x (0 - n/2) (n/2 - n)
		//y (0- n/2) (n/2 -n)
		
		float half = ((float)lenX)/2;
		int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0;
		for(int i = 0; i< lenX; i++){
			for(int j = 0; j < lenY; j++){
				if(i < half && j < half){
					res[0][counter1++] = mat[i][j];
				}else if (i < half && j >= half){
					res[1][counter2++] = mat[i][j];
				}else if (i >= half && j < half){
					res[2][counter3++] = mat[i][j];
				}else if (i >= half && j >= half){
					res[3][counter4++] = mat[i][j];
				}
			}
		}
		return res;
	}//trick: boundary
	//dividing > and <=
	//preallocating all sizes of the arry.
}
