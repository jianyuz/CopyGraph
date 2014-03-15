
public class RemoveElement {

	
	public int removeElement(int[] A, int elem) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        int len = 0;
        int last = A.length -1;
        
        for(int i=0; i< A.length; i++){
            if(A[i] != elem){
                len ++;
            }else{
                //find the target value, swap with the last element if it is not target.
                while(last > i && A[last] == elem) last --; //last must be greater than current i
                //there is no meaning to swap if it is smaller.
                if(last > i){
                    swap(A, i, last);
                    last--; //decrement last after sawp.
                    len++; //increse len after swap.
                }
            }
            
        }
        return len;
    }
    
    public void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    
    /*
     * Simpler solution.
     * 
     */
    public int removeElement1(int[] A, int elem) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(A == null || A.length == 0) return 0;
        
        int len = 0;
        
        for(int i=0; i< A.length; i++){
            if(A[i] != elem){
                A[len] = A[i];
                len++; //copy element to the biginning of the array.
                //others be overriden.
            }
            
        }
        return len;
    }
}
