package com.z2.TreeGraph;

import java.util.ArrayList;
import java.util.List;

public class TNode {

	private List<TNode> neighbors;
	private String label;
	private State state;
	
	public TNode(String l){
		this.label = l;
		this.neighbors = new ArrayList<TNode>();
		this.state = State.unvisited;
	}
	
	public List<TNode> getAdjacent(){
		return this.neighbors;
	}
	
	public TNode addNeighbors(TNode n){
		this.neighbors.add(n);
		return this;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public State getState(){
		return this.state;
	}
	
	public void setState(State visited){
		this.state = visited;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
