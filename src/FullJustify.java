import java.util.ArrayList;
import java.util.Arrays;


public class FullJustify {

	/**
	 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.
	 * @param args
	 */
	public static void main(String[] args){
		String[] words = new String[] {"What","must","be","shall","be."};
		int L = 12;
		System.out.println(Arrays.toString(fullJustify(words, L).toArray(new String[1])));
	}
	
	//improved version
	
	public static ArrayList<String> fullJustify(String[] words, int L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(words == null || words.length == 0) return null;
        
        ArrayList<String> res = new ArrayList<String>();
        
        int i=0;
        int count = 0;
        
        int start = 0;
        int end = -1;
        StringBuilder sb;
        
        
        while(i < words.length){
            start = i;
            count = words[start].length(); //one word
            
            while(i < words.length){
                end = i;
                i++;
                if(i < words.length){
                    int nextLen = count + words[i].length();
                    if(nextLen + end + 1 -start > L){//don't add the nextLen until it is good.
                        break;
                    }else{
                        count = nextLen;
                    } 
                }
            }
            
            //create sb.
            sb = new StringBuilder();
            //special case last line.
            if(end == words.length -1 || end -start ==0){
                sb.append(words[start]);
                for(int j = start +1 ; j<= end; j++){
                    sb.append(" ");
                    count ++; //add space as count.
                    sb.append(words[j]);
                }
                for(int j = 0; j< L - count; j++){
                    sb.append(" ");
                }
            //special case only one word.
            }else{
            //normal case one line with multiple words.
                int ave = (L - count)/(end - start);
                int spill = (L - count)%(end - start);
                sb.append(words[start]);
                //add spill spaces.
                int n = 0;
                for(int j = start+1; j<=end; j++){
                    if( n < spill){
                        sb.append(" ");
                        n++;
                    }
                    for(int k=0; k<ave; k++){
                        sb.append(" ");
                    }
                    sb.append(words[j]);
                }
            
            }
                       
            res.add(sb.toString());
        }
        
        return res;
        
    }
	
	 public static ArrayList<String> fullJustify1(String[] words, int L) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(words == null || words.length == 0) return null;
	        
	        ArrayList<String> res = new ArrayList<String>();//output.
	        
	        int i=0;
	        int count = 0;
	        
	        int start = 0;
	        int end = -1;
	        StringBuilder sb;
	        int leftOver = -1;
	        while(i < words.length){
	            start = i;
	            count = words[start].length(); //one word
	            
	            while(i < words.length){
	                end = i++;
	                if(i < words.length){
	                    int nextLen = (1 + words[i].length());
	                    if(nextLen + count > L) break;
	                    else count += nextLen;
	                }
	            }
	    
	            leftOver = L - count; //space to fill in.
	            sb = new StringBuilder();
	            if(end - start == 0){//just one word.
	                sb.append(words[start]);
	                for(int k = 0; k< leftOver; k++){
	                    sb.append(" ");//append space at the end.
	                }
	                
	            }else if(end == words.length -1){//end line.
	                sb.append(words[start]);
	                for(int k=start+1; k<=end; k++){
	                    sb.append(" ");
	                    sb.append(words[k]);
	                }//separate by one space.
	                for(int m = 0; m< leftOver; m++){
	                        sb.append(" ");
	                }
	                
	            }else{
	                int ave = leftOver/(end -start);//calculate average space.
	                int over = leftOver%(end-start); //mod the average. over space.
	                //evenly distributed.
	                sb.append(words[start]);
	                int n = 0;
	                for(int k=start+1; k<=end; k++){
	                    sb.append(" ");//count already have it considered.
	                    if(n < over){
	                        sb.append(" ");
	                        n++;
	                    }//evenly distribute over spaces.
	                    for(int m = 0; m< ave; m++){
	                        sb.append(" ");
	                    }
	                    sb.append(words[k]);
	                }
	            }
	            res.add(sb.toString());
	        }
	        
	        return res;
	        
	    }
}
