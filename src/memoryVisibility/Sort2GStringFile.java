package memoryVisibility;

public class Sort2GStringFile {

	/**
	 * 2G file with string line.
	 * use external sort.
	 * divide into 4 pieces
	 * 200M each piece 
	 * 10 bytes per string
	 * 2G memory
	 * load each piece into memory and sort.
	 * then load first 20M of each pieces. in total 200M
	 * sort in memory to get first 20M
	 * merge sort.
	 * if one piece is empty,
	 * load the next section
	 * 
	 * 2G / (10 + 1), 
	 * 
	 * the one is used for output buffer.
	 * 
	 */
	public static void sortFile(){
		
	}
}
