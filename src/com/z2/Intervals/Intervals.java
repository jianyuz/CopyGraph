package com.z2.Intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * merge a list of integer intervals.
 * sort first.
 * then use counters to count start and end 
 * like parentheses.
 * 
 * @author zhouzhou
 *
 */
public class Intervals {
	
	public static void main(String[] args){
		List<Interval> testInts = new ArrayList<Interval>();
		testInts.add(new Interval(1, 4));
		testInts.add(new Interval(2, 5));
		testInts.add(new Interval(5, 9));
		testInts.add(new Interval(10, 12));
		testInts.add(new Interval(3,4));
		List<Interval> res = mergeIntervals(testInts);
		
		for(Iterator<Interval> it = res.iterator(); it.hasNext();){
			Interval interval = it.next();
			System.out.print(interval.toString() + ",");
		}
		
		System.out.println();
		testInts = new ArrayList<Interval>();
		testInts.add(new Interval(4, 7));
		testInts.add(new Interval(2, 6));
		testInts.add(new Interval(1, 4));
		testInts.add(new Interval(8, 12));
		testInts.add(new Interval(7,9));
		res = mergeIntervals(testInts);
		
		for(Iterator<Interval> it = res.iterator(); it.hasNext();){
			Interval interval = it.next();
			System.out.print(interval.toString() + ",");
		}
	}
	
	public static List<Interval> mergeIntervals(List<Interval> intervals){
		List<String> endPoints = new ArrayList<String>();
		
		
		for(Iterator<Interval> it = intervals.iterator(); it.hasNext();){
			Interval interval = it.next();
			endPoints.add(interval.getLow() + "S");
			endPoints.add(interval.getHigh() + "E");
		}
		
		Collections.sort(endPoints, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				int s1 = Integer.parseInt(o1.substring(0, o1.length() - 1));
				int s2 = Integer.parseInt(o2.substring(0, o2.length() - 1 ));
				return s1 - s2;
			}
			
		});
		
		int counter = 0;
		int start = 0;
		int end = 0;
		
		List<Interval> res = new ArrayList<Interval>();
		for(Iterator<String> it=endPoints.iterator(); it.hasNext();){
			String str = it.next();
			if( str.endsWith("S")){
				counter ++;
				if(counter == 1){
					start = Integer.parseInt(str.substring(0, str.length() -1));
					if(res.size() <= 0) continue;
					Interval prev = res.get(res.size()-1);
					if(start == prev.getHigh()){
						start = prev.getLow(); //continuous interval,keep previous start.
						res.remove(res.size() -1);
					}
				}
			}else if(str.endsWith("E")){
				counter --;
				if(counter ==0){
					end = Integer.parseInt(str.substring(0, str.length() -1));
					res.add(new Interval(start, end));
				}
					
			}
			
			
		}
		return res;
	}
	
	static class Interval{
		private int low;
		private int high;
		
		Interval (int low, int high){
			this.low = low;
			this.high = high;
		}
		
		public int getLow(){
			return low;
		}
		public int getHigh(){
			return high;
		}
		
		public String toString(){
			return "[" + low + "," + high + "]";
		}
	}

}
