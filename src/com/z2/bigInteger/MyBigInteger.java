package com.z2.bigInteger;

import java.util.Arrays;

/**
 * represent integer using arrays of int.
 * class is immutable.
 * 
 * @author zhouzhou
 *
 */
public class MyBigInteger extends Number implements Comparable<MyBigInteger>{

	public static final MyBigInteger ZERO = new MyBigInteger(new int[0], 0);
	public static void main(String[] args){
		long diff = 0;
		diff = (5 & LONG_MASK) - (1 & LONG_MASK) ;
		System.out.println(diff);
		System.out.println(Long.toBinaryString(1 & LONG_MASK));
		System.out.println(Long.toBinaryString(0xffffffff & LONG_MASK));
		System.out.println(Long.toBinaryString(diff));
		System.out.println(diff & LONG_MASK);
		System.out.println(((int)diff));
		System.out.println(diff >> 32);//borrow is 0 otherwise it is -1
		
		System.out.println(Integer.parseInt("3b9aca00", 16));
		
		MyBigInteger m1 = new MyBigInteger("2345678900000", 10);
		MyBigInteger m2 = new MyBigInteger("1111111100000", 10);
		MyBigInteger res = m1.add(m2);
		System.out.println("m1 + m2 "  + res);
	}
	
	/* zero[i] is a string of i consecutive zeros. */
         private static String zeros[] = new String[64];
         //generate variable lengths of zeros.
         static {
             zeros[63] =
                "000000000000000000000000000000000000000000000000000000000000000";
             for (int i=0; i<63; i++)
                zeros[i] = zeros[63].substring(0, i);
         }
    
	/**
	 * obtain value of an int as if it is unsigned.
	 */
	static final long LONG_MASK = 0x00000000ffffffffL;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * represent the sign of big integer.
	 * -1 0 or 1.
	 */
	final int signum;
	
	/**
	 * bigendian. nost significantin zeroes element.
	 * most significant first.
	 * like writtern on paper from left to right.
	 * little endian, arithmatic calculation order.
	 */
	final int[] mag;
	
	public MyBigInteger (byte[] val){
		if(val == null || val.length == 0){
			throw new NumberFormatException("zero length");
		}
		
		//val[0] most significate digit.
		if(val[0] < 0){
			mag = makePositive(val);
			signum = -1;
		}else{
			mag = stripLeadingZeroBytes(val);
			signum = (mag.length == 0? 0: 1);
		}
	}
	
	/**
	 * array represent 2's complement number
	 * return minimal unsigned whose value is -a
	 * note return the int array
	 * 
	 * @param val
	 * @return
	 */
	private int[] makePositive(byte[] val) {
		int keep, k;
		int byteLen = val.length;
		
		for(keep = 0; keep < byteLen && val[keep] == -1; keep ++)
			;
		
		for(k = keep; keep < byteLen && val[keep] == 0; keep ++)
		;
		
		//check if all non sign bytes are 0x00, if so allocate more space for extra output byte.
		
		int extraByte = (k== byteLen)? 1: 0;
		//calculate the length of int array
		int intLength = ((byteLen - keep + extraByte) + 3) >>> 2;
		int[] res = new int[intLength];
		
		int b = byteLen -1;
		//copy one's complement of input into int array.
		//leaving extra byte in int as 0x00
		for(int i = intLength -1; i>=0; i--){
			res[i] = val[b--] & 0xff;
			int bytesTransfer = Math.min(3, b-keep + 1);//left bytes;
			if(bytesTransfer < 0)
				bytesTransfer = 0;
			for(int j=8; j<= bytesTransfer * 8; j = j+8){
				res[i] |= (val[b--] & 0xff) << j;
			}
			//mask indicate which bits must be complemented.
			int mask = -1 >>> (8 * (3 -bytesTransfer));
			res[i] = ~res[i] & mask;
		}
		
		//add one'complement to generate two's complement.
		for(int i= res.length -1; i>=0; i--){
				res[i] = (int)((res[i] & LONG_MASK) + 1);
				if(res[i] != 0) break;
		}
		return res;
		
	}
	
