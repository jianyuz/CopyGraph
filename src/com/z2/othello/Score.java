package com.z2.othello;

public class Score {

	private int[] score;
	
	public Score(){
		score = new int[2];
		score[0] = 0; 
		score[1] = 0;
	}
	
	public int getScore(int player){
		return score[player];
	}
	
	public final void addScore(int player, int amount){
		score[player] += amount;
	}
}
