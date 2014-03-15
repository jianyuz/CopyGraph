import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * merge k sorted list.
 * three approach
 * n way comparison
 * priority queue based
 * 2 2 merge
 * n * lgk.
 * @author zhouzhou
 *
 */
public class MergeKSortedList {

	static class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			this.val = v;
			next = null;
		}
	}
	
	
	public static void main(String[] args){
		ListNode empty = new ListNode(1);
		ArrayList<ListNode> list = new ArrayList<ListNode>();
		list.add(empty);
		
		ListNode res = mergeKLists(list);
	}
	public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(lists == null || lists.size() == 0) return null;
        
        Comparator<ListNode> comp = new Comparator<ListNode>(){//parameterized Comparator
            public int compare(ListNode l1, ListNode l2){
                if(l1.val > l2.val){
                    return 1;
                }else if(l1.val == l2.val){
                    return 0;
                }else{
                    return -1;
                }
            }
        };
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), comp);
        //heap is queue in java, priority queue
        
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        
        for(int i=0; i< lists.size(); i++){//we don't care if fill in k element in queuey.
            ListNode tmp = lists.get(i);//just all the list head. can't offer empty element.
            if(tmp != null){
                minHeap.offer(tmp);
            }
        }
        
        while(!minHeap.isEmpty()){
            ListNode tmp = minHeap.poll();
            res.next = tmp;
            res = res.next;
            
            //everything is alreay in the heap.
            //refill the heap with next element after listNode.
            if(tmp.next != null){
                minHeap.offer(tmp.next);//fill up with non empty elements.
            }
        }
        
        return dummy.next;
    }
	/**
	 * This algorithm use n * k
	 * k is the number of list.
	 * n is the total number of nodes in all lists.
	 * to speed up comparison, we can use a minHeap.
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists1(ArrayList<ListNode> lists) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(lists == null || lists.size() == 0) return null;
        
        ListNode dummy = new ListNode(0);//use dummy head.
        
        ListNode res = dummy;
        
        ListNode node = null;
        
        while(true){
            int min = Integer.MAX_VALUE;//keep min minnode and min index.
            ListNode minNode = null;
            int minIndex = -1;
            for(int i=0; i< lists.size(); i++){
                node = lists.get(i);
                if(node != null && node.val < min){
                    min = node.val;
                    minNode = node;
                    minIndex = i;
                }
            }
            if(minNode != null){
                res.next = minNode;//link result.
                res = minNode;//advance res pointer last node.
                lists.set(minIndex, minNode.next); //set list candidates to new head.
            }else{
                break;//break if every node is empty. run out of lists.
            }
        }
        return dummy.next; //return what' behind dummy
    }
}
