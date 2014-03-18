package memoryVisibility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Route {
	
	public static void main(String[] args){
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		
		n1.addNeighbor(n2);
		n1.addNeighbor(n3);
		n2.addNeighbor(n1);
		n2.addNeighbor(n4);
		n5.addNeighbor(n4);
		
		Route route = new Route();
		System.out.println(route.reachable(n1, n5));
	}
	public boolean reachable(Node n1, Node n2){
		Queue<Node> q = new LinkedList<Node>();
		boolean found = false;
		q.add(n1);
		
		while(!q.isEmpty()){
			Node node = q.poll();
			if(node == n2){
				found = true;
				break;
			}
			
			node.visited = true;
			for(Node n: node.neighbors ){
				if(!n.visited)
					q.add(n);
			}
		}
		return found;
	}
	
	
	static class Node{
		int data;
		List<Node> neighbors;
		boolean visited;
		
		Node(int data){
			this.data = data;
			neighbors = new ArrayList<Node>();
			visited = false;
		}
		
		void addNeighbor(Node n){
			this.neighbors.add(n);
		}
		
	}

}
