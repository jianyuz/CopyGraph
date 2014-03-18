package memoryVisibility;

import java.util.Stack;

public class StackQueue<T> {

	private Stack<T> s1 = new Stack<T>();
	private Stack<T> s2 = new Stack<T>();
	
	public StackQueue(){
		
	}
	
	public void queue(T t){
		s1.push(t);
	}
	
	public T dequeue() throws Exception{
		if(!s2.isEmpty()){
			return s2.pop();
		}else{
			while(!s1.isEmpty()){
				s2.push(s1.pop());
			}
			
			if(s2.isEmpty()){
				throw new Exception("queue is empty");
			}else{
				return s2.pop();
			}
		}
	}
	
	public boolean isEmpty(){
		return s1.isEmpty()&&s2.isEmpty();
	}
	
	public static void main(String[] args) throws Exception{
		StackQueue<Integer> sq = new StackQueue<Integer>();
		sq.queue(1);
		sq.queue(2);
		sq.queue(3);
		sq.dequeue();
		sq.queue(4);
		System.out.println(sq.dequeue());
		
	}
}
