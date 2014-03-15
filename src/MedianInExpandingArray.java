import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class MedianInExpandingArray {

	/**
	 * Keeping track of median of an expanding array.
	 * 
	 * the idea is to use two priority queue.
	 * lower half uses the max heap
	 * upper half uses the min heap.
	 * 
	 * insertion O(nLogn)
	 * getMedian O(1);
	 */
	
	private PriorityQueue<Integer> lows, highs;
	private int count;
	
	public MedianInExpandingArray(){
		lows = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
			public int compare(Integer i1, Integer i2){
				return i2.compareTo(i1);//max heap different from natural order.
			}
		});
		
		highs = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
			public int compare(Integer i1, Integer i2){
				return i1.compareTo(i2);//min heap default comparison order.
			}
		});
		count = 0;
	}
	
	public int size(){
		return count;
	}
	
	/**
	 * get Median from the priority queues.
	 * @return
	 * @throws Exception
	 */
	public double getMedian() throws Exception{
		if(count == 0) {
			throw new Exception("Empty Array");
		}
		
		if(count %2 == 0){
			return (double)(highs.peek() + lows.peek())/2;
		}else{
			return lows.peek();//lows always get size bigger or equals to highs -- invariants.
		}
	}
	
	public void addIntToArray(int n){
		if(lows.size() == highs.size()){//size are the same.
			if(lows.isEmpty() && highs.isEmpty()){
				lows.add(n);//add only one to lows.
			}else{
				int med1 = lows.peek();
				int med2 = highs.peek();
				if(n > Math.max(med1, med2)){
					//med2 is the new median.
					//med2 need to be get out and put in lows.
					highs.poll();
					highs.add(n);
					lows.add(med2);
				}else{
					//n < Math.min(med1, med2)
						//med1 is the new median.
						//new median is still in the lows.
						lows.add(n);
					//or 
					//n is in between med1 and med2.
					//n is the new median.
					//add it to the lows.
				}
			}
		}else{ //size is different. lows.size() is always one more.
			int med = lows.peek();
			if(n < med){
				//n and med are the new medians.
				lows.poll();
				lows.add(n);
				highs.add(med);
			}else{
				//add n to highs.
				highs.add(n);
			}
		}
		count++;
	}
	
	public String toString(){
		Integer[] lowsArray = lows.toArray(new Integer[1]);
		Integer[] highsArray = highs.toArray(new Integer[1]);
		List<Integer> combined = new ArrayList<Integer>();
		combined.addAll(Arrays.asList(lowsArray));
		combined.addAll(Arrays.asList(highsArray));
		Collections.sort(combined);
		return combined.toString();
		
	}
	
	public static void main(String[] args){
		MedianInExpandingArray mia = new MedianInExpandingArray();
		try{
			System.out.println(mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		mia.addIntToArray(1);
		try{
			System.out.println("median : " + mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		mia.addIntToArray(3);
		try{
			System.out.println("median : " + mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		mia.addIntToArray(7);
		try{
			System.out.println("median : " + mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		mia.addIntToArray(5);
		try{
			System.out.println("median : " + mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		mia.addIntToArray(2);
		try{
			System.out.println("median : " + mia.getMedian());
		}catch(Exception e){
			System.out.println("empty median");
		}
		System.out.println("array is " + mia.toString());
		System.out.println("Array size " + mia.size());
	}
	
}
