import java.util.ArrayList;


public class GetPermutation {
	
	/**
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(getPermutation1(3, 3));
	}
	public static String getPermutation(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(n <= 0) return null;
        
        int[] pCount = permCount(n);
        
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        for(int i=1; i<=n; i++){
            candidates.add(i);
        }
        
        StringBuilder res = new StringBuilder();
        
        getPerm(pCount, candidates, n, k, res);
        
        return res.toString();
        
    }
    
	/**
	 * pick one element, then remove it from candidates list.
	 * know the count of permutation at each level remove the number from there.
	 * use stringBuilder to keep track of result.
	 * 
	 * @param pCount
	 * @param candidates
	 * @param n
	 * @param k
	 * @param res
	 */
    public static void getPerm(int[] pCount, ArrayList<Integer> candidates, int n, int k,  StringBuilder res){
        if(n==1){//terminate condition. append the last one.
            res.append(candidates.get(0));
            return;
        }
        
        int count = pCount[n-1];
        
        int index = (k-1) /count; //shift by one here to fall into bucket.
        res.append(candidates.get(index));
        candidates.remove(index);
        getPerm(pCount, candidates, n-1, k- index * count, res); //get the number of left over count.
    }
    
    public static int[] permCount(int n){
        int[] res = new int[n];
        
        res[0] = 1;
        
        for(int i=1; i< n; i++){
            res[i] = res[i-1] * i;
        }//from 1 to n-1;
        
        return res;
    }
    
    public static String getPermutation1(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(n <= 0 || k < 0) return null;
        
        //get permutation computation result for n-1 items.
        int[] perm = new int[n];
        perm[0] =1;
        for(int i = 1; i< perm.length; i++){
            perm[i] = perm[i-1] * i;
        }
        
        //use boolean array to remember which item has been used in perm sequence.
        boolean[] used = new boolean[n+1];//by default all false.
        
        //find digit one by one and count the position.
        StringBuilder res = new StringBuilder();
        
        for(int i=1; i<=n; i++){
            int olCount = perm[n-i];
            int index  = (k-1)/olCount; //which bin to fall in.
            k = k- index * olCount ; //update k. can't use mod.
            //or use (k-1)%olCount + 1 use mod to compensate by 1.
            
            int count = 0;
            for(int j=1; j<=n; j++){
                //find the element with index among unused digit.
                if(used[j]) continue;
                if(count < index){
                    count ++;
                }else if( count == index){
                    used[j] = true;
                    res.append(j);
                    break;//found must break out. otherwise used[j+1} is found too.
                } 
            }
        }
        
        return res.toString();
    }
}
