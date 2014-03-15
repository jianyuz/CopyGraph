package JigsawPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
	
	Piece[][] pieces;
	Piece[][] solution;
	
	Edge[] inners, outers, flats;
	
	List<Edge> exposed_edges = new ArrayList<Edge>();
	
	void sort(){
		//look through all pieces.
		//and all the edges, add each to inner, outer and flats.
		//look for flat edge to fit into corners.
		//add non falt edge of the corner to the exposed edge.
		
		//find pieces with 2 flats edges.
		//four pieces in total.
		//set them to be 
		//soluction[0][0];
		//solution[m-1][0];
		//solution[0][n-1];
		//solution[m-1][n-1];
		//set the solved orentation.
		//two neighbors.
		//add 8 edges into exposed_edges.
		//edge matching
		//curve matching
		//evenly sampled point.
		
		//2 *(n-2) + 2 *(m-2)
		//one edge flat.
		
	
	}
	
	void aseembleFrame(){
		//fix four corners.
		//match the exposed edges with the inner outer edges of 
		//one flat edge piece.
	}
	
	void solve(){
		for(Edge e : exposed_edges){
			if(e.type == Edge.Type.INNER){
				for(Edge oe: outers){
					if(e.fitWith(oe)){
						exposed_edges.remove(e);
						solution[0][0] = oe.parent;
						//check if exposed edge add to exposed edge.
						exposed_edges.add(oe.parent.bottom);
					}
				}
			}
			
			if(e.type == Edge.Type.OUTER){
				
			}
		}
	}

}
