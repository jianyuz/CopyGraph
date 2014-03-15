import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * find the most frequent k ids from a list.
 * what if the list is very big.
 * out put freuqency value of each id to a file.
 * use priority queue to get first k.
 * nlogk.
 * 
 * or use a self balancing tree to keep track of the k element.
 * minimum height of such tree is log2n
 * in this case logk for insertion and deletion
 * nlogk in total too.
 * 
 * avl tree, maintain height difference not bigger than 0ne.
 * tree rotation.
 * one rotation
 * and two rotation
 * deletion.
 * replacement and followed by rotation.
 * 
 * red black tree
 * black tree root.
 * nil leaves are black.
 * for single root.
 * every path to leaves contains the same number of black nodes.
 * Guarantee that no path of more than twice of the shortest path.
 * 
 * 
 * one other way,
 * transform the list into a binary heap
 * and traverse it with bFS using priority queue.
 * n + klogk.
 * 
 * skiplists logn solution.
 * data structure to store sorted list itmes.using hierachly of linked list connecting increasingly
 * sparse subsequence of items.
 * 
 * @author zhouzhou
 *
 */

public class FirstKFrequentIDs {
	
	public static void main(String[] args){
		int[] ids = new int[]{
				1, 3, 4, 5, 6, 7, 3, 2, 5, 7, 1, 4, 4, 3, 2, 7, 7, 9 
		};
		
		List<Integer> res = KFrequentIDs1(ids, 3);
		System.out.println(res);
	}
	
