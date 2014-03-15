public class PlusOne {

	/**
	 * Given a number represented as an array of digits, plus one to the number.
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0)
			return null;

		int len = digits.length;

		int carry = 1;
		int sum;

		for (int i = len - 1; i >= 0; i--) {
			sum = digits[i] + carry;
            digits[i] = sum % 10;
			carry = sum / 10;
		}

		if (carry == 1) {
			int[] newDigits = new int[len + 1];
			newDigits[0] = 1;
			System.arraycopy(digits, 0, newDigits, 1, len);
			return newDigits;
		} else {
			return digits;
		}
	}
}
