package com.z2.t7;

import java.util.HashSet;

public class LCALink {
	static NodeP[] nodes = new NodeP[9];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NodeP root = initTestBinary();
		
		NodeP a8 = nodes[8];
		NodeP a6 = nodes[6];
		NodeP a5 = nodes[5];
		NodeP a4 = nodes[4];
		NodeP a1 = nodes[1];
		
		
		//int count = matchCount(root, a5, a4);
		//System.out.println(count);
		
		NodeP res = linkTrace(root, a5, a4);
		System.out.println("LCA is: " + res);

		res = linkTrace(root, a6, a4);
		System.out.println("LCA is: " + res);
		
		res = linkTrace(root, a8, a4);
		System.out.println("LCA is: " + res);
		
		res = linkTrace(root, a8, null);
		System.out.println("LCA is: " + res);
		
		System.out.println("---------------");
		
		res = heightDiff(root, a5, a4);
		System.out.println("LCA is: " + res);

		res = heightDiff(root, a6, a4);
		System.out.println("LCA is: " + res);
		
		res = heightDiff(root, a8, a4);
		System.out.println("LCA is: " + res);
		
		res = heightDiff(root, a8, null);
		System.out.println("LCA is: " + res);

		res = heightDiff(root, a5, a1);
		System.out.println("LCA is: " + res);
	}
	
	public static NodeP initTestBinary(){
		nodes[7] = new NodeP(7);
		nodes[4] = new NodeP(4);
		nodes[2] = new NodeP(2);
		nodes[2].setlChild(nodes[7]);
		nodes[2].setrChild(nodes[4]);
		nodes[6] = new NodeP(6);
		nodes[5] = new NodeP(5);
		nodes[5].setlChild(nodes[6]);
		nodes[5].setrChild(nodes[2]);
		
		nodes[0] = new NodeP(0);
		nodes[8] = new NodeP(8);
		nodes[1] = new NodeP(1);
		nodes[1].setlChild(nodes[0]);
		nodes[1].setrChild(nodes[8]);
		
		nodes[3] = new NodeP(3);
		nodes[3].setlChild(nodes[5]);
		nodes[3].setrChild(nodes[1]);
		
		return nodes[3];
	}
	
	public static NodeP linkTrace(NodeP root, NodeP a, NodeP b){
		HashSet<NodeP> set = new HashSet<NodeP>();
		
		while(a != null){
			set.add(a);
			a = a.getParent();
		}
		
		while(b != null){
			if(!set.add(b)){
				return b;
			}
			b = b.getParent();
		}
		return null;
	}
	
	public static NodeP heightDiff(NodeP root, NodeP a, NodeP b){
		int aheight = getDistToRoot(a);
		int bheight = getDistToRoot(b);
		NodeP temp;
		int temph;
		if(aheight > bheight){
			temp = a;
			a = b;
			b = temp;
			//swap Node;
			temph = aheight;
			aheight = bheight;
			bheight = temph;
			//swap height;
		}
		
		int hDiff = bheight - aheight ;
		
		while(b != null && hDiff > 0){
			hDiff --;
			b = b.getParent();
		}
		
		while(b!= null && a!=null){
			if(a.equals(b)){return a;}
			b = b.getParent();
			a = a.getParent();
		}
		
		return null;
		
	}
	
	/**
	 * get distance to root from child node.
	 * @param node
	 * @return
	 */
	public static int getDistToRoot(NodeP node){
		int count = 0;
		while(node != null){
			count ++;
			node = node.getParent();
		}
		return count;
	}
	
	public static NodeP diffRace(NodeP root, NodeP a, NodeP b){
		return null;
	}

}
