package com.z2.TreeGraph;

import java.util.ArrayList;
import java.util.List;

public class Route {

	static TNode start;
	static TNode end;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		buildGraph();
		System.out.println(search(start, end));
	}
	
	private static boolean search(TNode s, TNode e) {
		List<TNode> q = new ArrayList<TNode>();
		s.setState(State.visiting);
		q.add(s);
		//add first node.
		TNode current;
		while(!q.isEmpty()){
			current = q.remove(0);			
			if(current == e){
				return true;
			}
			current.setState(State.visited);
			
			for(TNode n: current.getAdjacent()){
				if(n.getState() == State.unvisited){
					q.add(n);
					n.setState(State.visiting);
				}
			}
		}
		return false;
	}

	public static void buildGraph(){
		TNode root = new TNode("a");
		
		TNode b = new TNode("b");
		
		TNode c= new TNode("c");
		
		TNode d = new TNode("d");
		
		TNode e = new TNode("e");
		
		TNode f = new TNode("f");
		
		root.addNeighbors(b).addNeighbors(c);
		b.addNeighbors(d);
		c.addNeighbors(d).addNeighbors(f);
		d.addNeighbors(f);
		f.addNeighbors(e);
		e.addNeighbors(b).addNeighbors(root);
		
		start = root;
		end = e;
	}

}
