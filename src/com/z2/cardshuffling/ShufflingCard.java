package com.z2.cardshuffling;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * fully random shuffling or a deck of cards
 * or int array.
 * 
 * random pick element and swap toward the end of array.
 * 
 * @author zhouzhou
 *
 */
public class ShufflingCard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		a = shuffle1(a);
		
	
		for(int i = 0; i< a.length; i++){
			System.out.print(a[i] + " " );
		}
		
		System.out.println();
		a = initAndShuffle(8);
		
		for(int i = 0; i< a.length; i++){
			System.out.print(a[i] + " " );
		}
		System.out.println();
		a = shuffle2(a);
		
		for(int i = 0; i< a.length; i++){
			System.out.print(a[i] + " " );
		}
	}
	
	public static int[] shuffle(int[] a){
		if(a == null) return null;
		int n = a.length;
		Random r = new Random();
		int resCount = 0;
		for(int i = 0; i < n -1 ; i ++){
			int pick =(int)(r.nextDouble() * (n-resCount-1));
			int tmp = a[n-resCount-1];
			a[n-resCount-1] = a[pick];
			a[pick] = tmp;
			resCount ++;
		}
		return a;
	}
	
	public static int[] shuffle1(int[] a){
		if(a == null) return null;
		int n = a.length;
		Random r = new Random();
		
		int [] b= Arrays.copyOf(a, a.length);
		for(int i = n-1; i >=1 ; i--){
			int pick = r.nextInt(i);
			int tmp = b[i];
			b[i] = b[pick];
			b[pick] = tmp;
		}
		return b;
	}
	
	/**
	 * inside out.
	 * successively place number i into reandom position of first i position.
	 * moving the elements prviously occupised to position i.
	 *
	 * @return
	 */
	public static int[] initAndShuffle(int n){
		int[] a = new int[n];
		a[0] = 0;
		Random r = new Random();
		for(int i = 1; i< n; i++){
			int pick = r.nextInt(i);
			a[i] = a[pick]; //a[i] gets the old value.
			a[pick] = i; //a pick
			
		}
		return a;
	}
	
	/**
	 * space O(n)
	 * complexity O(nlgn)
	 * @param a
	 * @return
	 */
	public static int[] shuffle2(int[] a){
		Map<Double, Integer> rMap = new TreeMap<Double, Integer>();
		Random r = new Random();
		for(int i =0; i< a.length; i++){
			rMap.put(r.nextDouble(), i);
		}
		
		int[] b = new int[a.length];
		Collection<Integer> values = rMap.values();
		
		int i=0;
		for(Iterator<Integer> it=values.iterator(); it.hasNext();){
			b[i++] = a[it.next()];
		}
		return b;
		
	}

}
