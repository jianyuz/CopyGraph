package com.z2.determinePermutation;

/**
 * determine if two arrays are permutation of each other.
 * using constant space.
 * O(n) time complexity.
 * using min hash apporach.
 * select a set of hash function
 * compute hash value of the set.
 * select min to represent the array.
 * get the signature of the array, size of number of hash functions.
 * see of the hash values are the same.
 * 
 * this approach use constant space and O(n) time complexity.
 * 
 * map reduce count the words in document
 * map function
 * key word, value doc id
 * generate list of word key value counts in doc.
 * reduce function group the output with the same key.
 * intput, map, partition and comparision function
 * reduce and write output.
 * 
 * @author zhouzhou
 *
 */
public class DeterminePermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] A = {1, 7, 3, 4, 5};
		int[] B = {5, 4, 3, 7, 1};
		
		System.out.println(isPermutation(A, B));
	}

	public static boolean isPermutation(int[] A, int[] B){
		if(A == null || B == null) throw new IllegalArgumentException();
		if(A.length != B.length)throw new IllegalArgumentException(); 
		int [] hashValuesA = new int[A.length];
		int [] hashValuesB = new int[B.length];
		
		for(int i=0; i< 4; i++){
			minHashCalculation(A, B, i, hashValuesA, hashValuesB);
		}
		
		int sameCount= 0;
		for(int i=0; i<4; i++){
			System.out.println(hashValuesA[i] + " " + hashValuesB[i]);
			if(hashValuesA[i] == hashValuesB[i]){
				sameCount ++;
			}
		}
		
	    float simIndex= ((float)sameCount/4F);
	    System.out.println("sim index: " + simIndex);
		return (simIndex == 1)? true: false;
	
	}
	
	public static void minHashCalculation(int [] A, int[] B, int index, 
			int[] hashValuesA, int[] hashValuesB){
		
		int minHashA = Integer.MAX_VALUE;
		int minHashB = Integer.MAX_VALUE;
		int value;
		
		for(int i=0; i< A.length; i++){
			switch(index){
				case 0:	value = hashFunc1(A[i]);break;
				case 1: value = hashFunc2(A[i]); break;
				case 2: value = hashFunc3(A[i]); break;
				case 3: value = hashFunc4(A[i]); break;
				default: value = A[i]; break;
			}
			if(value < minHashA){
				minHashA = value;
			}
			
			switch(index){
				case 0:	value = hashFunc1(B[i]);break;
				case 1: value = hashFunc2(B[i]); break;
				case 2: value = hashFunc3(B[i]); break;
				case 3: value = hashFunc4(B[i]); break;
				default: value = A[i]; break;
			}
			if(value < minHashB){
				minHashB = value;
			}
		}
		hashValuesA[index] = minHashA;
		hashValuesB[index] = minHashB;
	}
	
	public static int hashFunc1(int a){
		return a % 10;
	}
	
	public static  int hashFunc2(int a){
		return (2 * a + 1) % 10;
	}
	
	public static int hashFunc3(int a){
		return (3 * a + 2 )%10;
	}
	
	public static int hashFunc4(int a){
		return (4 * a + 3)%10;
	}
	
}