	private static int[] makePositive (int a[]){
		int keep, k;
		for(keep = 0; keep< a.length && a[keep] == -1; keep++);
		for(k = keep; k< a.length && a[k] ==0; k++);
		int extraInt = (k == a.length)? 1: 0;
		
		int res[] = new int[a.length - keep + extraInt];
		for(int i= keep; i< a.length; i++){
			res[i -keep + extraInt] = ~a[i];
		}
		for(int i = res.length - 1;  ++res[i] ==0; i--);
		return res;
		
		
	}
	
	// bitsPerDigit in the given radix times 1024
        // Rounded up to avoid underallocation.
	/**
	 * 1.bitsPerDigit是用于计算radix进制m个有效数字  转换成2进制所需bit位[假设所需x位],我们来看一个计算式:
radix^m - 1 = 2^x - 1, 解这个方程得 x = m * log2(radix) , 现在m是几位有效数字,常量就只有 log2(radix),
这是一个小数,这不是我们喜欢的,所以我们希望用一个整数来表示,于是我们把他扩大1024倍然后取整,
例如3进制 bitsPerDigit[3] = 1624(我用计算器算了一下 x = log2(3) * 1024 ~= 1623.xxx) ,我们队这个数取整,
为什么取1624呢,其实只要不超过太多都可以的,你可以设置为1620,1600,1610...;
我们来看代码 (int)(((numDigits * bitsPerDigit[radix]) >>> 10) + 1);
numDigits 是有效数字m,  bitsPerDigit[radix]这个是我们的常量 ,因为之前我们扩大了1024现在理所当然的要缩小1024,
于是我们作整除操作右移10位, 很明显我们丢了一部分小数[因为我们作的是整除],所以再+1保证位数一定够!
(int)(((numDigits * bitsPerDigit[radix]) >>> 10) + 1);这整个算下来就是 一个radix进制的数numDigits位有效数字转换成2进制所需bit位
2.digitsPerInt是用于计算radix进制数最多几位有效数字能被一个int型容纳 [假设是x位有效数字],我们来看一个计算式:
radix^x - 1 <= 2^31 - 1(x∈N*), 解这个方程得 x <= 31*log radix(2) , 然后就有了那些常量
	 */
         private static long bitsPerDigit[] = { 0, 0,
             1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247, 3402, 3543, 3672,
             3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633,
             4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210,
                                                5253, 5295};
         
         /**
          * number of digits can fit into java int without going negative.
          * radix ** n < 2 ** 31;
          * intRadix[i] = i ** digitPerInt[i];
          * 
          */
         private static int digitsPerInt[] = {0, 0, 30, 19, 15, 13, 11,
                   11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6,
                      6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};
    
         
         private static int intRadix[] = {0, 0,
                      0x40000000, 0x4546b3db, 0x40000000, 0x48c27395, 0x159fd800,
                      0x75db9c97, 0x40000000, 0x17179149, 0x3b9aca00, 0xcc6db61,
                     0x19a10000, 0x309f1021, 0x57f6c100, 0xa2f1b6f,  0x10000000,
                      0x18754571, 0x247dbc80, 0x3547667b, 0x4c4b4000, 0x6b5a6e1d,
                      0x6c20a40,  0x8d2d931,  0xb640000,  0xe8d4a51,  0x1269ae40,
                      0x17179149, 0x1cb91000, 0x23744899, 0x2b73a840, 0x34e63b41,
                      0x40000000, 0x4cfa3cc1, 0x5c13d840, 0x6d91b519, 0x39aa400
                  };
         
         
         /*
         2933      * The following two arrays are used for fast String conversions.  Both
         2934      * are indexed by radix.  The first is the number of digits of the given
         2935      * radix that can fit in a Java long without "going negative", i.e., the
         2936      * highest integer n such that radix**n < 2**63.  The second is the
         2937      * "long radix" that tears each number into "long digits", each of which
         2938      * consists of the number of digits in the corresponding element in
         2939      * digitsPerLong (longRadix[i] = i**digitPerLong[i]).  Both arrays have
         2940      * nonsense values in their 0 and 1 elements, as radixes 0 and 1 are not
         2941      * used.
         2942      */
              private static int digitsPerLong[] = {0, 0,
                  62, 39, 31, 27, 24, 22, 20, 19, 18, 18, 17, 17, 16, 16, 15, 15, 15, 14,
                 14, 14, 14, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12};
         
