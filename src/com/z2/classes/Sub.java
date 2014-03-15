package com.z2.classes;

import java.util.Date;

public final class Sub extends Super {
	private final Date date;
	
	Sub(){
		date = new Date();
	}
	
	@Override public void overrideMe(){
		System.out.println(date);
	}
	
	public static void main(String[] args){
		Sub sub = new Sub();
		sub.overrideMe();
	}

}
