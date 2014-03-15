package com.z2.comparable;

public class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
	private String s;
	
	@Override
	public int compareTo(CaseInsensitiveString o) {
		return String.CASE_INSENSITIVE_ORDER.compare(s, o.s);
	}

}