             private static MyBigInteger longRadix[] = {null, null,
                 valueOf(0x4000000000000000L), valueOf(0x383d9170b85ff80bL),
                  valueOf(0x4000000000000000L), valueOf(0x6765c793fa10079dL),
                  valueOf(0x41c21cb8e1000000L), valueOf(0x3642798750226111L),
                  valueOf(0x1000000000000000L), valueOf(0x12bf307ae81ffd59L),
                  valueOf( 0xde0b6b3a7640000L), valueOf(0x4d28cb56c33fa539L),
                  valueOf(0x1eca170c00000000L), valueOf(0x780c7372621bd74dL),
                  valueOf(0x1e39a5057d810000L), valueOf(0x5b27ac993df97701L),
                  valueOf(0x1000000000000000L), valueOf(0x27b95e997e21d9f1L),
                  valueOf(0x5da0e1e53c5c8000L), valueOf( 0xb16a458ef403f19L),
                 valueOf(0x16bcc41e90000000L), valueOf(0x2d04b7fdd9c0ef49L),
                  valueOf(0x5658597bcaa24000L), valueOf( 0x6feb266931a75b7L),
                  valueOf( 0xc29e98000000000L), valueOf(0x14adf4b7320334b9L),
                  valueOf(0x226ed36478bfa000L), valueOf(0x383d9170b85ff80bL),
                  valueOf(0x5a3c23e39c000000L), valueOf( 0x4e900abb53e6b71L),
                  valueOf( 0x7600ec618141000L), valueOf( 0xaee5720ee830681L),
                  valueOf(0x1000000000000000L), valueOf(0x172588ad4f5f0981L),
                  valueOf(0x211e44f7d02c1000L), valueOf(0x2ee56725f06e5c71L),
                  valueOf(0x41c21cb8e1000000L)};

                 /**
                   * Initialize static constant array when class is loaded.
             	 * 1 to 16 constant bigInteger?
                   */
                  private final static int MAX_CONSTANT = 16;
                  private static MyBigInteger posConst[] = new MyBigInteger[MAX_CONSTANT+1];
                  private static MyBigInteger negConst[] = new MyBigInteger[MAX_CONSTANT+1];
                  static {
                     for (int i = 1; i <= MAX_CONSTANT; i++) {
                         int[] magnitude = new int[1];
                         magnitude[0] = i;
                         posConst[i] = new MyBigInteger(magnitude,  1);
                         negConst[i] = new MyBigInteger(magnitude, -1);
                     }
                 }
             /**
             940      * Returns a BigInteger whose value is equal to that of the
             941      * specified {@code long}.  This "static factory method" is
             942      * provided in preference to a ({@code long}) constructor
             943      * because it allows for reuse of frequently used BigIntegers.
             944      *
             945      * @param  val value of the BigInteger to return.
             946      * @return a BigInteger with the specified value.
             947      */
                  public static MyBigInteger valueOf(long val) {
                      // If -MAX_CONSTANT < val < MAX_CONSTANT, return stashed constant
                      if (val == 0)
                          return ZERO;
                      if (val > 0 && val <= MAX_CONSTANT)
                          return posConst[(int) val];
                      else if (val < 0 && val >= -MAX_CONSTANT)
                          return negConst[(int) -val];
              
                      return new MyBigInteger(val);
                  }
              
             
	/*
	 * convert string representation of biginteger into the class.
	 * consistes of optional minus or plus sign followed by digits in radix.
	 * character to digit mapping is offer by Character.digit.
	 * 
	 */
	public MyBigInteger (String val, int radix){
		if(radix < Character.MIN_RADIX || radix > Character.MAX_RADIX){
			throw new NumberFormatException("out of range radix");
		}
		final int len = val.length();
		if(len == 0)
			throw new NumberFormatException("zero length integer");
		
		int index1 = val.lastIndexOf('-');
		int index2 = val.lastIndexOf('+');
		
		//shoud be at position 0
		//sum of them should be -1.
		//if -2, no sign in the string.
		int cursor = 0;
		int sign = 1; //default value
		if(index1 + index2 <= -1){
			if(index1 == 0 | index2 == 0){
				cursor = 1;
				if(len == 1){
					throw new NumberFormatException("zero length integer");
				}
			}
			if(index1 == 0)
				sign = -1;
		}else{
			throw new NumberFormatException("Illegal embedded sign");
		}
		
		int numDigits;
		//skip leading 0 and compute number of digits
		while(cursor < len && Character.digit(val.charAt(cursor), radix) == 0)
			cursor ++;
		if(cursor == len){
			signum = 0;
			mag = ZERO.mag;
			return;
		}
		
		numDigits = len -cursor;
		signum = sign;
		
		//preallocate int array of expected size.
		int numBits = (int)(((numDigits * bitsPerDigit[radix]) >>> 10) + 1);
		int numWords = (numBits + 31) >>> 5; //divide by 32.
		int [] magnitude = new int[numWords];
		
		//process first int that holds the digit group
		//may not fit the whole int type
		int fGroupLen = numDigits % digitsPerInt[radix];
		if(fGroupLen ==0 ){
			fGroupLen = digitsPerInt[radix];
		}
		String group = val.substring(cursor, cursor += fGroupLen);
		magnitude[numWords -1] = Integer.parseInt(group, radix);
		if(magnitude[numWords -1] < 0){
			throw new NumberFormatException("illegal digit");
		}
		
		int superRadix = intRadix[radix]; //radix of per int elment.
		int groupVal = 0;
		
		while(cursor < len){
			group = val.substring(cursor, cursor += digitsPerInt[radix]);
			groupVal = Integer.parseInt(group, radix);
			//need multiply and add
			if(groupVal < 0){
				throw new NumberFormatException("illegal digit");
			}
			destructiveMulAdd(magnitude, superRadix, groupVal);
		}
		mag = trustedStripLeadingZeroInts(magnitude);
		
	}
	
