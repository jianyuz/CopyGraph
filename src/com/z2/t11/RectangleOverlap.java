package com.z2.t11;

public class RectangleOverlap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point p1 = new Point("p1", 1,1);
		Point p2 = new Point("p2", 4,3);
		Point p3 = new Point("p3", 2,0);
		Point p4 = new Point("p4", 5,5);
		Rectangle r1 = new Rectangle("r1", p1, p2);
		Rectangle r2 = new Rectangle("r2", p3, p4);
		System.out.println(overlap(r1, r2));
		
		p1 = new Point("P5", 1,1);
		p2 = new Point("p6", 4,4);
		p3 = new Point("p7", 2,2);
		p4 = new Point("p8", 3,3);
		r1 = new Rectangle("r1", p1, p2);
		r2 = new Rectangle("r2", p3, p4);
		System.out.println(overlap(r1, r2));
		
		p1 = new Point("p9", 1,1);
		p2 = new Point("p10",4,4);
		p3 = new Point("p11", 2,2);
		p4 = new Point("P12", 5,5);
		r1 = new Rectangle("r1", p1, p2);
		r2 = new Rectangle("r2", p3, p4);
		System.out.println(overlap(r1, r2));
	}
	/** think in opposite way*/
	public static boolean overlap(Rectangle r1, Rectangle r2){
		
		//!(r1.p2.x < r2.p1.x | r1.p2.y < r2.p1.y | r1.p1.x > r2.p2.x | r1.p1.y >r2.p2.y)
		if (r1.p2.x >= r2.p1.x && r1.p2.y >= r2.p1.y && r1.p1.x <= r2.p2.x && r1.p1.y <= r2.p2.y)
		return true;
		
		return false;
	}

}
