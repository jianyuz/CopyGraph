/**
 * log(n) performance
 * build in layers.
 * each element has the probability P to be in next layer.
 * the list itself is sorted.
 * 
 * performance on finding an element.
 * number of movies from up to down.
 * at most LogN levels.
 * number of horizontal moves.
 * at most logN moves + 1;
 * 
 * search operation.
 * start from head elements.
 * which appears in all the list.
 * proceed to searc elelment until the element is greater or equal to the targe.
 * if equal, found.
 * otherwise, retrurn to previous element 
 * and drop down vertically to next lower level list.
 * 
 * two level linked list
 * search cost 
 * len of L1 + len of L2/ len of L1.
 * minimization.
 * derirative.
 * square root of n.
 * k k sauare root of n
 * k = logn
 * 2logn
 * 
 * insert operation.
 * go through the bottom list. 
 * find the position and insert it.
 * always insert into bottom list.
 * then determine the chance to promote to upeer level.
 * use coin flip.
 * flip coin all the way to the top.
 * head elements should start all the lists at various levels.
 * doesn't the inserted element got promoted indefinitely.
 * O(n) worst?
 * 
 * 
 * delete remove it from all the lists that contains it.
 * O(n) worst?
 * 
 * how many levels can elements got promoted.
 * P <= N * probability of element promoted cLogN times.
 * 1/n to power of c-1
 * chances are small with high probbability it doesn't go up more than clogn.
 * 
 * @author zhouzhou
 *
 */
public class SkipList<E extends Comparable<? super E>> {
	private static final double P = 0.5;
	private static final int MAX_LEVEL = 10;
	
	public static int randomLevel(){
		int level  = (int)(Math.log(1. - Math.random())/Math.log(1. - P));
		return Math.max(level, MAX_LEVEL);
	}
	
	public final SkipNode<E> header = new SkipNode<E>(MAX_LEVEL, null);//header node.
	public int level = 0; //current level;
	
	public void insert(E e){
		SkipNode<E> current = header;
		SkipNode<E>[] update = new SkipNode[MAX_LEVEL +1]; //list of nodes need to be updated.
		
		for(int i= level; i>=0; i--){
			while(current.next[i] != null && current.next[i].value.compareTo(e) < 0){
				current = current.next[i];
			}
			update[i] = current; //the node to be updated at current level.
		}
		current = current.next[0];
		
		if(current == null || !current.value.equals(e)){//end of list or value doesn't exist.
			int l = randomLevel();
			if(l > level){
				for(int i= l; i>level; i--){
					update[i] = header;
				}
				level = l;//udate current level;
			}
			//insert the node.
			current = new SkipNode<E>(l, e);
			for(int i=0; i<= l;  i++ ){
				current.next[i] = update[i].next[i];
				update[i].next[i] = current;
			}
		}
		
	}
	
	public boolean contains(E e){
		SkipNode<E> current = header;
		for(int i= level; i>=0; i--){
			while(current.next[i] != null && current.next[i].value.compareTo(e) < 0){
				current = current.next[i];
			}
		}
		//fond the node before.
		current = current.next[0];
		return (current != null) && current.value.equals(e);
	}
	
	public void delete(E e){
		SkipNode<E> current = header;
		SkipNode<E> [] update = new SkipNode[MAX_LEVEL +1];//track update to all levels.
		for(int i = level; i>=0; i--){
			while(current.next[i] != null && current.next[i].value.compareTo(e) < 0){
				current = current.next[i];
			}
			update[i] = current;
		}
		current = current.next[0];
		
		//check if the value is the same.
		if(current.value.equals(e)){
			//remove the node from all the lists.
			for(int i=0; i<=level; i++){//remove from bottom up.
				if(update[i].next[i] != current) break;
				update[i].next[i] = current.next[i];
			}
			//decrese the list level;
			while(level > 0 && header.next[level] == null){
				level --;
			}
		}
	}
	
	static class SkipNode<E extends Comparable<? super E>>{
		public final E value;
		public final SkipNode<E>[] next;
		
		public SkipNode(int level, E val){
			this.value = val;
			this.next = new SkipNode[level+1];
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    SkipNode<E> x = header.next[0];
	    while (x != null) {
	        sb.append(x.value);
	        x = x.next[0];
	        if (x != null)
	            sb.append(",");
	    }    
	    sb.append("}");
	    return sb.toString();
	}
	public static void main(String[] args){
		SkipList<Integer > sl = new SkipList<Integer>();
		System.out.println(sl);
		
		sl.insert(5);
		sl.insert(10);
		sl.insert(7);
		sl.insert(7);
		sl.insert(6);
		
		if(sl.contains(7)){
			System.out.println("7 is in list");
		}
		
		System.out.println(sl);
		sl.delete(7);
		
		System.out.println(sl);
		if(!sl.contains(7)){
			System.out.println("7 has been deleted");
		}
		
	}
}