	private int[] trustedStripLeadingZeroInts(int[] value) {
		int vLen = value.length;
		int keep;
		for(keep = 0; keep< vLen&& value[keep] ==0; keep ++)
			;
		return keep == 0? value: Arrays.copyOfRange(value, keep, vLen);
	}

	private void destructiveMulAdd(int[] val, int superRadix, int groupVal) {
		//perform the multiplication word by word
		
		long ylong = superRadix & LONG_MASK;
		long zlong = groupVal & LONG_MASK;
		int len = val.length;
		
		long product = 0;
		long carry = 0;
		for(int i = len -1; i>=0; i--){
			product = (val[i] & LONG_MASK) * ylong + carry;
			val[i] = (int) product;
			carry = product >>> 32;
		}
		
		//perform addition
		long sum = (val[len-1] & LONG_MASK) + zlong;
		val[len-1] = (int) sum;
		carry = sum >>> 32;
		for(int i = len-2; i>=0; i--){
			sum = (val[i] & LONG_MASK) + carry;
			val[i] = (int) sum;
			carry = sum >>> 32;
		}
	}

	/** for internal use assume the argument are correct
	 * used by negate.
	 * @param mag
	 * @param signum
	 */
	private MyBigInteger (byte[] mag, int signum){
		this.signum = (mag.length == 0? 0: signum);
		this.mag = stripLeadingZeroBytes(mag);
	}
	
	private MyBigInteger(int[] mag, int signum){
		this.signum = (mag.length == 0? 0: signum);
		this.mag = mag;
	}
	
	private MyBigInteger(long val) {
		if(val < 0){
			val = -val;
			signum = -1;
		}else
			signum = 1;
		
		int highWord = (int) (val >>> 32);
		if(highWord == 0){
			mag = new int[1];
			mag[0] = (int)val;
		}else{
			mag = new int[2];
			mag[0] = highWord;
			mag[1] = (int)val;
		}
	}

	/**
	 * myBigInteger is immutable thus create a new instance for it
	 * @return
	 */
	public MyBigInteger negate(){
		return new MyBigInteger(this.mag, -this.signum);
	}
	
