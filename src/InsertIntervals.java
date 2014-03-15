import java.util.ArrayList;


public class InsertIntervals {

	public class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
   }
	
	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<Interval> res = new ArrayList<Interval>();
        //if the intervals are empty;
        if(intervals == null || intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        
        int index = 0; //keep track of the position where the newInterval is inserted.
        Interval cur = null;
        for(int i=0; i< intervals.size(); i++){
            cur = intervals.get(i); //current interval
            //non intersecting scanario.
            if(newInterval.end < cur.start){
                //new interval to the left of cur.start.
                res.add(cur);
            }else if(cur.end < newInterval.start){
                res.add(cur); //new interval to the right.
                index ++; //increment insert position of newInterval.
            }else{
                //intersecting. merge the intervals.
                newInterval = new Interval(Math.min(cur.start, newInterval.start), Math.max(cur.end, newInterval.end));
            }
        }
        //finally, insert the new interval at the correct location.
        res.add(index, newInterval);
        return res;
    }
	
}
