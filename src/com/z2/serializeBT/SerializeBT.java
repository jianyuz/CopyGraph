package com.z2.serializeBT;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class SerializeBT {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fw = null;
		DataOutputStream dos = null;
		try {
			File outputFile = new File("/Users/zhouzhou/Documents/testOut2.txt");
			fw = new FileOutputStream(outputFile);
			dos = new DataOutputStream(fw);
			Node root = constructTestTree();
			serializeBT(root, dos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (fw != null) {
				fw.close();
			}
		}
		
		try{
			File inputFile = new File("/Users/zhouzhou/Documents/testOut2.txt");
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line  = br.readLine();
			if(null != line){
				StringTokenizer st = new StringTokenizer(line);
				Node root1 = reconstructBT(st);
				preOrderBST(root1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void preOrderBST(Node node)
			throws IOException {
		// use preOrder traversal.
		// output parent before the child.
		// 30, 20, 10, 40, 35, 50.
		if(node == null) return;
		System.out.print(node.data + " ");
		if (node.left != null) {
			preOrderBST(node.left);
		}
		if (node.right != null) {
			preOrderBST(node.right);
		}

	}
	public static void serializeBT(Node node, DataOutputStream dos) throws IOException{
		if(node == null) {
			dos.writeChars("# ");
			return;
		}else{
			dos.writeChars(node.data + " ");
		}
		
		serializeBT(node.left, dos);
		serializeBT(node.right, dos);
		
		
	}
	
	public static Node reconstructBT(StringTokenizer st) throws IOException{
		if(st == null){
			return null;
		}
		if(st.hasMoreTokens()){
			String token = st.nextToken();
			if(!"#".equalsIgnoreCase(token.trim())){
				Node node = new Node(token);
				node.left = reconstructBT(st);
				node.right = reconstructBT(st);
				return node;
			}
		}
		return null;
	}
	
	public static Node constructTestTree() {
		Node root = new Node(30);
		root.left = new Node(10);
		root.right = new Node(20);
		root.left.left = new Node(50);
		root.right.left = new Node(45);
		root.right.right = new Node(35);
		return root;
	}

}
