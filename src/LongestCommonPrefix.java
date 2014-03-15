
public class LongestCommonPrefix {
	
	/**
	 * shorter version.
	 * use first string as base of comparison
	 * use stringbuilder to add char.
	 * if not match return sb toString.
	 * else until the end return sb.
	 * 
	 * timeComplexity.
	 * longest word length * n (length of array)
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix1(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(strs== null) return null;
        if(strs.length == 0) return "";
        
        char[] chars = strs[0].toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j< chars.length; j++){
            for(int i = 1; i< strs.length; i++){
                if(strs[i].length() -1 < j || strs[i].charAt(j) != chars[j]){
                    return sb.toString();
                }
            }
            sb.append(chars[j]);
                
        }
        return sb.toString();
    }
    
	/**
	 * space complexity
	 * 256 * longest word.
	 * time complexity
	 * when building the trie
	 * n * (average length of word)
	 * 
	 * when search for longest common prefix.
	 * 256 * longest word.
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //build a trie.
        //find the path in it without a branch.
        //if multiple root, then common prefix doesn't exist.
        
        if(strs== null) return null;
        if(strs.length == 0) return "";
        
        //or for smae position in string. comapre char by char. until we find we non match.
        
        TrieNode root = new TrieNode();
        
        for(String str: strs){
            root.addWord(str);
        }
        
        StringBuilder res = new StringBuilder();
        
        TrieNode cur = root;
        
        while(!cur.isWord){
            int count = 0;
            int candidatePos = 0;
            for(int i=0; i< cur.children.length; i++){
                if(cur.children[i] != null){
                    count ++;
                    candidatePos = i;
                }
                if(count > 1) return res.toString();
            }
            if(count == 1) res.append((char)candidatePos);
            cur = cur.children[candidatePos];
        }
        
        return res.toString();
    }
    
    static class TrieNode {
        public final static char start = (char)0 ;//null chars as start char of the char range.
        private final static int NUM_OF_CHAR = 256;
        TrieNode[] children; //number of trie children, don't predefine the range.always consume 256 positions.
        boolean isWord; // value at the end node of Trie.
        
        public TrieNode(){
            this.children = new TrieNode[NUM_OF_CHAR];
            this.isWord = false;
        }
        
        /**
         * add word return to indicate if the word is new in trie or
         * duplicate already exists.
         * @param word
         * @return
         */
        public boolean addWord(String word){
            if(word == null) return false;
            
            if(word.length() == 0){
                if(isWord) return false;//mark isWord at the end.
                //so consturctor doesn't need it.
                else {
                    isWord = true;
                    return true;
                }
            }else{
                int pos = word.charAt(0) - start;
                if(children[pos] == null){
                    children[pos] = new TrieNode();
                }
                return children[pos].addWord(word.substring(1));//consume char one by one.
            }
        }
    }
}
