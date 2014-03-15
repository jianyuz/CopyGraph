package com.z2.StackTest;

import java.util.Stack;
/**
 * implements the tower using stack.
 * remember to put elements into stack 0.
 * @author zhouzhou
 *
 */
public class HanoiTower {

	private Stack<Integer> disks;
	private int index;
	
	public HanoiTower(int i){
		this.index = i;
		this.disks = new Stack<Integer>();
	}
	
	public int index(){
		return index;
	}
	
	public void add(int d) throws Exception{
		if(!disks.isEmpty() && disks.peek() <= d){
			throw new Exception("error placing disks");
		}else{
			disks.push(d);
		}
		
	}
	
	public void moveTopTo(HanoiTower t) throws Exception{
		int top = disks.pop();
		t.add(top);
		System.out.println("move disk " + top + " from " + index + " to " + t.index());
	}
	
	public void moveDisks(int n, HanoiTower dest, HanoiTower buffer) throws Exception{
		if(n > 0){
			moveDisks( n-1, buffer, dest);
			moveTopTo(dest);
			buffer.moveDisks(n-1, dest, this );
		}
	}
	
	public static void solveHanoiTower(int size, int from, int to, int mid){
		if(size == 1){
			log(from, to);
			return;
		}
		solveHanoiTower(size-1, from, mid, to);
		solveHanoiTower(1, from, to, mid);
		solveHanoiTower(size -1, mid, to, from);
		
	}
	
	public static void log(int from , int to){
		System.out.println("move disk from " + from + " to " + to);
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		solveHanoiTower(3, 1, 3, 2);
		System.out.println("----");
		HanoiTower[] ts = new HanoiTower[3];
		for(int i=0; i< 3; i++){
			ts[i] = new HanoiTower(i+1);
		}
		int n=4;
		for(int i= n; i>0; i--){
			ts[0].add(i);
		}//add disks to tower 0
		ts[0].moveDisks(n, ts[2], ts[1]);
	}

}
