package memoryVisibility;

import java.util.ArrayList;
import java.util.List;

public class Parenthese {
	
	public static void main(String[] args){
		List<String> res = new ArrayList<String>();
		parens(res, new StringBuilder(), 0, 0, 3);
		System.out.println(res.toString());
	}
	
	public static void parens(List<String> res, StringBuilder current, int left, int right, int n){
		if(left == right && right == n){
			res.add(current.toString());
			return;
		}
		
		if(right > left || left > n){
			return;
		}
		
		current.append("(");
		parens(res, current, left+1, right, n);
		current.setLength(current.length() -1);
		
		current.append(")");
		parens(res, current, left, right+1, n);
		current.setLength(current.length() -1);
	}

}
