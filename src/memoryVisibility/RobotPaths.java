package memoryVisibility;

import java.util.ArrayList;
import java.util.List;

public class RobotPaths {

	/*
	 * step to right 3
	 * step to down 4.
	 * c74
	 * 7 * 6 * 5 * 4 /4 *3 * 2
	 */
	public static void main(String[] args){
		System.out.println(pathCount(5, 4));
		System.out.println(pathCount1(5, 4));
		System.out.println(pathCount2(5, 4));
		
		int[][] block = new int[][]{
				{0, 0, 0, 0, 0 },
				{0, 1, 1, 1, 0},
				{0, 0, 1, 1, 1},
				{0, 1, 1, 1, 1}
		};
		
		System.out.println(pathCount3(block, 3, 4));
		
		List<List<Point>> res = new ArrayList<List<Point>>();
		paths(res, new ArrayList<Point>(), block, 3, 4, 1, 1);
		
		int i = 0;
		for(List<Point> path : res){
			i++;
			System.out.println("path " + i);
			for(Point p: path){
				System.out.print(p + "->");
			}
			System.out.println();
		}
		
	}
	public static int pathCount(int m, int n){
		if(m == 1 || n == 1){
			return 1;
		}
		
		return pathCount(m, n-1) + pathCount(m -1, n);
		
	}
	
	/**
	 * combitorial calculation.
	 * @param m
	 * @param n
	 * @return
	 */
	public static int pathCount1(int m, int n){
		return factorial(m + n -1 -1)/(factorial(m-1) * factorial(n-1));
	}
	
	public static int factorial(int m){
		if (m == 1) return 1;
		return m * factorial(m-1);
	}
	
	public static int pathCount2(int m, int n){
		//dp solution
		int[][] res = new int[m+1][n+1];
		for(int i=1; i<= m; i++){
			res[i][1] = 1;
		}
		for(int j=1; j<=n; j++){
			res[1][j] = 1;
		}
		
		for(int i=2; i<=m; i++){
			for(int j=2; j<=n; j++){
				res[i][j] = res[i-1][j] + res[i][j-1];
			}
		}
		
		return res[m][n];
		
	}
	
	/**with some square marked as blocked.
	 * nope on edges if one is blocked , afterward squares are blocked too.
	 * @param res
	 * @param m
	 * @param n
	 * @return
	 */
	public static int pathCount3(int[][] res, int m, int n){
		//dp solution
		//int[][] res = new int[m+1][n+1];
		boolean blocked = false;
		for(int i=1; i<= m; i++){
			if(res[i][1] ==0) blocked = true;
			if(blocked){
				res[i][1] = 0;
			}
		}
		blocked = false;
		for(int j=1; j<=n; j++){
			if(res[1][j] == 0) blocked = true;
			if(blocked)
			res[1][j] = 0;
		}
		
		for(int i=2; i<=m; i++){
			for(int j=2; j<=n; j++){
				if(res[i][j] != 0)
 				res[i][j] = res[i-1][j] + res[i][j-1];
			}
		}
		
		return res[m][n];
		
	}
	
	/**
	 * print all the paths.
	 * @param res
	 * @param path
	 * @param block
	 * @param m
	 * @param n
	 * @param i
	 * @param j
	 */
	public static void paths(List<List<Point>> res, List<Point> path, int[][] block, int m, int n, int i, int j){
		//List<Point> path = new ArrayList<Point>(inPath); //this way too costly.
		path.add(new Point(i, j));
		if(i==m && j== n){
			res.add(new ArrayList<Point>(path)); //reached to the end. //copy it
			return;
		}else if(i > m || j > n){
			return;
		}
		
		if(i < m && block[i+1][j] != 0){ //no overflow.
			paths(res, path, block, m, n, i+1, j); //depth first search.
			path.remove(path.size()-1); //recover the original content.
		}
		
		if(j < n && block[i][j+1] != 0){
			paths(res, path, block, m, n, i, j + 1);
			path.remove(path.size()-1);
		}
	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public String toString(){
			return "{ " + x + "," + y + "}";
		}
	}
}
