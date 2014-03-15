
public class CheckTreeBalanced {

	
	public boolean isBalanced(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        HeightBalance hb =  doBalanced(root);
        return hb.isBalanced;
        
    }
    
    public HeightBalance doBalanced (TreeNode root){
        if(root == null){
            return new HeightBalance(true, 0);
        }
        
        if(root.left == null && root.right == null){
            return new HeightBalance(true, 1);
        }
        
        HeightBalance lb = doBalanced(root.left);
        HeightBalance rb = doBalanced(root.right);
        
        long h = Math.max(lb.height, rb.height) + 1;
        boolean b = lb.isBalanced && rb.isBalanced && Math.abs(lb.height - rb.height) <=1;
        
        HeightBalance fb = new HeightBalance(b, h);
        return fb;
    }
    
    static class HeightBalance{
        
        boolean isBalanced;
        long height;
        
        HeightBalance(boolean b, long h){
            this.isBalanced = b;
            this.height = h;
        }
    }
    
    static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
    
    
    /**
     * another version
     * use -1 to represent balanced or not balanced flag.
     * if left or right are unbalanced, we don't have to know the actually height
     * cause that would be the result.
     * otherwise, we trace the height and height difference level by level.
     * 
     * only this pass the bigger validation.
     * @param root
     * @return
     */
    
    public boolean isBalanced1(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return doBalanced1(root) != -1;
    }
    
    public int doBalanced1 (TreeNode root){
        if(root == null){
            return 0;
        }
        
        int lHeight = doBalanced1(root.left);
        int rHeight = doBalanced1(root.right);
        
        if(lHeight == -1 || rHeight == -1) return -1;
        
        int h = Math.max(lHeight, rHeight) + 1;
        
        if( Math.abs(lHeight - rHeight) > 1) return -1;
        else return h;
    }
    
   
}
