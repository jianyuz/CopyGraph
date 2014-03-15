package com.z2.t29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * find all the unqiue paths while traversing the triangulated triangle.
 * use depth first search.
 * back tracking using stack.
 * when each new path is found.
 * dump the content in stack into result list.
 * 
 * @author zhouzhou
 *
 */
public class UniquePathsInDiamiondShape {
	public static void main(String[] args){
		Node root = createTestData();
		List<List<Node>> res = uniquePaths(root);
		printPaths(res);
		System.out.println("\n------");
		root = createTestData1();
		res = uniquePaths(root);
		printPaths(res);
	}
	
	public static void printPaths(List<List<Node>> paths){
		for(List<Node> path: paths){
			System.out.println();
			for(Node n: path){
				System.out.print(n.id + ",");
			}
		}
	}
	
	public static Node createTestData(){
		Node root = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		root.addNeighbor(n2);
		n2.addNeighbor(n3);
		n2.addNeighbor(n4);
		
		n3.addNeighbor(n2);
		n4.addNeighbor(n2);
		return root;
	}
	
	
	public static Node createTestData1(){
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);
		
		n1.addNeighbor(n2);
		n2.addNeighbor(n3);
		n2.addNeighbor(n4);
		n3.addNeighbor(n2);
		n3.addNeighbor(n6);
		n4.addNeighbor(n2);
		n4.addNeighbor(n8);
		n5.addNeighbor(n6);
		n6.addNeighbor(n3);
		n6.addNeighbor(n5);
		n6.addNeighbor(n7);
		n7.addNeighbor(n6);
		n7.addNeighbor(n8);
		n8.addNeighbor(n4);
		n8.addNeighbor(n7);
		n8.addNeighbor(n9);
		n9.addNeighbor(n8);
		return n1;
	}
	
	public static List<List<Node>> uniquePaths(Node root){
		List<List<Node>> paths = new ArrayList<List<Node>>();
		Map<Node, Integer> neighborPos = new HashMap<Node, Integer>();
		
		Node curNode, oldNode;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.visited = true;
		
		List<Node> path;
		List<Node> nList;
		
		boolean allVisited;
		boolean newPath;
		while(!stack.isEmpty()){
			curNode = stack.peek();
			allVisited = false;
			newPath = false;
			while (!allVisited) {
				nList = curNode.neighbors;
				allVisited = true;

				Integer posInt = neighborPos.get(curNode);
				int startPos = 0;
				if(null != posInt){
					startPos = posInt;
				}
				for(int i = startPos; i < nList.size(); i++){
					Node node = nList.get(startPos++);
					neighborPos.put(curNode, new Integer(startPos));
					if(!node.visited){
						stack.push(node);
						node.visited = true;
						newPath = true;
						allVisited = false;
						curNode = node;
						break;
					}
				}
			}
			
			if(newPath){
				Node[] pathNodes = stack.toArray(new Node[] {});
				path = Arrays.asList(pathNodes);
				paths.add(path);
			}
			oldNode = stack.pop();
			oldNode.visited = false;
			neighborPos.remove(curNode);
		
		}
		
		return paths;
		
	}

}
