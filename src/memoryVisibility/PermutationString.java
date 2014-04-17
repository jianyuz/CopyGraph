package memoryVisibility;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PermutationString {

	
	public static void main(String[] args){
		String input = "abc";
		List<String>res = new ArrayList<String>();
		permutate(res, input, new StringBuilder(), 0, input.length(), new BitSet(input.length()));
		
		System.out.println("total size " + res.size());
		for(String s:res ){
			System.out.println(s);
		}
		
		res = permutate("abc");
		
		System.out.println("total size " + res.size());
		for(String s:res ){
			System.out.println(s);
		}
		
		res = permutate1("abc");
		
		System.out.println("total size " + res.size());
		for(String s:res ){
			System.out.println(s);
		}
	}
	
	/** bitset iteration is costly **/
	/** cost? **/
	public static void permutate(List<String> res, String input, StringBuilder current, int count, int n, BitSet positions){
		if(count == n){
			res.add(current.toString());
			return;
		}
		
		for(int i= 0; i< n; i++){ //first error bitset count.
			if(!positions.get(i)){
				current.append(input.charAt(i));
				positions.set(i);
			
				permutate(res, input, current, count+1, n, positions );
				//System.out.println("count " + count + " n " + n + "current  " + current.toString());
				current.setLength(current.length() -1); //second remove last added. not -2
				positions.clear(i);//include the segment in the postions.get black
			}
		}
		
	}
	
	/**
	 * add the char before the next permutation results.
	 * @param input
	 * @return
	 */
	public static List<String> permutate(String input){
		//each time remove one char, get permulation of the left.
		List<String> res = new ArrayList<String>();
		if("".equals(input)){
			
			res.add("");
			return res;
		}
		
		for(int i = 0; i< input.length(); i++){
			String c = input.substring(i, i+1);
			String tmp = input.substring(0, i) + input.substring(i+1);
			List<String> res1 = permutate(tmp);
			
			for(String s: res1){
				res.add(c + s);
			}
		}
		return res;
	}
	
	/**
	 * take one char
	 * get permu of the rest.
	 * then insert into position 0 to n
	 * @param input
	 * @return
	 */
	public static List<String> permutate1(String input){
		List<String> res =new ArrayList<String>();
		if(input == null) return res;
		if("".equals(input)){	
			res.add(input);
			return res;
		}
		
		char c = input.charAt(0);
		res = permutate1(input.substring(1));
		List<String> fRes = new ArrayList<String>();
		for(String s: res){
			int n = s.length();
			for(int i=0; i<=n; i++){
				fRes.add(s.substring(0, i) + c + s.substring(i));
			}
		}
		return fRes;
	}
}
