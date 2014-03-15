package com.z2.sorting;

public class PointsOnLines {

	private double slope;
	private double yIntercept;
	private float test;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(slope);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(test);
		temp = Double.doubleToLongBits(yIntercept);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PointsOnLines other = (PointsOnLines) obj;
		if (Double.doubleToLongBits(slope) != Double
				.doubleToLongBits(other.slope))
			return false;
		if (Float.floatToIntBits(test) != Float.floatToIntBits(other.test))
			return false;
		if (Double.doubleToLongBits(yIntercept) != Double
				.doubleToLongBits(other.yIntercept))
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
