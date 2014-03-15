import java.util.Iterator;
import java.util.PriorityQueue;


/**
 * Find top k number in billion number
 * say to 100.
 * if we compare each number in a stream with
 * number in data structure.
 * total time would be O(n*k)
 * 
 * if use priorityQueue (min heap)
 * total time complexity is O(n * logK)
 * 
 * @author zhouzhou
 *
 */
public class TopKNumberInBillion {

	public static void main(String[] args){
		int k = 5;
		int[] a = new int[] {1, 5, 6, 7, 9, 10, -4, 15, 2 , 3, 11};
		topKNumber(a, k);
	}
	
	public static void topKNumber(int[] a, int k){
		
		if(a == null) return;
		if(k <= 0) return;
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		
		Integer curMin;
		int count = 0;
		for(int i=0; i< a.length; i++){
			if(count < k){//heapify the first k number.
				minHeap.add(a[i]);
				count ++;
			}else{//then compare and store.
				curMin = minHeap.poll();
				minHeap.add(Math.max(a[i], curMin));
			}
		}
		
		Iterator<Integer> it = minHeap.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
	}
}
