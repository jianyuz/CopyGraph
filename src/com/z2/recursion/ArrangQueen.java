package com.z2.recursion;

/**
 * arrange 8 queens on board.
 * print out the number of ways.
 * timecomplexity 8 ^ 3;
 * @author zhouzhou
 *
 */
public class ArrangQueen {
	
	static int[] colForRow = new int[8];//remember where the col is placed for previous arrangement.
	
	public static void main(String[] args){
		arrangeQueen(0);
	}
	
	public static boolean check(int row){
		for(int i=0; i< row; i++){//previous arrangement check for collision.
			int diff = Math.abs(colForRow[i] - colForRow[row]);
			if(diff == 0 || diff == row -i) return false; //same col or on diagnal.
		}
		return true;
	}
	
	public static void printBoard(){
		for(int i=0; i< 8; i++){
			System.out.print("[row " + i + " col " + colForRow[i] + "]");
		}
		System.out.println();
	}
	
	//arrange queen row by row.
	//each row must have a queen.
	//for each row. it can be on any cols.
	//iterate through.
	
	public static void arrangeQueen(int row){
		if(row == 8){
			printBoard();
			return;
		}
		for(int i=0; i< 8; i++){
			colForRow[row] = i;
			if(check(row)){//check for collision with previous decision.
				arrangeQueen(row + 1);//ok with arrangement. continue.
				//otherwise loop will try next arrangement.
			}
		}
		
	}

}