	/**
	 * return copy of input array stripped of leading zero bytes.
	 * @param mag2
	 * @return
	 */
	private int[] stripLeadingZeroBytes(byte[] mag2) {
		int byteLen = mag2.length;
		int keep;
		for(keep = 0; keep < byteLen && mag2[keep] == 0; keep ++);
		
		//allocate new array and copy relevant part of input array;
		int intLength = ((byteLen - keep) + 3) >>> 2; //equivalent to be /divide by 4 ;
		int[] res = new int[intLength];
		
		int b = byteLen -1;
		for( int i = intLength -1; i>=0; i--){//copy byte into int array.
			res[i] = mag2[b--] & 0xff;
			int remain = b - keep + 1;
			int byteToCopy = Math.min(3,  remain);
			for(int j=8 ; j <= (byteToCopy << 3); j+=8){ //<<3 == * 8
				res[i] |= ((mag2[b--] & 0xff) << j);
			}
		}
		return res;
	}

	/**add big integer
	 * one's complement's two representation of 0
	 * 2 n-1 -a and 2^ (n-1) -1 numbers
	 * wrap around end borrow or carry.
	 * 2's complement substracting from 2N one bit longer.
	 * equivalent to one 's complement + 1.
	 * most negative 1 most significnt bit and others are 0.
	 * one extra negative number compared to one's complement system.
	 * weight representation of 2's complements number
	 * higher big -1 weight. all other positive.
	 * sign extension when shifting.
	 * copy from 1 byte to 2 bytes, copy the sign bit.and repeat it.
	 * most negative number is weird, no corresponding positive number in system.
	 * overflow examination 
	 * left most two bits. they are not the same.
	 * 1111111 represent -1 in 2 's complements.
	 * 
	 * @param val
	 * @return
	 */
	public MyBigInteger add(MyBigInteger val){
		if(val.signum == 0){
			return this;
		}
		if(signum == 0)
			return val;
		
		if(val.signum == signum){
			return new MyBigInteger(add(mag, val.mag), signum);
		}
		
		//if different sign
		//compare magnitude.
		//then sutract
		int cmp = compareMagnitude(val);
		if(cmp == 0){
			return ZERO;
		}
		
		int[] resMag = (cmp > 0)? subtract(mag, val.mag): subtract(val.mag, mag);
		
		resMag = trustedStripLeadingZeroInts(resMag);
		return new MyBigInteger(resMag, cmp== signum? 1: -1);
	}
	
	public MyBigInteger subtract(MyBigInteger val){
		if(val.signum ==0)
			return this;
		if(this.signum == 0)
			return val;
		
		if(val.signum != signum){
			return new MyBigInteger(add(mag, val.mag), signum);
		}
		int cmp = compareMagnitude(val);
		if(cmp == 0){//magnitude the same.
			return ZERO;
		}
		
		int[] resMag = (cmp > 0? subtract(mag, val.mag): subtract(val.mag, mag));
		resMag = trustedStripLeadingZeroInts(resMag);
		return new MyBigInteger(resMag, (cmp ==signum)? 1: -1); //same sign 
	}
	
	//array is object in java
	//when checking borrow use >> 32 signed shift.
	
	private int[] subtract(int[] big, int[] little) {
		int bigLen = big.length;
		int littleLen = little.length;
		int res[] = new int[bigLen];
		
		long diff = 0;
		//substract common part
		while(littleLen > 0){
			diff = (big[--bigLen] &LONG_MASK) -
					(little[--littleLen] & LONG_MASK) + (diff >> 32);
			res[bigLen] = (int) diff;
		}
		
		//substract the remainder of long int array while borrow propagate.
		boolean borrow = (diff >> 32 != 0);
		while(bigLen > 0 & borrow){
			borrow = ((res[--bigLen] = big[bigLen] -1) == -1);
		}
		
		//copy the remainder of longer number
		while(bigLen > 0){
			res[--bigLen] = big[bigLen];
		}
		
		return res;
	}

