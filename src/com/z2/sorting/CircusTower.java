package com.z2.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircusTower {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Person> input = new ArrayList<Person>();
		input.add(new Person(65, 100));
		input.add(new Person(70, 150));
		input.add(new Person(56, 90));
		input.add(new Person(75, 190));
		input.add(new Person(60, 96));
		input.add(new Person(65, 110));
		humanTower(input);
	}

	
	public static void humanTower(List<Person> input){
		Collections.sort(input);
		
		int mStart, mEnd;
		int rStart = -1, rEnd = -1;
		mStart = mEnd = 0;
		for(int i = 1; i< input.size(); i++){
			if(input.get(i).wt >= input.get(i-1).wt){
				mEnd = i;
			}else{
				if(rEnd - rStart < mEnd - mStart){
					rStart = mStart;
					rEnd = mEnd;
				}
				mStart = mEnd = i;
			}
		}
		rStart = mStart;
		rEnd = mEnd;
		
		for(int i = rStart; i<= rEnd; i++){
			System.out.print(input.get(i));
		}
		
	}
	
	static class Person implements Comparable<Person>{
		int ht;
		int wt;
		
		public Person(int ht, int wt){
			this.ht = ht;
			this.wt = wt;
		}
		
		public int compareTo(Person p){
			if(this.ht == p.ht){
				return this.wt - p.wt;
			}else{
				return this.ht - p.ht;
			}
		}
		
		public String toString(){
			return "[" + ht + "," + wt + "]";
		}
	}
	
	
}
