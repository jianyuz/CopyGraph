
/**
 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.


 * @author zhouzhou
 *
 */
public class PopulateNextRightNode {

	public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null) return;
        if(root.left ==null && root.right == null){
            root.next = null;
            return;
        }
        
        connect(root.left);
        connect(root.right);
        
        TreeLinkNode left = root.left;
        TreeLinkNode right = root.right;
        while(left != null && right != null){
            left.next = right;
            left = left.right;
            right = right.left;
        }
        
        root.next = null;
    }
	
	/**
	 * iterative version.
	 * keep track of start node of each level.
	 * utilize the parent info to link the bottom nodes across.
	 * 
	 * complexity O(n)
	 * constant space
	 * 
	 * @param root
	 */
	public void connect1(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null) return;
        TreeLinkNode leftEdge = root;
        while(leftEdge != null){
            TreeLinkNode curNode = leftEdge;
            while(curNode != null){
                if(curNode.left != null && curNode.right != null)
                    curNode.left.next = curNode.right;
                if(curNode.next != null && curNode.right !=null){
                    curNode.right.next = curNode.next.left;
                }
                curNode = curNode.next;
            }
            leftEdge = leftEdge.left;
        }
    }
	
	/** this works for incomplet binary 
	 * tree
	 * @param root
	 */
	 public void connect2(TreeLinkNode root) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        //iterate through level by level
	        //suppose each level we have got the next pointer correctly set up.
	        //the pointer to the first node of each level is our queue 
	        //for setting up links at next level.
	        
	        if(root == null) return ;
	        
	        TreeLinkNode lastLevelCur = root;
	        TreeLinkNode curLevelHead;
	        TreeLinkNode curLevelPrev;
	        while( lastLevelCur != null){
	            curLevelHead = null;
	            curLevelPrev = null;
	            while(lastLevelCur != null){
	                if(curLevelHead == null){
	                    curLevelHead = (lastLevelCur.left == null)?lastLevelCur.right: lastLevelCur.left;
	                }
	                
	                if(lastLevelCur.left != null){
	                    if(curLevelPrev != null) {
	                        curLevelPrev.next = lastLevelCur.left;
	                    }//first set next for prev.
	                    curLevelPrev = lastLevelCur.left; //then update prev pointer.‰
	                }
	                
	                if(lastLevelCur.right != null){
	                    if(curLevelPrev != null){
	                        curLevelPrev.next = lastLevelCur.right;
	                    }
	                    curLevelPrev = lastLevelCur.right;
	                }
	                lastLevelCur = lastLevelCur.next;
	            }
	            lastLevelCur = curLevelHead;
	        }
	        
	    }
	
	static class TreeLinkNode{
		int val;
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		
	}
}
