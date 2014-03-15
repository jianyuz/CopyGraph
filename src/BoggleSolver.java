import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * The game of Boggle is played on a N x N board (usually made of cubes that has letters engraved on it). Given a dictionary, you will have to construct words on the board following these rules:

i) The letters in the word must be "adjacent" to each other
ii) Two letters on the board are "adjacent" if they are located on the board next to each other horizontally, vertically, or diagonally.
iii) A word must be 3 or more letters long to be valid 

binary search in dictionary to find the word?
but search space is huge.

trie construction complexity O(W * L) number of words times the length of word.
explore the board like a graphc.
look for nodes correpspond to accepting strings

 * @author zhouzhou
 *
 */
public class BoggleSolver {

	public static void main(String[] args){
		char[][] grid = new char[][]{
			{'F', 'X', 'I', 'E'},
			{'A', 'M', 'L', 'O'},
			{'E', 'W', 'B', 'X'},
			{'A', 'S', 'T', 'U'}
		};
		
		String[] words = {"fa", "tux", "miles", "wast", "limbo", "bob", "oil", "boil"};
		//remove words with leng < 3. minimum word length is 3.
		List<String> res = solve(grid, words);
		System.out.println("boggle result");
		System.out.println(res);
		
	}
	/**
	 * solve Boggle with char grid and list of dictionary words.
	 * timcomplexity
	 * n2 * 8 to power of n number of edges.
	 * dfs uses O(n) space.
	 * 
	 * dictionary setup.
	 * W * L length of words.
	 * 
	 * @param grid
	 * @param words
	 */
	public static ArrayList<String> solve(char[][] grid, String[] words){
		Trie trie = new Trie();
		for(String word : words){
			trie.insert(word);
		}
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		ArrayList<String> res = new ArrayList<String>();
		String prefix = "";
		LinkedList<Position> visited = new LinkedList<Position>();//visited indices.
		
		for(int i=0; i< rows; i++){
			for(int j=0; j< cols; j++){
				Position pos = new Position(i,j);
				find(trie.root, grid,  pos, prefix, visited, res);
			}
		}
		
		return res;
	}
	
	public static void find(Node parent, char[][] grid, Position  pos, String prefix, LinkedList<Position> visited, List<String> res){
		if(visited.contains(pos)){
			return;
		}
		
		//depth first search.
		//http://aima.cs.berkeley.edu/python/search.html
		int rows = grid.length;
		int cols = grid[0].length;
		
		prefix += grid[pos.i][pos.j];
		Node node = parent.match(grid[pos.i][pos.j]);
		if(node != null){
			if(node.isWord){
				res.add(prefix);
			}
			visited.offer(pos);
			//visit neighbors.
			for(int i= -1; i<=1; i++){
				for( int j = -1; j<=1; j++){
					if(!(i==0 && j==0)){
						Position newPos = new Position(pos.i + i, pos.j + j);
						if(newPos.i >=0 && newPos.i <rows && newPos.j >=0 && newPos.j <cols){
							find(node, grid, newPos, prefix, visited, res);
						}
					}
				}
			}
			visited.poll();
			
		}
		
	}
	
	static class Position{
		int i;
		int j;
		public Position(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}
		
		public boolean insert(String word){
			return root.insert(word.toUpperCase());
		}
		
		public static Trie makeTrie(String words[]){
			Trie trie = new Trie();
			for(String word : words){
				trie.insert(word);
			}
			return trie;
		}
	}

	static class Node {
		public static final int NUM_OF_CHARS = 26;
		public static final int START_CHAR = 'A';//start char is always the same here
		Node[] children;
		boolean isWord;

		public Node() {
			isWord = false;
			children = new Node[NUM_OF_CHARS];
		}
		
		public boolean insert(String word){
			if(word == null) return false;
			if(word.length() == 0){
				if(isWord) return false; //already a word.
				else{
					isWord = true;
					return true;
				}
			}else{
				int pos = word.charAt(0) - START_CHAR;
				if(children[pos] == null){
					children[pos] = new Node();
					return children[pos].insert(word.substring(1));//recursive call for next char.
				}else{
					return children[pos].insert(word.substring(1));
				}
			}
		}
		
		/**
		 * check if trie contains prefix word.
		 * @param prefix
		 * @return
		 */
		public Node match(char c){
			
			int pos = Character.toUpperCase(c) -START_CHAR;
			return children[pos];
		}
		
		/**
		 * current node match the word.
		 * @return
		 */
		public boolean isWord(){
			return isWord;
		}
		
		
	}
	
	

}