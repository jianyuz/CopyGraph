package com.z2.utilClass;

public class UtilClass {

	/**
	 * enforce the utilclass is not instantiable.
	 * assertion error prevent from internal call.
	 * no subclassing either as the parent's constructor is private.
	 */
	private UtilClass(){
		throw new AssertionError();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
