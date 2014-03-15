import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class WordLadder {

	public static void main(String[] args){
		
		String start = "a";
		String end = "c";
		HashSet<String> dict = new HashSet<String>();
		dict.addAll(Arrays.asList("a", "b", "c"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hit";
		end = "cog";
		dict = new HashSet<String>();
		dict.addAll(Arrays.asList("hot","dot","dog","lot","log"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hit";
		end = "cog";
		dict = new HashSet<String>();
		dict.addAll(Arrays.asList("hot"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hit";
		end = "cog";
		dict = new HashSet<String>();
		System.out.println(ladderLength(start, end, dict));
		
		start = "hot";
		end = "dog";
		dict = new HashSet<String>(Arrays.asList("hot", "dog"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hot";
		end = "dog";
		dict = new HashSet<String>(Arrays.asList("hot", "dog", "dot"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hot";
		end = "dot";
		dict = new HashSet<String>(Arrays.asList("hot", "dot", "dog"));
		System.out.println(ladderLength(start, end, dict));
		
		start = "hot";
		end = "hot";
		dict = new HashSet<String>(Arrays.asList("hot"));
		System.out.print(ladderLength1(start, end, dict) + " ");
		System.out.println(ladderLength2(start, end, dict));
		
		start = "hot";
		end = "hot";
		dict = new HashSet<String>(Arrays.asList("hot", "dot"));
		System.out.print(ladderLength1(start, end, dict) + " " );
		System.out.println(ladderLength2(start, end, dict));

		start = "hot";
		end = "dog";
		dict = new HashSet<String>(Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
		System.out.print(ladderLength1(start, end, dict) + " ");
		System.out.println(ladderLength2(start, end, dict));
		
	}
	public static int ladderLength(String start, String end, HashSet<String> dict) {
		
		if( start == null || end == null || dict == null) return 0;
        // Start typing your Java solution below
        // DO NOT write main() function
        HashMap<String, List<String>> adjMatrix = new HashMap<String, List<String>>();
        
        Iterator<String> iter = dict.iterator();
        List<String> adjList = new ArrayList<String>();
        
        while(iter.hasNext()){
            String item = iter.next();
            if(oneOfDist(start, item)){
                adjList.add(item);
            }
        }
        boolean endable = false;
        if(oneOfDist(start, end)){
            adjList.add(end);
            endable = true;
        }
        if(adjList.size() == 0) return 0;
        adjMatrix.put(start, adjList);
        
        String[] dictItems = dict.toArray(new String[0]);
        for(int i = 0; i< dictItems.length; i++){
            List<String> adj = new ArrayList<String>();
            for(int j=0; j< dictItems.length; j++){
                if(i != j && oneOfDist(dictItems[i], dictItems[j])){
                    adj.add(dictItems[j]);
                }
            }
            adjMatrix.put(dictItems[i], adj);
            if(oneOfDist(dictItems[i], end)){
               adj.add(end); 
               endable = true;
            }
        }
        if(!endable) { return 0;}
        
        HashMap<String, Integer> distMatrix = new HashMap<String, Integer>();
        distMatrix.put(start, 1);
        HashSet<String> visited = new HashSet<String>();
        //shortest path algorithm.
        while(distMatrix.size() > 0){
            String sKey = minDistItem(distMatrix);
            if(sKey == end)
            	break;
            int sDist = distMatrix.get(sKey);
            visited.add(sKey);
            distMatrix.remove(sKey);
            adjList = adjMatrix.get(sKey);
            Iterator<String> it = adjList.iterator();
            while(it.hasNext()){
                String adj = it.next();
                int newDist = sDist + 1;
                if(distMatrix.get(adj) == null || distMatrix.get(adj) > newDist){
                   if(!visited.contains(adj))
                	distMatrix.put(adj, newDist);                    
                }
            }
        }
        
        return distMatrix.get(end);
    }
    
    public static String minDistItem(HashMap<String, Integer> map){
        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();
        int sInt = Integer.MAX_VALUE;
        String sKey = null;
        while(it.hasNext()){
            String key = it.next();
            if(map.get(key) < sInt){
                sInt = map.get(key);
                sKey = key;
            }
        }
        return sKey;
    }
    
    public static boolean oneOfDist(String a, String b){
        if(a == null || b == null) return false;
        if(a.length() != b.length()) return false;
        
        int len = a.length();
        int diffCount = 0;
        int i, j;
        i= j= 0;
        while(i< len && j < len){
            if(a.charAt(i) != b.charAt(j)){
                diffCount ++;
            }
            i++;
            j++;
        }
        return diffCount == 1;
    }
    
    /**
     * complexity:
     * 
     * n number of nodes in dict.
     * 
     * k is the length of word in dict.
     * 
     * visit node once 
     * 
     * for each node to find neighbor
     * 
     * k * 26
     * 
     * so time complexity is n * k
     * 
     * space.
     * 
     * O(n)
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public static int ladderLength2(String start, String end, HashSet<String> dict) {
    
    	HashSet<String> visited = new HashSet<String>();
    	Queue<String> queue = new LinkedList<String>(); //search front.
    	queue.add(start);
    	
    	String curr = null;
    	char[] charArray;
    	char currChar;
    	String mutStr;
    	int distance = 1;
    	
    	int count = 1; 
		while (count > 0) {
			while (count > 0) {
				curr = queue.poll();
				charArray = curr.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					currChar = charArray[i];
					for (char c = 'a'; c <= 'z'; c++) {// mutate char
						if (currChar == c)
							continue;
						charArray[i] = c;
						mutStr = new String(charArray);

						if (mutStr.equals(end))
							return distance +1;// found it without going through
											// dict.

						if (dict.contains(mutStr) && !visited.contains(mutStr)) {
							queue.offer(mutStr);
							visited.add(mutStr);
						}
					}
					charArray[i] = currChar; //revert the char back;
				}
				count--;
			}
			// breath first search
			distance++;
			count = queue.size();
		}
		//didn't find the match.
		return 0;
    }
    
    
    
    /** why
     * my implementation got run time error in leetcode?
     * http://blog.sina.com.cn/s/blog_b9285de20101j1xl.html
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public static int ladderLength1(String start, String end, HashSet<String> dict) {
        Set<String> set=new HashSet<String>();
        Queue<String> queue=new LinkedList<String>();
        queue.offer(start);
        int distance=1;
        int count=1;
        set.add(start);
        
        while(count>0){
            while(count>0){
                char[] curr=queue.poll().toCharArray();
                
                for(int i=0; i<curr.length; i++){
                    char tmp=curr[i];
                    for(char c='a';c<='z';c++){
                        if(c==tmp) continue;
                        curr[i]=c;
                        String str=new String(curr);
                        if(str.equals(end)) return distance+1;
                        if(dict.contains(str) && !set.contains(str)){
                            queue.offer(str);
                            set.add(str);
                        }
                    }
                    curr[i]=tmp;
                }
                count--;
            }
            distance++;
            count=queue.size();
        }
        return 0;
    }
}

