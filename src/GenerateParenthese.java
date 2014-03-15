import java.util.ArrayList;

/**
 * depth first generation with back tracking.
 * 
 * @author zhouzhou
 *
 */
public class GenerateParenthese {

	public static void main(String[] args){
		ArrayList<String> res = generateParenthesis(3);
		System.out.println(res.toString());
	}
	public static ArrayList<String> generateParenthesis(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<String> res = new ArrayList<String>();
        if(n <= 0){
            return res;
        }
        
        gen(n, 1, 0, res, new StringBuilder());
        return res;
        
    }
    
    public static void gen(int n, int np, int nc, ArrayList<String> res, StringBuilder sb){
        sb.append("(");
        int allowed = np - nc;
        if(np == n){//add all left.
            for(int j=1; j<= allowed; j++){
                    sb.append(")");
            }           
            res.add(sb.toString());//add it to result
            sb.setLength(sb.length() - allowed -1); //undo all the effects.
            return; //got one combination done.
        }else{
            for(int i=0; i<= allowed; i++){
                //can put from 0 to numOfClosing bracktes here.
                for(int j=1; j<= i; j++){
                    sb.append(")");
                }//put various ).
                gen(n, np+1, nc + i, res, sb); //recursion.
                sb.setLength(sb.length() - i );//recover the added closing paren
            }
        }
        sb.setLength(sb.length() - 1); //recover added (
    }
    
    public ArrayList<String> generateParenthesis1(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<String> res = new ArrayList<String>();
        if(n <=0) return res;
        
        gen(n, 0, 0, "", res);
        return res;
    }
    
    /**
     * recursive solution
     * toal catalan number 1/n+1 C 2n n
     * C2n n - C2n n+1
     * number of l paren so far
     * num of r pren so far.
     * 
     * recursive solution.
     * if (l not = n
     * add one (
     * if r < l
     * we can add )
     * stop when number of left right both equal to n.
     * 
     */
    public void gen(int n, int l, int r, String s, ArrayList<String> res){
        if(l == n && r == n){
            res.add(s);
            return;
        }
        
        if(l < n){
            gen(n, l+1, r, s+"(", res);    
        }
        
        if(r < l){
            gen(n, l, r+1, s+")", res);
        }
    }
}
