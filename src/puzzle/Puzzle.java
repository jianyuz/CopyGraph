package puzzle;

import java.util.Set;

public interface Puzzle<P, M> {

	P initialPosition();
	
	Set<M> moves(P p);
	
	P move(P p, M m);
	
	boolean isGoal(P p);
	
}
