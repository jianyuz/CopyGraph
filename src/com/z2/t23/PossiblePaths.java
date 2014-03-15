package com.z2.t23;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * get possible paths from one element to the bottom of the matrix
 * only down, down left and down right
 * 
 */
public class PossiblePaths {

		
		private static List<String> getPath(int[][] a,int row, int col, int sum){
			if(row <0 || row > 2)
				return new ArrayList<String>();
			if(col < 0 || col > 2)
				return new ArrayList<String>();
			List<String> paths = new ArrayList<String>();
			//sum = sum + a[row][col];
			paths.addAll(getPath(a,row+1,col-1,0));
			paths.addAll(getPath(a,row+1,col,0));
			paths.addAll(getPath(a,row+1,col+1,0));
			for(int i =0;i<paths.size();i++){
				paths.set(i,String.valueOf(a[row][col]) + "-->"+paths.get(i));// append to existing path info.
			}
			if(paths.isEmpty()){
				paths.add(String.valueOf(a[row][col]));
			}
			return paths;
		}
		
		public static void main(String[] argv){
			int a[][] = {{1,2,3},{4,5,6},{7,8,9}};
			List<String> paths = getPath(a,0,0,0);
			for(String path: paths){
				System.out.println(path.toString());
			}
			System.out.println("---");
			paths = getPath(a,0,1,0);
			for(String path: paths){
				System.out.println(path.toString());
			}
		}



}
