package memoryVisibility;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Solver {
	int[][] data;
	int n;
	final CyclicBarrier cb;
	
	public Solver(int[][] matrix){
		data = matrix;
		n = matrix.length;
		cb = new CyclicBarrier(n, new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//merge row.
			}
			
		});
		
		for(int i=0; i< n; i++){
			new Thread(new Worker(i)).start();
		}
	}
	
	class Worker implements Runnable{
		int row;
		public Worker(int i){
			row = i;
		}
		@Override
		public void run() {

			while(!done()){
				//process row
				try {
					cb.await();//wait all thread finish them. then command to merge row
					//and continue won't stop until it is done.
					
				} catch (InterruptedException e) {
					return;
				} catch (BrokenBarrierException e) {
					return;
				}
			}
		}
		
		public boolean done(){
			return false;
		}
		
	}

}
