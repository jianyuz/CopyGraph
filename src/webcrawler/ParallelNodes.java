package webcrawler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelNodes {

	public static <T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results){
		for(final Node<T> node: nodes){
			exec.execute(new Runnable(){

				@Override
				public void run() {
					results.add(node.compute());
				}
				
			});
		}
	}
	
	public static <T> Collection<T> getParallelResult(List<Node<T>> nodes) throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		Queue<T> result = new ConcurrentLinkedQueue<T>();
		parallelRecursive(exec, nodes, result);
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException{
		List<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
		nodes.add(new Node<Integer>(1));
		nodes.add(new Node<Integer>(2));
		nodes.add(new Node<Integer>(2));
		Collection<Integer> col = getParallelResult(nodes);
		System.out.println(col);
	}
	static class Node<T>{
		T data;
		public Node(T data){
			this.data = data;
		}
		public T compute(){
			return data;
		}
	}
}
