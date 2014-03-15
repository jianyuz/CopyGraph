package com.z2.tagReplace;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Host {

	private static class StrLenComparator implements Comparator<String>, Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public int compare(String s1, String s2){
			return s1.length() -s2.length();
		}
	}
	
	public static Comparator<String> STRING_LENGTH_COMP = new StrLenComparator();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tests = new String[]{};
		Arrays.sort(tests, new Comparator<String>(){
			public int compare(String s1, String s2){
				return s1.length() -s2.length();
			}
		});
	}

}
