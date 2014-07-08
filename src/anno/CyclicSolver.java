package anno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CyclicSolver {

	private static int[][] matrix;
	private static int[] results;
	
	static class Worker implements Runnable{

		private int row;
		private CyclicBarrier cb;
		public Worker(CyclicBarrier cb, int row){
			this.row = row;
			this.cb = cb;
		}
		
		@Override
		public void run(){
			int sum = 0;
			for (int i = 0; i < matrix[row].length; i++) {
				sum += matrix[row][i];
			}
			System.out.println("finished adding row " + row + " sum " + sum);
			results[row] = sum;//set results before awaits
			try {
				System.out.println("num waiting " + cb.getNumberWaiting());
				cb.await();
				System.out.println("after, num waiting " + cb.getNumberWaiting());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
	}
	
	public static void main(String[] args){
		matrix = new int[3][3];
		results = new int[3];
		
		for(int i=0; i< 3; i++){
			for(int j=0; j< 3; j++){
				matrix[i][j] = i * 3 + j;
			}
		}
		
		for(int i=0; i< 3; i++){
			System.out.println(Arrays.toString(matrix[i]));
		}
		
		
		CyclicBarrier cb = new CyclicBarrier(matrix.length, new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int sum = 0;
				System.out.println("results array " + Arrays.toString(results));
				for(int res : results){
					sum += res;
				}
				System.out.println("total matrix sum " + sum);
			}
			
		});
		
		System.out.println("parties " + cb.getParties());
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=0; i< 3; i++){
			executor.submit(new Worker(cb, i));
		}
		
	}
}
