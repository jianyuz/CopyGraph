package com.z2.medianOfLargeData;

/**
 * There is a file that contains 10G(1000000000) number of integers, please find the Median of these integers. you are given 2G memory to do this
 * 
 * merge sort and then find median.
 * 
 * or use algorithm similiar to bucket sort.
 * bin the integers using top 16 bit
 * then in the middle bin.
 * bin the integers using the bottom 16 bit.
 * 
 * @author zhouzhou
 *
 */
public class MedianOfLargeData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sample = new int[]{
				13, 4, 7, 9, 10, 18, 355, 678, 567, 23456777
		};
		
		int[] sample1 = new int[] {
				1,123,12345,1234567,123456789
		};
		
		System.out.println(medianOfData(sample));
	}
	
	/**
	 * since half of the integer range is 2 ^ 16.
	 * create an array of 2^16 of 8 bytes long. 2^6 * 2^16 - 2 ^ 22, 4 mega.
	 * at most fast less than 2 G
	 * in total 
	 * 
	 * the following is for 32 bit integer
	 * what if the stored integer is 64 bit.
	 * run the bin histogram four passes.
	 * each pass for 16 bit.
	 * 
	 * @param a
	 * @return
	 */
	public static double medianOfData(int[] a){
		int size = (int)Math.pow(2, 16);
		long [] histogram = new long[size];
		int count = 0;
		for(int i = 0 ; i < a.length; i++){
			histogram[ a[i] >>> 16] ++;
			count ++;
		}
		
		int midBinIndex = findMidBin (histogram, (count+1)/2);
		long sumTmp = sumBeforeMidBin(histogram, midBinIndex);
		System.out.println("count " + count);
		long [] botHist = new long[size];
		for(int i=0; i< a.length; i++){
			if( a[i] >>> 16 == midBinIndex){
				botHist[a[i] & 0xFFFF] ++;
			}
		}
		
		int botBinIndex = findMidBin(botHist, (count+1)/2 - sumTmp);
		System.out.println("top mid bin " + midBinIndex);
		System.out.println("bot mid bin " + botBinIndex);
		
		if(count %2 == 0){
			long m1 = (midBinIndex << 16) | botBinIndex;
			int nextIndex = findNextMidBin(botHist, botBinIndex);
			long m2 = (midBinIndex << 16 | nextIndex);
			return ((double)(m1+m2))/2;
		}else{
			return (midBinIndex << 16) | botBinIndex;
		}
	}
	
	/**
	 * Find the bin where the half count lies in.
	 * 
	 * @param bins
	 * @param halfCount
	 */
	private static int findMidBin(long[] bins, long halfCount){
		long accum = 0;
		for(int i = 0; i< bins.length; i++){
			accum += bins[i];
			//System.out.println("acc " + accum);
			if(accum >= halfCount){
				return i;
			}
		}
		return -1;
		
	}
	
	private static int findNextMidBin(long[] bins, int midIndex){
		
		for(int i = midIndex + 1; i< bins.length; i++){
			if(bins[i] > 0){
				return i;
			}
		}
		return -1;
		
	}
	
	private static long sumBeforeMidBin(long[] bins, int midIndex){
		long sum = 0;
		for(int i = 0; i< midIndex; i++){
			sum += bins[i];
		}
		return sum;
	}

}