	/**Priority queue version.
	 * 
	 * @param IDs
	 * @param k
	 * @return
	 */
	public static List<Integer> KFrequentIDs1(int[] IDs, int k){
		if(IDs == null || IDs.length == 0) return null;
		
		Map<Integer, FreqEntry> freqMap = new HashMap<Integer, FreqEntry>();
		for(int i=0; i< IDs.length; i++){
			if(freqMap.get(IDs[i]) == null){
				freqMap.put(IDs[i], new FreqEntry(IDs[i]));
			}else{
				freqMap.get(IDs[i]).count ++;
			}
		}
		FreqEntry[] values = freqMap.values().toArray(new FreqEntry[1]);
		
		for(FreqEntry e: values){
			System.out.println("id " + e.ID + " count " + e.count);
		}
		
		PriorityQueue<FreqEntry> queue = new PriorityQueue<FreqEntry>(k, new Comparator<FreqEntry>(){
			public int compare(FreqEntry a, FreqEntry b){
				return a.count - b.count;
			}
		});//min queue.
		
		for(int i=0; i< values.length; i++){
			if(queue.size() < k){
				queue.add(values[i]);
			}else{
				FreqEntry fe = queue.peek();
				if(fe.count < values[i].count){
					queue.poll();
					queue.add(values[i]);
				}
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();
		//output the items in the queue.
		FreqEntry[] entries = queue.toArray(new FreqEntry[1]);
		for(int i=0; i< entries.length; i++){
			res.add(entries[i].ID);
		}
		return res;
		
	}
	
	/**
	 * 
	 * @param IDs
	 * @param k
	 * @return
	 */
	public static List<Integer> KFrequentIDs(int[] IDs, int k){
		if(IDs == null) return null;
		
		Map<Integer, FreqEntry> freqMap = new HashMap<Integer, FreqEntry>();
		for(int i=0; i< IDs.length; i++){
			if(freqMap.get(IDs[i]) == null){
				freqMap.put(IDs[i], new FreqEntry(IDs[i]));
			}else{
				freqMap.get(IDs[i]).count ++;
			}
		}
		
		FreqEntry[] values = freqMap.values().toArray(new FreqEntry[1]);
		
		for(FreqEntry e: values){
			System.out.println("id " + e.ID + " count " + e.count);
		}
		
		//selection algorithm to find out the top K elements
		//partition function to get correct Index of pivot.
		//if == k, return
		//else adjust left or right index accordingly
		
		//get the Kth entry (top).
		FreqEntry entry = select(values, 0, values.length -1, k);
		
		//divide the list based on the Kth entry.
		List<Integer> res = new ArrayList<Integer>();
		
		System.out.println("k pivot count " + entry.count + " id " + entry.ID);
		int count = 0;
		for(FreqEntry fe: values){
			if(fe.count > entry.count ){
				res.add(fe.ID);
				count ++;
			}
		}
		
		//add the compliment element that has value equals.
		for(FreqEntry fe: values){
			if(fe.count == entry.count && count < k){
				res.add(fe.ID);
				count ++;
			}
		}
		return res;
		
	}
	
	/**
	 * partial quick sort.
	 * we already know where the pivot here.
	 * bad pivot selection degrade to selection sort. O(n2)
	 * expected linear performance.
	 * median of medain quick select algorithm
	 * O(n) performance.
	 * @param values
	 * @param left
	 * @param right
	 * @param k
	 * @return
	 */
	public static FreqEntry select(FreqEntry[] values, int left, int right, int k){
		while(left <= right){
			//int pivotIndex = left + (right - left)/2;
			int pivotIndex = medianOfMedians(values, left, right);
			System.out.println("left " + left + " right " + right + " pivot " + pivotIndex);
			int newIndex = partition(values, left, right, pivotIndex);
			int distToRight = right - newIndex + 1;
			if(distToRight == k){
				return values[newIndex];
			}else if(distToRight > k){
				left = newIndex + 1;
			}else{
				k = k - distToRight;
				right = newIndex - 1;
			}
		}
		return null;
	}
	
	
	public static int medianOfMedians(FreqEntry[] values, int left, int right){
		//group into 5 elements group
		int groups = (right-left)/5;
		FreqEntry[] medians = new FreqEntry[groups+1];
		int[] medianIndices = new int[groups+1];
		for(int i=0; i<= groups; i++){
			int subLeft = left + i * 5;
			int subRight = subLeft + 4;
			if(subRight > right){
				subRight = right;
			}
			int medianIndex = medianInGroup(values, subLeft, subRight);
			//swap(values, left + i, medianIndex);//move the median to the beginning
			medians[i] = values[medianIndex];
			medianIndices[i] = medianIndex;
		}
		//return medianInGroup(values, left, left + groups);
		int mIndex = medianInGroup(medians, 0, groups);
		return medianIndices[mIndex];
	}
	
	public static int medianInGroup(FreqEntry[] values, int left, int right){
		//use the method like selection sort.
		int count = right - left + 1;
		
		if(count == 1 || count == 2){
			return left;
		}else if(count == 3){
			FreqEntry a  = values[left];
			FreqEntry b = values[right];
			FreqEntry c = values[left + 1];
			if(a.count < b.count){
				if(a.count > c.count){
					return left;
				}else if(c.count > b.count){
					return right;
				}else{
					return left + 1;
				}
			}else{// a > b
				if(b.count > c.count){
					return right;
				}else if(c.count > a.count){
					return left;
				}else{
					return left + 1;
				}
				
			}
		}else if(count == 4){
			FreqEntry a  = values[left];
			FreqEntry b = values[left + 1];
			FreqEntry c = values[left + 2];
			FreqEntry d = values[left + 3];
			
			FreqEntry less1, more1;
			int less1Index, more1Index;
			if(a.count < b.count){//compare 1
				less1 = a;
				less1Index = left;
				more1 = b;
				more1Index = left + 1;
			}else{
				less1 = b;
				less1Index = left+1;
				more1 = a;
				more1Index = left;
			}
			FreqEntry less2, more2;
			int less2Index, more2Index;
			if(c.count < d.count){//compre 2
				less2 = c;
				less2Index = left + 2;
				more2 = d;
				more2Index = left + 3;
			}else{
				less2 = d;
				less2Index = left + 3;
				more2 = c;
				more2Index = left + 2;
			}
			
			if(less1.count < less2.count){//get rid of less1
				if(less2.count < more1.count){
					return less2Index;
				}else{
					return more1Index;
				}
			}else{//get rid of less2. compare with less1.
				if(less1.count < more2.count){
					return less1Index;
				}else{
					return more2Index;
				}
				
			}//compare 5 and 6.
			
			
		}else{ //count >= 4;
			FreqEntry a  = values[left];
			FreqEntry b = values[left + 1];
			FreqEntry c = values[left + 2];
			FreqEntry d = values[left + 3];
			FreqEntry e = values[left + 4];
			
			FreqEntry less1, more1;
			int less1Index, more1Index;
			if(a.count < b.count){//compare 1
				less1 = a;
				less1Index = left;
				more1 = b;
				more1Index = left + 1;
			}else{
				less1 = b;
				less1Index = left+1;
				more1 = a;
				more1Index = left;
			}
			FreqEntry less2, more2;
			int less2Index, more2Index;
			if(c.count < d.count){//compre 2
				less2 = c;
				less2Index = left + 2;
				more2 = d;
				more2Index = left + 3;
			}else{
				less2 = d;
				less2Index = left + 3;
				more2 = c;
				more2Index = left + 2;
			}
			
			if(less1.count < less2.count){//replace less1.
				if(e.count < more1.count){
					less1 = e;
					less1Index = left + 4;
				}else{
					less1 = more1;
					less1Index = more1Index;
					more1 = e;
					more1Index = left + 4;
				}
			}else{//replace less2
				if(e.count < more2.count){
					less2 = e;
					less2Index = left + 4;
				}else{
					less2 = more2;
					less2Index = more2Index;
					more2 = e;
					more2Index = left + 4;
				}
				
			}//compare 3 and 4
			
			
			if(less1.count < less2.count){//get rid of less1
				if(less2.count < more1.count){
					return less2Index;
				}else{
					return more1Index;
				}
			}else{//get rid of less2. compare with less1.
				if(less1.count < more2.count){
					return less1Index;
				}else{
					return more2Index;
				}
				
			}//compare 5 and 6.
			
		}
		
	}
	/**
	 * find the correct index for the pivot index.
	 * it is meant for find the top k elements.
	 * @param values
	 * @param left
	 * @param right
	 * @param pivot
	 * @return
	 */
	public static int partition(FreqEntry[] values, int left, int right, int pivot){
		//swap pivot entry with the end element.
		//System.out.println("pivot value " + values[pivot].count);
		swap(values, pivot, left);
		
		
		int storeIndex = right;
		for(int i = right; i > left; i--){
			if(values[i].compareTo(values[left]) > 0){
				//swap it with storeIndex element.
				swap(values, i, storeIndex);
				storeIndex --; // increment storeIndex.
			}
		}
		//afterward, put pivot back to its location.
		//System.out.println("left pivot value " + values[left].count);
		swap(values, storeIndex, left);
		//System.out.println("pivot " + pivot + " store Index " + storeIndex + " pivot value " + values[storeIndex].count);
		return storeIndex; //new pivot index;
		
	}
	
	/**
	 * java pass by value. the following is the correct way to swap the 
	 * array elements.
	 * @param array
	 * @param index1
	 * @param index2
	 */
	public static void swap(FreqEntry[] array, int index1 , int index2){
		FreqEntry tmp;
		
		tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
	
	/**
	 * comparable entry use comparable template.
	 * @author zhouzhou
	 *
	 */
	static class FreqEntry implements Comparable<FreqEntry>{
		int ID;
		int count;
		
		public FreqEntry(int ID){
			this(ID, 1);
		}
		
		public FreqEntry(int ID, int count){
			this.ID = ID;
			this.count = count;
		}

		@Override
		public int compareTo(FreqEntry o) {
			return this.count - o.count;
		}
		
		
	}

}
