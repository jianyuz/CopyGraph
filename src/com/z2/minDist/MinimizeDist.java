package com.z2.minDist;

/**
 * Goemetirc Mean issue
 * http://en.wikipedia.org/wiki/Geometric_median
 * @author zhouzhou
 *
 */
public class MinimizeDist {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Point a = new Point(0, 0);
		Point b = new Point(0, 0);
		Point c = new Point(0, 12);
		
		Point a1 = new Point(0, 0);
		Point b1 = new Point(-1, -1);
		Point c1 = new Point(0, 1);
		
		Point a2 = new Point(0, 0);
		Point b2 = new Point(0, 1);
		Point c2 = new Point(1, 0);
		Point d2 = new Point(1, 1);
		
		Point[] points = new Point[] { a, b, c};
		Point[] points1 = new Point[] { a1, b1, c1};
		Point[] points2 = new Point[] { a2, b2, c2, d2};
		Point res = pointMinDist(points);
		System.out.println("min point is " + res.x + " " + res.y);
		res = pointMinDist(points1);
		System.out.println("min point is " + res.x + " " + res.y);
		res = pointMinDist(points2);
		System.out.println("min point is " + res.x + " " + res.y);
	}
	
	/**
	 * find the point that min the sum of distance from the point to each point in the set.
	 * find the mean postion point first.
	 * @param points
	 * @return
	 */
	public static Point pointMinDist(Point[] points){
		double sumX = 0;
		double meanX = 0;
		for(Point p : points){
			sumX = sumX + p.x;
		}
		meanX = sumX/points.length;
		
		double sumY = 0;
		double meanY = 0;
		for(Point p : points){
			sumY = sumY + p.x;
		}
		meanY = sumY/points.length;
		int counter = 0;
		double currSumDist = Double.MAX_VALUE;
		Point minPoint = null;
		Point meanPoint;
		while(counter < 20){
			meanPoint = new Point(meanX, meanY);
			double[] dists = new double[points.length];
			int count = 0;
			for(Point p: points){
				dists[count++] =  p.dist(meanPoint);
			}
			
			float sumDist =0;
			for(int i=0; i< dists.length; i++){
				sumDist += dists[i];
			}
			if(sumDist < currSumDist){
				currSumDist = sumDist;
				minPoint = meanPoint;
			}
			//get new weighted point 
			//use 1/dist as the weight
		    //iterate through until there is no further improvement.
			double[] weights = new double[points.length];
			double totalWeight = 0;
			for(int i = 0; i< dists.length; i++){
				weights[i] = 1/ dists[i];
				totalWeight += weights[i];
			}
			
			double weightSum = 0;
			for(int i=0; i< points.length; i++){
				weightSum += weights[i] * points[i].x;
			}
			meanX = weightSum/totalWeight;
			weightSum = 0;
			for(int i=0; i< points.length; i++){
				weightSum += weights[i] * points[i].y;
			}
			meanY = weightSum/totalWeight;
			counter++;
		}
		
		System.out.println("minDis is " + currSumDist);
		return minPoint;
	}

}
