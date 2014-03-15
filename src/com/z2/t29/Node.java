package com.z2.t29;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public int id;
	public List<Node> neighbors;
	public boolean visited;
	
	public Node(int id){
		this.id = id;
		this.neighbors = new ArrayList<Node>();
		this.visited = false;
	}
	
	public void addNeighbor(Node n){
		this.neighbors.add(n);
	}

}
