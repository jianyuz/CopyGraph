import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MergeIntervals {

	
	static class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
	}
	
	public static void main(String[] args){
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(2, 3));
		intervals.add(new Interval(5, 5));
		intervals.add(new Interval(2, 2));
		intervals.add(new Interval(3, 4));
		intervals.add(new Interval(3, 4));
		merge(intervals);
	}
	
	/**
	 * sort intervals and merge interval one by one.
	 * update last interval's end pos or add the new interval.
	 * @param intervals
	 * @return
	 */
	public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<Interval> res = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0) return res;
        
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start < i2.start){
                    return -1;
                }else if(i1.start > i2.start){
                    return 1;
                }else{
                    return i1.end - i2.end;
                }
            }
        });
        
        //sort the intervals based on start and end pos.
        
        res.add(intervals.remove(0));
        
        for(Interval i: intervals){
            Interval last = res.get(res.size()-1);
            if(i.start <= last.end && i.end > last.end){
                last.end = i.end;
            }else if(i.start > last.end){
                res.add(i);
            }
            
        }
        
        return res;
        
    }
	
	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<Interval> res = new ArrayList<Interval>();
        
        if(intervals == null || intervals.size() == 0) return res;
        
        List<Point> points = new ArrayList<Point>();
        
        int start, end;
        for(Interval i : intervals){
                start = i.start;
                points.add(new Point(start, 'A')); //change type to enumeration.
                end = i.end;
                points.add(new Point(end, 'B')); //A B choice is better than ST.
        }
        
        Collections.sort(points); //static sort.
        
        int curStart = 0, curEnd= 0;
        
        int count =0;
        for(Point p: points){
            if(p.type == 'A'){
                if(count == 0){
                    curStart = p.value;
                }
                count ++;
            }else{
                count --;
                if(count == 0){
                    curEnd = p.value;
                    res.add(new Interval(curStart, curEnd));
                }
            }
        
        }
        return res;
        
    }
    
    static class Point implements Comparable<Point>{
        int value;
        char type;
        
        public Point(int v, char type){
            this.value = v;
            this.type = type;
        }
        
        public int compareTo(Point p1){//compare to must be public.
            if(this.value < p1.value) return  -1;
            if(this.value > p1.value) return 1;
            
            return this.type - p1.type;
        }
    }
    
	/**
	 * I can only store one value in the hashMap.
	 * can't solve the case [5,5]
	 * @param intervals
	 * @return
	 */
	 public static ArrayList<Interval> merge1(ArrayList<Interval> intervals) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        
	        ArrayList<Interval> res = new ArrayList<Interval>();
	        
	        if(intervals == null || intervals.size() == 0) return res;
	        
	        Map<Integer, Boolean> isStart = new HashMap<Integer, Boolean>();
	        
	        List<Integer> points = new ArrayList<Integer>();
	        
	        int start, end;
	        for(Interval i : intervals){
	                start = i.start;
	                if(isStart.get(start) ==  null || isStart.get(start)){
	                    isStart.put(start, true);
	                    points.add(start);
	                }else{
	                    isStart.put(start, null); //cancel out the element.
	                    points.remove(points.indexOf(start));
	                }
	                end = i.end;
	                if(isStart.get(end) == null || !isStart.get(end)){
	                    isStart.put(end, false);
	                    points.add(end);
	                }else{
	                    isStart.put(end, null); //cancel out the element.
	                    points.remove(points.indexOf(end));
	                }
	        }
	        
	        Collections.sort(points);
	        
	        int curStart = 0, curEnd= 0;
	        
	        int count =0;
	        for(Integer i: points){
	            if(isStart.get(i)){
	                if(count == 0){
	                    curStart = i;
	                }
	                count ++;
	            }else{
	                count --;
	                if(count == 0){
	                    curEnd = i;
	                    res.add(new Interval(curStart, curEnd));
	                }
	            }
	        
	        }
	        return res;
	        
	    }
}
