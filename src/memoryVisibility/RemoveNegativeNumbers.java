package memoryVisibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class RemoveNegativeNumbers {
	
//	public static void main(String[] args){
//		List<Integer> intList = new ArrayList<Integer>();
//		
//		intList = new ArrayList<Integer>(Arrays.asList(2, 5, -1, -3, 5, -4));
//		Iterator<Integer> it = intList.iterator();
//		
//		while(it.hasNext()){
//			int elem = it.next();
//			if(elem < 0){
//				it.remove();
//			}
//		}
//		
//		System.out.println(intList.toString());
//	}
	
	public static void main(String[] args){//backward deletion of negative number
		List<Integer> intList = new ArrayList<Integer>();
		
		intList = new ArrayList<Integer>(Arrays.asList(2, 5, -1, -3, 5, -4));
		
		ListIterator<Integer> it = intList.listIterator(intList.size());
		
		while(it.hasPrevious()){
			int elem = it.previous();
			if(elem < 0){
				it.remove();
			}
		}
		
		System.out.println(intList.toString());
	}


}
