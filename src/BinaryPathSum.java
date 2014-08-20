import java.util.ArrayList;

/**
 * Binary tree issue. think of recursion first.
 * 
 * @author zhouzhou
 *
 */
public class BinaryPathSum {

	/**
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
	 * key here is about the termination condition.
	 * terminal node needs to be a leaf.
	 * which doesn't have any child node, left or right.
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null){
            return false;
        } 
        
        if(root.left == null && root.right == null ){
            return (sum == root.val);
        }
        
        
        int diff = sum - root.val;
        
        return hasPathSum(root.left, diff) || hasPathSum(root.right, diff);
        
    }
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
	
	//still a recursive solution but returns the path list in each step.
	//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
    //node for empty path sum return empty array.
	//idea here is to find the path down to the leaf.
	//add one by one add the node back to the list front.
	//merge the list from left and right child.
	
	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(root == null){
            return new ArrayList<ArrayList<Integer>>();
        }
        
        if(root.left == null && root.right == null){
            if(sum == root.val){
                ArrayList<Integer> res = new ArrayList<Integer>();
                res.add(0, root.val);
                ArrayList<ArrayList<Integer>> listRes = new ArrayList<ArrayList<Integer>>();
                listRes.add(res);
                return listRes;
            }else{
                return new ArrayList<ArrayList<Integer>>();
            }
        }
        
        int diff = sum - root.val;
        
        ArrayList<ArrayList<Integer>> leftList = pathSum(root.left, diff); 
        ArrayList<ArrayList<Integer>> rightList = pathSum(root.right, diff);
        if(leftList.size() > 0){
            for(ArrayList<Integer> list : leftList){
                list.add(0, root.val);
            }
        }
        
        if(rightList.size() > 0){
            for(ArrayList<Integer> list: rightList){
                list.add(0, root.val);
            }
        }
        
       
        leftList.addAll(rightList);
        return leftList;
    }

}
