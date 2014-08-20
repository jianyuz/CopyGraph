package puzzle;

import java.util.ArrayList;
import java.util.List;

public class Node <P, M>{

	final P p;
	final M m;
	final Node<P, M> prev;
	public Node(P p, M m, Node<P, M> prev){
		this.p = p;
		this.m = m;
		this.prev = prev;
	}
	
	List<M> asMoveList(){
		List<M> list = new ArrayList<M>();
		for(Node<P, M> node=this; node.m != null; node = node.prev){
			list.add(0, node.m);
		}
		return list;
	}
}
