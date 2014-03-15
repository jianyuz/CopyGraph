package com.z2.expandList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListOfList implements Iterable<String>{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListOfList lists = new ListOfList();
		
		Iterator<String> it = lists.iterator();
		while(it.hasNext()){
			System.out.print("["+it.next()+"] ");
		}
		System.out.println("done");
		
		List<String> aList = new ArrayList<String>(Arrays.asList("a", "b", "c"));
		List<String> bList = new ArrayList<String>(Arrays.asList("A", "B", "C"));
		List<String> cList = new ArrayList<String>();
		List<String> dList = new ArrayList<String>(Arrays.asList("D"));
		lists.addList(aList);
		lists.addList(bList);
		lists.addList(null);
		lists.addList(cList);
		lists.addList(dList);
		
		it = lists.iterator();
		it.remove();
		while(it.hasNext()){
			System.out.print("["+it.next()+"] ");
			it.remove();
		}
		System.out.println("done");
		
		it = lists.iterator();
		while(it.hasNext()){
			System.out.print("["+it.next()+"] ");
			it.remove();
		}
		System.out.println("done");
		
	}
	
	private List<List<String> > lists;
	
	public ListOfList(){
		lists = new ArrayList<List<String>>();
		
	}
	
	public void addList(List<String> item){
		lists.add(item);
	}
	
	@Override
	public Iterator<String> iterator() {
		return new ListsIterator<String>(lists);
	}

	
}
