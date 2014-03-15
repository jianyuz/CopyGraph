package com.z2.minDist;

public class Point {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	double x;
	double y;
	
	public Point (double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double dist(Point a){
		return Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
	}

}