	private int[] add(int[] mag, int[] mag1) {
		if(mag.length < mag1.length){
			int[] tmp = mag1;
			mag1= mag;
			mag = tmp;
		}
		//swap to make mag length longer.
		int magLen = mag.length;
		int mag1Len = mag1.length;
		int res[] = new int[magLen];
		long sum = 0;
		
		//add common part
		while(mag1Len > 0){
			sum = (mag[--magLen] & LONG_MASK ) +
					(mag1[--mag1Len] & LONG_MASK) + (sum >>> 32);
			res[magLen] = (int) sum;
		}
		
		//copy remainder of longer array while carry propagation is required.
		//Note subexpressions are evaluated from left to right.
		boolean carry = sum >>> 32 != 0;
		while(magLen > 0 && carry){
			carry = (res[--magLen] = mag[magLen] + 1) == 0;//magLen is decreased already.
		}
		//copy remainder
		while(magLen > 0){
			res[--magLen] = mag[magLen];
		}
		
		//grow result if necessry
		//carry is still on and we run out of maglen array.
		if(carry){
			int[] bigger = new int[res.length + 1];
			System.arraycopy(res, 0, bigger, 1, res.length);
			bigger[0] = 0x01;
			return bigger;
		}
		
		return res;
	}

	@Override
	public int compareTo(MyBigInteger o) {
		if(signum == o.signum){
			switch(signum){
			case 1:
				return compareMagnitude(o);
			case -1:
				return o.compareMagnitude(this);
			default:
				return 0;
			}
		}
		
		return signum > o.signum? 1: -1;
	}

	private int compareMagnitude(MyBigInteger o) {
		//compare length.
		int [] m1 = mag;
		int [] m2 = o.mag;
		int len1 = m1.length;
		int len2 = m2.length;
		if(len1 < len2 ) return -1;
		if(len1 > len2) return 1;
		//length the same.
		for(int i=0; i< len1; i++){
			int a = m1[i];
			int b = m2[i];
			if(a != b){
				return ((a & LONG_MASK) < (b & LONG_MASK))? -1: 1;
			}
		}
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.toString());
	}

	@Override
	public long longValue() {
		return Long.parseLong(this.toString());
	}

	@Override
	public float floatValue() {
		return Float.parseFloat(this.toString());
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return Double.parseDouble(this.toString());
	}
	public String toString(){
		return toString(10);
	}

	public MyBigInteger abs(){
		if(signum < 0){
			return this.negate();
		}
		return this;
	}
	private String toString(int radix) {
		 if (signum == 0)
		                return "0";
		 if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
		     radix = 10;
		     // Compute upper bound on number of digit groups and allocate space
		     int maxNumDigitGroups = (4*mag.length + 6)/7;
		     String digitGroup[] = new String[maxNumDigitGroups];
		    
		     // Translate number to string, a digit group at a time
		     MyBigInteger tmp = this.abs();
		     int numGroups = 0;
		     while (tmp.signum != 0) {
		    	 MyBigInteger d = longRadix[radix];
		    	 MutableBigInteger q = new MutableBigInteger(),
		    	 a = new MutableBigInteger(tmp.mag),
		         b = new MutableBigInteger(d.mag);
		    	 MutableBigInteger r = a.divide(b, q);
		    	 MyBigInteger q2 = q.toBigInteger(tmp.signum * d.signum);
		    	 MyBigInteger r2 = r.toBigInteger(tmp.signum * d.signum);
		    	 digitGroup[numGroups++] = Long.toString(r2.longValue(), radix);
		         tmp = q2;
		     }
		     
		     // Put sign (if any) and first digit group into result buffer
		     StringBuilder buf = new StringBuilder(numGroups*digitsPerLong[radix]+1);
		             if (signum<0)
		                 buf.append('-');
		             	buf.append(digitGroup[numGroups-1]);
		             // Append remaining digit groups padded with leading zeros
		             for (int i=numGroups-2; i>=0; i--) {
		                  // Prepend (any) leading zeros for this digit group
		                 int numLeadingZeros = digitsPerLong[radix]-digitGroup[i].length();
		                 if (numLeadingZeros != 0)
		                      buf.append(zeros[numLeadingZeros]);
		                  buf.append(digitGroup[i]);
		              }
		      return buf.toString();
	}

}
