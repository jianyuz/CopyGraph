package memoryVisibility;

import java.util.Stack;

public class SortStack<T extends Comparable<T>> {

	private Stack<T> main;
	private Stack<T> helper;
	
	public SortStack(Stack<T> main){
		this.main = main;
		helper = new Stack<T>();
		
	}
	
	public Stack<T> sort(){
		
		while(!main.isEmpty()){
			T e = main.pop();
			if(helper.isEmpty()){
				helper.push(e);
			}else{
				while(!helper.isEmpty() && helper.peek().compareTo(e)> 0){
					main.push(helper.pop());
				}
				helper.push(e);
			}
		}
		
		while(!helper.isEmpty()){
			main.push(helper.pop());
		}
		
		return main;
	}
	
	public static void main(String[] args){
		Stack<Integer> s = new Stack<Integer>();
		s.push(7);
		s.push(5);
		s.push(6);
		s.push(1);
		s.push(2);
		
		SortStack<Integer> ss = new SortStack<Integer>(s);
		Stack<Integer> res = ss.sort();
		while(!res.isEmpty()){
			System.out.println(res.pop());
		}
		
	}
	
}
