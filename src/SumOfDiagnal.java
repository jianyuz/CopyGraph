import java.util.Arrays;

/**
 * add the sum of diagonal elements of a spiral matrix
 * with n rows and n columns.
 * n are odd number only.
 * 
 * two approachs
 * 1) n2 complexity.
 * 2) O(n)
 * @author zhouzhou
 *
 */
public class SumOfDiagnal {
	
	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT;
		
		Direction next(){
			switch(this){
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
			case UP:
				return RIGHT;
			case RIGHT:
				return DOWN;
			}
			return null;
		}
	}
	
	public static int[][] constructSpiralMatrix(int n){
		int count = 1;
		
		int[][] matrix = new int[n][n];
		
		int mid = n/2;
		
		matrix[mid][mid] = 1;
		int curi = mid;
		int curj = mid;
		
		for(int i=1; i<=n/2; i++){	
			Direction curDirection = Direction.DOWN;
			int size = (i * 2 + 1) * (i * 2 + 1);
			
			while (count < size) {
				int tmpi = curi, tmpj = curj;
				if (curDirection == Direction.DOWN) {
					tmpi = curi + 1;
				} else if (curDirection == Direction.LEFT) {
					tmpj = curj - 1;
				} else if (curDirection == Direction.UP) {
					tmpi = curi - 1;
				} else if (curDirection == Direction.RIGHT) {
					tmpj = curj + 1;
				}
				if(tmpi >= mid-i && tmpi <= mid + i && tmpj >= mid-i
						&& tmpj <= mid + i) {
					curi = tmpi;
					curj = tmpj;
				    matrix[curi][curj] = ++count;
					//System.out.println("add i " + curi + " j " + curj + " direction " + curDirection  + " "  + count);
				}else{
					curDirection = curDirection.next();
					//System.out.println("next " + curDirection);
				}
			}
			//System.out.println("next layer");
			
		}
		return matrix;
	}
	
	public static int diagSum(int n){
		int prev = 1;
		int next = 0;
		int sum = prev;
		for(int i = 1; i<= n/2; i++){
			int base = 2 * i;
			next = base*base + 1;
			sum += next;
			//System.out.println(next);
			prev = next + next - prev;
			//System.out.println(prev);
			sum += prev;
		}
		int times = 1;
		prev = 1;
		for(int i=1; i<= n/2; i++){
			prev +=  2 * times;
			//System.out.println(prev);
			sum += prev;
			times ++;
			prev +=  2 * times;
			//System.out.println(prev);
			sum += prev;
			times ++;
		}
		
		// 1 5 9 17 25 37 49
		// 1 n2 + 1  and + the difference.
		// 1 3 7 13 21 31 43 57 73
		// diff 2 4 6 8 10 12 14 16
		return sum;
	}

	public static void main(String[] args){
		int n = 7;
		int[][] matrix = constructSpiralMatrix(n);
		
		for(int i=0; i< matrix.length; i++){
			System.out.println(Arrays.toString(matrix[i]));
		}
		
		int diagSum = 0;
		
		for(int i= 0; i < n; i++ ){
			diagSum += matrix[i][i];
		}
		
		for(int i=0; i< n; i++){
			diagSum += matrix[i][n-1-i];
		}
		
		diagSum -= matrix[n/2][n/2];
		
		System.out.println("Diag sum is " + diagSum);
		
		// 1 5 9 17 25 37 49
		// 1 n2 + 1  and + the difference.
		// 1 3 7 13 21 31 43 57 73
		// diff 2 4 6 8 10 12 14 16
		
		System.out.println("Diag sum1 is " + diagSum(n));
	}
}
