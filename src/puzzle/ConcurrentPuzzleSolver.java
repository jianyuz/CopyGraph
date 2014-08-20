package puzzle;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentPuzzleSolver<P, M> {
	private final Puzzle<P, M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentMap<P, Boolean> visited = new ConcurrentHashMap<P, Boolean>();
	final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();

	public ConcurrentPuzzleSolver(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.exec = Executors.newCachedThreadPool();
	}

	public List<M> solve() {
		try {
			P p = puzzle.initialPosition();
			exec.execute(newTask(p, null, null));
			Node<P, M> solNode = solution.getValue(); // block until value
														// found.
			return solNode == null ? null : solNode.asMoveList();
		} finally {
			exec.shutdown();//shut down exe no matcher what.
		}
	}

	protected Runnable newTask(P p, M m, Node n) {
		return new SolverTask(p, m, n);
	}

	class SolverTask extends Node<P, M> implements Runnable {

		public SolverTask(P p, M m, Node n) {
			super(p, m, n);
		}

		@Override
		public void run() {
			if(visited.putIfAbsent(p, true) != null || solution.isSet())
				return;
			if(puzzle.isGoal(p)){
				solution.setValue(this);
			}else{
				for(M m : puzzle.moves(p)){
					P np = puzzle.move(p,  m);
					exec.execute(newTask(np, m, this));
				}
			}
		}

	}
	
	class CountingSolverTask extends SolverTask{
		public CountingSolverTask(
				 P p, M m,
				Node n) {
			super(p, m, n);
			active.incrementAndGet();
			
		}
		
		public void run(){
			try{
				super.run();
			}finally{
				if(active.decrementAndGet()==0){
					solution.setValue(null);
				}
			}//termiate when active reaches 0.
		}

		private final AtomicInteger active = new AtomicInteger(0);
		
	}

	class ValueLatch<T> {

		private T value = null;
		private final CountDownLatch done = new CountDownLatch(1);

		public boolean isSet() {
			return done.getCount() == 0;
		}

		public synchronized void setValue(T newValue) {
			if (!isSet()) {
				value = newValue;
				done.countDown();
			}
		}

		public T getValue() throws InterruptedException {
			done.await();
			synchronized (this) {
				return value;
			}
		}
	}
}
