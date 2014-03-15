package JigsawPuzzle;

/**
 * rectangle outside boarder
 * each interior has four primary neighbors.
 * interlock with neighbor.
 * indent and outer.
 * solve boarder and fill interior
 * frame construction with boarder pice
 * sort piece into different types.
 * 
 * four edges orientation.
 * 
 * @author zhouzhou
 *
 */
public class Piece {
	public enum Orientation{
		Z, N, O, W;
	};
	
	Edge left, right, top, bottom;
	
	Orientation orignal;
	Orientation sovled;
	
}

