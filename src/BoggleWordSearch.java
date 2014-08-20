import java.util.ArrayList;
import java.util.List;

/**
 * search wrods in dictionary from documents.
 * 
 * W * L + L * n (chars in documents)
 * @author zhouzhou
 *
 */
public class BoggleWordSearch {

	public static void main(String[] args){
		
		String document = "I fa 5 miles with bob and waisted qoil";
		
		String[] words = {"fa", "tux", "miles", "wast", "limbo", "bob", "oil", "boil"};
		//remove words with leng < 3. minimum word length is 3.
		List<String> res = solve(document, words);
		System.out.println("sword search result");
		System.out.println(res);
		
	}
	
	public static List<String> solve(String document, String[] words){
		TrieNode root = new TrieNode();
		for(String word : words){
			root.insert(word);
		}
		
		List<String> res = new ArrayList<String>();
		String prefix = "";
		for(int i=0; i< document.length(); i++){//or add condition here. only search from word beginning.
			find(document, i, prefix, root, res);
		}
		return res;
	}
	
	public static void find(String document, int pos, String prefix,  TrieNode node, List<String> res){
		int i = pos;
		int len = document.length();
		while(i < len){
			char c = document.charAt(i);
			prefix += c;
			node = node.match(c);
			if(node != null && node.isWord()){
				res.add(prefix);
				break;
			}else if(node != null){
				i++;
			}else {
				break; //not found
			}
		}
	}
	
	static class TrieNode{
		TrieNode[] children;
		boolean isWord;
		
		TrieNode(){
			children = new TrieNode[26];
			isWord = false;
		}
		
		public boolean insert(String word){
			if(word == null) return false;
			if(word.length() == 0){
				if(isWord) return false;
				else {
					isWord = true;
					return true;
				}
			}
			
			char c = word.charAt(0);
			int cIndex = c - 'a';
			if(children[cIndex] == null){
				children[cIndex] = new TrieNode();
			}
			return children[cIndex].insert(word.substring(1));
		}
		
		public boolean isWord(){
			return isWord;
		}
		
		public TrieNode match(char c){
			int index = c-'a';
			if (index < 0 || index >=26) return null;//safety check, some char is not in the range.
			return children[index];
		}
	}
	
}
