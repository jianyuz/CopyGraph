package com.z2.t25;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ClosestPairPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point[] testSet = new Point[100];
		Random rand = new Random(System.currentTimeMillis());
		for(int i=0; i< 100 ; i++){
			testSet[i] = new Point(rand.nextFloat() *1000, rand.nextFloat()*1000);
		}
		
		Point[] res = bruteForce(testSet);
		System.out.println("closes pair: " + res[0].toString() + " --- " +res[1].toString() );
		
		
		res = divideConquer(testSet);
		System.out.println("closes pair: " + res[0].toString() + " --- " +res[1].toString() );
		res = sweep(testSet);
		System.out.println("closes pair: " + res[0].toString() + " --- " +res[1].toString() );
	}
	
	public static Point[] bruteForce(Point[] points){
		
		float minDist = Float.MAX_VALUE;
		float tempDist;
		Point[] minDistPoints = new Point[2];
		for(int i = 0; i< points.length; i++){
			for(int j=i+1; j< points.length; j++){
				tempDist = (float)distance(points[i], points[j]);
				if(tempDist < minDist){
					minDistPoints[0] = points[i];
					minDistPoints[1] = points[j];
					minDist = tempDist;
				}
			}
		}
		System.out.println("minDist:" + distance(minDistPoints[0], minDistPoints[1]));
		return minDistPoints;
	}
	
	/**
	 * divide and conquer
	 * minLef, minRight
	 * and compare with minBetween.
	 * maximum 6 point in the comparsion zone.
	 * cause they must keep min(minLeft, minRight) distance.
	 * @param points
	 * @return
	 */
	public static Point[] divideConquer(Point[] points){
		Point[] sortedPts = Arrays.copyOf(points, points.length);
		Arrays.sort(sortedPts, new XAxisComparator() );
		
		TreeSet<Point> yProjection = new TreeSet<Point>(new YAxisComparator());
		yProjection.addAll(Arrays.asList(sortedPts));		
		
		int start = 0;
		int end = sortedPts.length-1;
		Point[] res = new Point[2];
		CPointPair pair= doDivideConquer(points, yProjection, start, end);
		res[0] = pair.p1;
		res[1] = pair.p2;
		System.out.println("minDist:" + pair.minDist);
		return res;
		
	}
	
	public static CPointPair doDivideConquer(Point[] points, TreeSet<Point> yProjection, int start, int end){
		
		if(start == end){
			return new CPointPair(null, null, Float.MAX_VALUE);
		}
		if(Math.abs(start - end) == 1){
			return new CPointPair(points[start], points[end], (float) distance(points[start], points[end]));
		}
		
		int mid = start + (end-start)/2;
		CPointPair c1 = doDivideConquer(points, yProjection, start, mid);
		CPointPair c2 = doDivideConquer(points, yProjection,  mid+1, end);
		
		float minLRDist = Math.min(c1.minDist, c2.minDist);
		CPointPair res = c1.minDist < c2.minDist? c1: c2;
		
		CPointPair c3 = searchBufferRegion(points, yProjection, start, mid, mid+1, end, minLRDist);
		minLRDist = Math.min(res.minDist, c3.minDist);
		res = c3.minDist < res.minDist? c3: res;
		
		return res;
	}
	
	public static CPointPair searchBufferRegion(Point[] points, TreeSet<Point> yProjection, int start, int end, int start1, int end1, float minLRDist){
		int j;
		CPointPair minDist = new CPointPair(null, null, minLRDist);
		for(int i = start; i<=end; i++){
			j = start1;
			while(j <= end1){
				if( distance(points[i], points[j]) < minDist.minDist){
					minDist.p1 = points[i];
					minDist.p2 = points[j];
					minDist.minDist = distance(points[i], points[j]) ;
				}else{
					break;
				}
				j++;
			}
			
			checkVerticalBuffer(points[i], yProjection, minDist);
			
		}
		
		for (int i = end1; i>=start1; i--){
			j = end;
			while(j >=start){
				if( distance(points[i], points[j]) < minDist.minDist){
					minDist.p1 = points[i];
					minDist.p2 = points[j];
					minDist.minDist = distance(points[i], points[j]) ;
				}else{
					break;
				}
				j--;
			}
			checkVerticalBuffer(points[i], yProjection,  minDist);
		}
		
		return minDist;
	}
	
	public static void checkVerticalBuffer(Point curPoint, TreeSet<Point> yProjection,  CPointPair minDist){
		Point k1 = new Point(curPoint.x, curPoint.y - minDist.minDist);
		Point k2 = new Point(curPoint.x, curPoint.y + minDist.minDist);
		SortedSet<Point> subSet= yProjection.subSet(k1, k2);
		
		for(Point entry: subSet){
			if(!curPoint.equals(entry)){
				float dist = distance(curPoint, entry);
				if(dist < minDist.minDist){
					minDist.p1 = curPoint;
					minDist.p2 = entry;
					minDist.minDist = dist;
				}
			}
		}	
	}
	
	public static float distance(Point p1, Point p2){
		return (float)Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
	}
	
	private static final class CPointPair{
		public Point p1;
		public Point p2;
		public float minDist;
		
		public CPointPair(Point p1, Point p2, float minDist){
			this.p1 = p1;
			this.p2 = p2;
			this.minDist = minDist;
		}
	}
	
	private static final class XAxisComparator implements Comparator<Point>{

		@Override
		public int compare(Point p1, Point p2) {
			if(p1.x < p2.x){
				return -1;
			}
			if(p1.x > p2.x){
				return 1;
			}
			if(p1.y < p2.y){
				return -1;
			}
			if(p1.y > p2.y){
				return 1;
			}
			return 0;
		}
		
	}
	
	private static final class YAxisComparator implements Comparator<Point>{

		@Override
		public int compare(Point p1, Point p2) {
			if(p1.y < p2.y){
				return -1;
			}
			if(p1.y > p2.y){
				return 1;
			}
			if(p1.x < p2.x){
				return -1;
			}
			if(p1.x > p2.x){
				return 1;
			}
			
			return 0;
		}
		
	}
	
	//sweep from left to right.
	//sweep scope is x-minDist and y+- minDist.
	//sort point on x axis.
	//keep leftMostIndex of points in candidates set.
	//if the point goes beyond the scope of current.x -minDist.
	//remove from candidates set.
	//test the candidate set on y axis.
	//but we can't remove points.
	//add points to candidate one by one.
	public static Point[] sweep(Point[] points){
		Point[] res= new Point[2];
		CPointPair pair = doSweep(points);
		res[0] = pair.p1;
		res[1] = pair.p2;
		System.out.println("minDist:" + pair.minDist);
		return res;
	}
	
	private static CPointPair doSweep(Point[] points){
		CPointPair pointPair= new CPointPair(null, null, Float.MAX_VALUE);
		
		Point[] sorted = Arrays.copyOf(points, points.length);
		Arrays.sort(sorted, new XAxisComparator());
		
		int leftMost = 0;
		TreeSet<Point> candidates = new TreeSet<Point>(new YAxisComparator());
		
		for(Point p: sorted){
			while(p.x - sorted[leftMost].x > pointPair.minDist ){
				candidates.remove(p);
				leftMost ++;
			}
			
			Point head = new Point(p.x, p.y-pointPair.minDist);
			Point tail = new Point(p.x, p.y+pointPair.minDist);
			
			for(Point pt: candidates.subSet(head, tail)){
				float dist = distance(p, pt);
				if(dist < pointPair.minDist){
					pointPair.minDist  = dist;
					pointPair.p1 = p;
					pointPair.p2 = pt;
				}
			}
			candidates.add(p);
		}
		
		return pointPair;
	}

}
