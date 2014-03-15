package com.z2.serializeBST;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.z2.zigzagprint.Node;

/**
 * serialize bst doesn't need space holder for empty node.
 * ARIN american registration of internet number
 * who is
 * traceroute.
 * IPlocation db.
 * fin a point
 * min distance to all the points
 * middle point.
 * do search around the middle point.
 * 8 points around.
 * decrease the test radius
 * 
 * @author zhouzhou
 *
 */
public class SerializeBST {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fw = null;
		DataOutputStream dos = null;
		try {
			File outputFile = new File("/Users/zhouzhou/Documents/testOut1.txt");
			fw = new FileOutputStream(outputFile);
			dos = new DataOutputStream(fw);
			Node root = constructTestTree();
			serializeBST(root, dos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (fw != null) {
				fw.close();
			}
		}
		
		System.out.println("finished serialization. reading back ...");
		FileInputStream fr = null;
		DataInputStream dis = null;
		try{
			File readInFile = new File("/Users/zhouzhou/Documents/testOut1.txt");
			fr = new FileInputStream(readInFile);
			dis = new DataInputStream(fr);
			Node root = rebuildBST(dis);
			preOrderBST(root);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(dis != null){
				dis.close();
			}
		}
		
		
	}

	public static Node constructTestTree() {
		Node root = new Node(30);
		root.left = new Node(20);
		root.right = new Node(40);
		root.left.left = new Node(10);
		root.left.right = new Node(25);
		root.left.right.left = new Node(24);
		root.left.right.right = new Node(26);
		root.right.left = new Node(35);
		root.right.right = new Node(50);
		root.right.left.left = new Node(33);
		root.right.left.right= new Node(37);
		//30 20 10 25 24 26 40 35 33 37 50 
		return root;
	}

	public static void serializeBST(Node node, DataOutputStream fw)
			throws IOException {
		// use preOrder traversal.
		// output parent before the child.
		//30 20 10 25 24 26 40 35 33 37 50 
		fw.writeInt(node.data);
		if (node.left != null) {
			serializeBST(node.left, fw);
		}
		if (node.right != null) {
			serializeBST(node.right, fw);
		}

	}
	
	
	public static void preOrderBST(Node node)
			throws IOException {
		// use preOrder traversal.
		// output parent before the child.
		// 30 20 10 25 24 26 40 35 33 37 50 
		System.out.print(node.data + " ");
		if (node.left != null) {
			preOrderBST(node.left);
		}
		if (node.right != null) {
			preOrderBST(node.right);
		}

	}
	
	/**
	 * pass root node in.
	 * then determine which side of tree next data is insert into
	 * @param dis
	 * @param min
	 * @param max
	 * @param node
	 * @return
	 * @throws IOException
	 */
	public static int rebuildBST(DataInputStream dis, int min, int max, Node node) throws IOException{
		int data;
		try{
			data = dis.readInt();
			if(data >= min && data <= node.data){
				node.left = new Node(data);
				data =  rebuildBST(dis, min, node.data, node.left);
			}
			
			if(data > node.data && data<=max){
				node.right = new Node(data);
				data = rebuildBST(dis, node.data, max, node.right);
			}
			
			return data;//bubble up data out of current range.
			
		}catch(EOFException e){
			return -1;//caller will ignore this. because al the data are positive.
		}
		
	}

	public static Node rebuildBST(DataInputStream dis){
		int data ;
		Node node = null;
		try{
			data = dis.readInt();
			node = new Node(data);
			rebuildBST(dis, Integer.MIN_VALUE, Integer.MAX_VALUE, node);
		}catch(EOFException e){
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return node;
	}
}
