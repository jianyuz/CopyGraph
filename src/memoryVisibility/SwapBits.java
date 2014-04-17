package memoryVisibility;

/**
 * swap odd and even bits.
 * @author zhouzhou
 *
 * 0001
 * 0010
 * 0011
 * 0100
 * 0101
 * 0110
 * 0111
 * 1000
 * 1001
 * 
 */
public class SwapBits {

	public static void main(String[] args){
		System.out.println(Integer.toBinaryString(5));
		System.out.println(Integer.toBinaryString(swapBits(5)));
	}
	public static int swapBits(int input){
		//extract odd bits and move to even position
		//extract event bits then by moving to right first.
		return ((input & 0x55555555) << 1) | ((input >> 1) & 0x55555555);
	}
}
