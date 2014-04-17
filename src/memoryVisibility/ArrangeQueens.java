package memoryVisibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrangeQueens {

	public static void main(String[] args){
		List<int[]> res = new ArrayList<int[]>();
		arrange(res, 8);
		System.out.println("total " + res.size());
		for(int[] l: res){
			System.out.println(Arrays.toString(l));
		}
	}
	/**
	 * can't be same row or col
	 * can't be on diagnal.
	 * m+n = a + b
	 * m-n = a -b
	 * 
	 * @param res
	 * @param n
	 */
	public static void arrange(List<int[]> res, int n){
		int[] cur = new int[n];
		arrange(res, cur,  0, n); //start from row 0
	}
	
	/**
	 * for each row find col pos.
	 * @param res
	 * @param cur
	 * @param row
	 * @param n
	 */
	public static void arrange(List<int[]> res, int[] cur, int row, int n){
	
		if(row == n){
			res.add(Arrays.copyOf(cur, cur.length));//need to copy array result for each DFS.
			return;
		}
		
		for(int i=1; i<= n; i++){
			boolean colUsed = false;
			for(int j=0; j< row; j++){
				if(cur[j] == i || cur[j] + j == i + row || cur[j] - j  == i - row) {
					colUsed = true;
					break;
				}
			}
			if(!colUsed){
				cur[row] = i; //pick a column.
				arrange(res, cur, row+1, n);
			}
			
		}
		
	}
	
	
}
