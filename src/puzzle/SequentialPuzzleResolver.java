package puzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequentialPuzzleResolver<P, M> {
	private final Puzzle<P, M> puzzle;
	private final Set<P> visited = new HashSet<P>();
	
	public SequentialPuzzleResolver(Puzzle<P, M> puzzle){
		this.puzzle = puzzle;
	}
	
	public List<M> solve(){
		P pos = puzzle.initialPosition();
		return search(new Node<P, M>(pos, null, null));
	}
	
	private List<M> search(Node<P, M> node) {
		if (!visited.contains(node.p)) {
			visited.add(node.p);
			if (puzzle.isGoal(node.p)) {
				return node.asMoveList();
			}

			Set<M> moves = puzzle.moves(node.p);
			for (M m : moves) {
				P p = puzzle.move(node.p, m);

				List<M> ms = search(new Node<P, M>(p, m, node));
				if(ms != null)
					return ms; //don' return prematurely if it is empty.
			}
		}
		return null;
	}
	
}
