package memoryVisibility;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

	public static void main(String[] args){
		
		/*int a[] = {
		        4, 3, 8, 5, 2, 1, 6
		    };*/
		Node root = new Node(5);
		root.left = new Node(3);
		root.right = new Node(1);
		root.left.left = new Node(4);
		root.left.right = new Node(8);
		root.right.left = new Node(2);
		root.right.right = new Node(6);
		
		pathSum(root, new ArrayList<Node>(), 7);
		
	}
	public static  void pathSum(Node node, List<Node> pathNodes, int sum){
		if(node == null) return;
		//depth first
		
		pathNodes.add(node);
		
		int tSum = 0;
		for(int i=pathNodes.size()-1; i>=0; i--){
			tSum += pathNodes.get(i).data;
			if(tSum == sum){
				//print the sum
				print(pathNodes, i, pathNodes.size());
			}else if(tSum > sum){
				break;
			}
		}
		
		pathSum(node.left, pathNodes, sum);
		
		pathNodes.remove(node.left); //remove the pathNodes last one so we don't need to copy it.
		
		pathSum(node.right, pathNodes, sum);
	}
	
	public static void print(List<Node> pathNodes, int start, int end){
		for(int i=start; i<end; i++){
			System.out.println(pathNodes.get(i).data);
		}
		System.out.println();
	}
	
	static class Node{
		int data;
		Node left;
		Node right;
		public Node(int data){
			this.data = data;
		}
	}
}
