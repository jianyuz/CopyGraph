package com.z2.utilClass;

import java.util.Arrays;

public class HashTest {

	private boolean state;
	private char mychar;
	private String myName;
	private long secret;
	private int shortSecret;
	private double mydouble;
	private float myfloat;
	private int[] myArry;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1; //non empty integer constant
		result = prime * result + Arrays.hashCode(myArry);
		//use 0 if an object is 0.
		result = prime * result + ((myName == null) ? 0 : myName.hashCode());
		result = prime * result + mychar;
		long temp;
		temp = Double.doubleToLongBits(mydouble);
		result = prime * result + (int) (temp ^ (temp >>> 32));//need to cast into int.
		result = prime * result + Float.floatToIntBits(myfloat);
		result = prime * result + (int) (secret ^ (secret >>> 32));
		result = prime * result + shortSecret;
		//boolean handling is different.
		result = prime * result + (state ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashTest other = (HashTest) obj;
		if (!Arrays.equals(myArry, other.myArry))
			return false;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equals(other.myName))
			return false;
		if (mychar != other.mychar)
			return false;
		if (Double.doubleToLongBits(mydouble) != Double
				.doubleToLongBits(other.mydouble))
			return false;
		if (Float.floatToIntBits(myfloat) != Float
				.floatToIntBits(other.myfloat))
			return false;
		if (secret != other.secret)
			return false;
		if (shortSecret != other.shortSecret)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
