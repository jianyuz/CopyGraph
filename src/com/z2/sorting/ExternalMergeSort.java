package com.z2.sorting;

/**
 * 2G data.
 * one string per line.
 * 
 * suppose 0.5 G Memory.
 * 
 * we can do two rounds of merge passes at the end
 * to reduce disk io.
 * 
 * n log(n/k) first round.
 * O(n)
 * 
 * @author zhouzhou
 *
 */
public class ExternalMergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * divide the files into K chunks  x * K = 2Gb.
	 * load each chunk into memmory and sort it.
	 * save the sorted chunk into temp file.
	 * K temp files.
	 * 
	 * merge the k trunks.
	 * divide the memory with k+1;
	 * s = x/(k+1);
	 * load the first s size from each of the chunk file.
	 * the remain size as output buffer.
	 * do merge sort,
	 * once fill up the k+1 space.
	 * output to final result file.
	 * if exhaust one of the s size piece from a chunk file.
	 * load next piece. 
	 * until all done.
	 * 
	 */

}
