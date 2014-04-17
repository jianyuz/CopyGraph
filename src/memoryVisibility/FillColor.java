package memoryVisibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * fill color region until it reaches the border.
 * @author zhouzhou
 *
 */
public class FillColor {

	public static void main(String[] args){
		int[][] screen = new int[][]{
				{0, 1, 1, 0, 0, 0},
				{1, 0, 0, 1, 1, 1},
				{1, 0, 0, 0, 1, 0},
				{0, 1, 0, 0, 0, 1},
				{0, 0, 1, 1, 1, 0}
		};
		
		int m = screen.length;
		int n = screen[0].length;
		
		fill1(screen, 2, 2, m, n, 1);
		
		for(int i=0; i<m; i++){
			System.out.println(Arrays.toString(screen[i]));
		}
		
	}
	
	/**
	 * recursive propagation.
	 * @param screen
	 * @param x
	 * @param y
	 * @param m
	 * @param n
	 * @param c
	 */
	public static void fill(int[][] screen, int x, int y, int m, int n, int c){
		if(x < 0 || x >= m || y < 0 || y >= n){
			return;
		}
		
		if(screen[x][y] == c) return ;
		
		screen[x][y] = c;
		fill(screen, x + 1, y, m, n, c);
		fill(screen, x, y+1, m, n, c);
		fill(screen, x-1 , y, m, n, c);
		fill(screen, x, y-1, m, n, c);
		
	}
	
	/**
	 * use queue.
	 * Bfs breadth first search.
	 */
	
	public static void fill1(int[][] screen,int x, int y, int m, int n, int c){
		Queue<Point> neighbors = new LinkedList<Point>();
		
		neighbors.offer(new Point(x, y));
		while(!neighbors.isEmpty()){
			Point p = neighbors.poll();
			if(screen[p.x][p.y] == c) continue;
			screen[p.x][p.y] = c;
			neighbors.offer(new Point(p.x + 1, p.y));
			neighbors.offer(new Point(p.x - 1, p.y));
			neighbors.offer(new Point(p.x, p.y -1));
			neighbors.offer(new Point(p.x, p.y + 1));
 		}
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
