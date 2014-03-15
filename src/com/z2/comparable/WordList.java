package com.z2.comparable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WordList {
	
	public static void main(String[] args){
		Set<String> s = new TreeSet<String>();
		Collections.addAll(s, args);
		System.out.println(s);
		
		BigDecimal b = new BigDecimal("1.0");
		BigDecimal b1 = new BigDecimal("1.00");
		
		Set<BigDecimal> ts = new TreeSet<BigDecimal>();
		Set<BigDecimal> hs = new HashSet<BigDecimal>();
		
		Collections.addAll(ts, b, b1);
		Collections.addAll(hs, b, b1);
		System.out.println(ts.size());
		System.out.println(hs.size());
		
		System.out.println(b.scale());
		System.out.println(b1.scale());
		//they are only equal when are equal in value and scale.
		//scale number of digits in fraction minus the exponent.
		
		
		System.out.println(b.equals(b1));
		
		System.out.println(Double.compare(1.2, 1.21));
	}

}
