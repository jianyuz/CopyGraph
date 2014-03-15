package JigsawPuzzle;

public class Edge {

	enum Type {
		INNER, OUTER, FLAT;
	}
	
	Type type;
	Piece parent;
	public boolean fitWith(Edge b){
		return true;
	}
	
	
}
