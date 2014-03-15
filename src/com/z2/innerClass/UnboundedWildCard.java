package com.z2.innerClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 * don't know what the actual parameter is.
 * @author zhouzhou
 *
 */
public class UnboundedWildCard {

	private static UnaryFunction<Object> Iden_function=
			new UnaryFunction<Object>(){
				public Object apply(Object args){
					return args;
				}
			};
	
	@SuppressWarnings("unchecked")
	public static <T> UnaryFunction<T> identityFunction(){
		return (UnaryFunction<T>) Iden_function;
	}
	
	public static void main(String[] args){
		UnaryFunction<Integer> uInteger = identityFunction();
		
		Set<Integer> s1 = new HashSet<Integer>();
		s1.add(1);
		s1.add(2);
		Set<String> s2 = new HashSet<String>();
		s2.add("new");
		
		Set<Object> s3 = new HashSet<Object>();
		s3.add(1);
		s3.add("me");
		
		System.out.println(numElesInCommon(s1, s3));
		System.out.println(numElementsInCommon(s1, s2));
		
		List<String> testList = new ArrayList<String>();
		testList.add("me");
		testList.add("you");
		System.out.println(max(testList));
		
		
		Set<Integer> integers = new HashSet<Integer>();
		Set<Double> doubles = new HashSet<Double>();
		Set<Number> numbers = UnboundedWildCard.<Number>union(integers, doubles);
		//explicitly tell how the type is converted.
		
		Object[] objectArray = new Long[1];
		objectArray[0] = " wrng";
		/*
		List<String>[] stringLists = new List<String>[1]; // (1)
		List<Integer> intList = Arrays.asList(42); // (2)
		Object[] objects = stringLists; // (3)
		objects[0] = intList; // (4)
		String s = stringLists[0].get(0); //
		*/
		//generic array can't be allowed.
	}
	
	public static<E>  E reduce(List<? extends E> list, Function<E> f, E init){
		E[] snapshot = (E[])list.toArray();//take snapshot to avoid concurrency issue.
		E result = init;
		for(E e: snapshot){
			result = f.apply(result, e);
		}
		return result;
	}
	
	public static <T extends Comparable<? super T>> T max(List<? extends T> l){
		Iterator<? extends T> it = l.iterator();
		T res = it.next();
		while(it.hasNext()){
			T t = it.next();
			if(t.compareTo(res) > 0)
				res = t;
		}
		return res;
	}
	
	/**
	 * best implementation
	 * replace array with list.
	 * avoid unsafe cast.
	 * 
	 */
	public static <E> E reduce1(List<E> list, Function<E> f, E init){
		List<E> copyList;
		synchronized(list){
			copyList = new ArrayList<E>(list);
		}
		E result = init;
		for(E e: copyList){
			result = f.apply(result, e);
		}
		return result;
	}
	
	/**
	 * wild card 
	 * @param s1
	 * @param s2
	 * @return
	 */
	static int numElesInCommon(Set<?> s1, Set<?> s2){
		int res = 0;
		//s1.add("myname");
		//s1.add(null);can't put anythign in except null.
		if(s1 instanceof Set<?>){//generic inf is erased at runtime.
			Set<?> m = (Set<?>) s1;
			for(Object o1 : m){
				if(s2.contains(o1)){
					res ++;
				}
			}
		}
		
		return res;
	}
	
	public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2){
		Set<E> res = new HashSet<E>(s1);
		res.addAll(s2);
		return res;
	}
	static int numElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1)
		if (s2.contains(o1))
		result++;
		return result;
		}
	/**
	 * public exposed api prefer using wild card
	 * can't add anything to list<?> except null.
	 * @param list
	 * @param i
	 * @param j
	 */
	public static void swap(List<?> list, int i, int j){
		swapHelper(list, i, j);
	}
	
	/**
	 * internal helper method.
	 * @param list
	 * @param i
	 * @param j
	 */
	private static <E> void swapHelper(List<E> list, int i, int j){
		list.set(i, list.set(j, list.get(i)));
	}
}
