package com.z2.t11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OverlapSet {
	
	private static OverlapSet ols;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ols = new OverlapSet();
		
		Point p1 = new Point("p1", 1,1);
		Point p2 = new Point("p2", 4,3);
		Point p3 = new Point("p3", 2,0);
		Point p4 = new Point("p4", 5,5);
		Rectangle r1 = new Rectangle("r1", p1, p2);
		Rectangle r2 = new Rectangle("r2", p3, p4);
		
		p1 = new Point("p5", 1,1);
		p2 = new Point("p6", 4,4);
		p3 = new Point("p7",2,2);
		p4 = new Point("p8", 3,3);
		Rectangle r3 = new Rectangle("r3",p1, p2);
		Rectangle r4 = new Rectangle("r4", p3, p4);
		
		p1 = new Point("p9", 1,1);
		p2 = new Point("p10", 4,4);
		p3 = new Point("p11",2,2);
		p4 = new Point("p12", 5,5);
		Rectangle r5 = new Rectangle("r5", p1, p2);
		Rectangle r6 = new Rectangle("r6", p3, p4);

		p1 = new Point("p13", 9,9);
		p2 = new Point("p14", 10,10);
		p3 = new Point("p15",8,8);
		p4 = new Point("p16", 11,11);
		Rectangle r7 = new Rectangle("r7", p1, p2);
		Rectangle r8 = new Rectangle("r8", p3, p4);
		
		List<Rectangle> recs = new ArrayList<Rectangle>();
		recs.add(r1);
		recs.add(r2);
		recs.add(r3);
		recs.add(r4);
		recs.add(r5);
		recs.add(r6);
		recs.add(r7);
		recs.add(r8);
		
		overlapSet(recs);
	} 
	
	public static void overlapSet(List<Rectangle> recs){
		List<IdentityEntry> pointList = new ArrayList<IdentityEntry>();
		
		for(Rectangle rec: recs){
			pointList.add(ols.new IdentityEntry(rec.p1.x, PointType.START, rec));
			pointList.add(ols.new IdentityEntry(rec.p2.x, PointType.END, rec));
		}
		
		Collections.sort(pointList);
		
		Set<Rectangle> resSet = new HashSet<Rectangle>();
		for(IdentityEntry ie: pointList){
			if(ie.type.equals(PointType.START)){
				if(resSet.size() > 0){
					for(Rectangle rec: resSet){
						System.out.println("intersecting rectangles : ");
						System.out.println(rec + " and " + ie.rectangle );
					}
				}
				resSet.add(ie.rectangle);
			}else{
				resSet.remove(ie.rectangle);
			}
		}
	}

	enum PointType {
		START, END
	}
	
	class IdentityEntry implements Comparable<IdentityEntry>{
		int pos;
		PointType type;
		Rectangle rectangle;
		
		public IdentityEntry(int pos, PointType type, Rectangle rec){
			this.pos = pos;
			this.type = type;
			this.rectangle = rec;
		}

		@Override
		public int compareTo(IdentityEntry ie) {
			return pos - ie.pos;
		}
	}
}
