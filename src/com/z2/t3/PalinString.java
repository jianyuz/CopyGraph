package com.z2.t3;

public class PalinString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "abacdfgdcaba";
		String res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "abacd";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "ecabacd";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaa";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaaa";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "abcd";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		input = "a";
		res = longestPalin(input);
		System.out.println("longest palindrome substring: " + res);

		System.out.println("============");
		input = "abacdfgdcaba";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "abacd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "ecabacd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaa";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaaa";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "abcd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "a";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		System.out.println("=================");
		input = "abacdfgdcaba";
		res = longestPalinLinear(input);
		System.out.println("longest palindrome substring: " + res);
		
		input = "abacd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "ecabacd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaa";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "egbfaaa";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "abcd";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);

		input = "a";
		res = longestPalinDP(input);
		System.out.println("longest palindrome substring: " + res);


	}

	public static String longestPalinDP(String input) {
		// dynamic programming need a table to remember the result.
		// formula to generate higher dimension result from lower dimension.
		int n = input.length();
		boolean[][] isPalin = new boolean[n][n];

		int maxLen = 1;
		int start = 0;
		for (int i = 0; i < n; i++) {
			isPalin[i][i] = true;
			start = i;
		}

		for (int i = 0; i < n - 1; i++) {
			if (input.charAt(i) == input.charAt(i + 1)) {
				isPalin[i][i + 1] = true;
				maxLen = 2;
				start = i;
			}
		}

		for (int len = 3; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				if (input.charAt(i) == input.charAt(j) && isPalin[i + 1][j - 1]) {
					isPalin[i][j] = true;
					start = i;
					maxLen = len;
				}
			}
		}

		return input.substring(start, start + maxLen);
	}

	public static String longestPalin(String input) {
		if (input == null || 0 == input.length())
			return input;
		int n = input.length();
		int maxLen = 0;

		String temp, res = null;
		for (int i = 0; i < n; i++) {
			temp = expandFromCenter(input, i, i);
			if (temp.length() > maxLen) {
				res = temp;
				maxLen = temp.length();
			}
			if (i == n - 1)
				break;
			temp = expandFromCenter(input, i, i + 1);
			if (temp.length() > maxLen) {
				res = temp;
				maxLen = temp.length();
			}

		}
		return res;
	}

	private static String expandFromCenter(String input, int left, int right) {
		int n = input.length();
		boolean expanded = false;

		while (left >= 0 && right < n
				&& input.charAt(left) == input.charAt(right)) {
			left--;
			right++;
			expanded = true;
		}

		return expanded ? input.substring(left + 1, right) : "";
	}

	public static String longestPalinLinear(String input) {
		String pInput = preProcess(input);

		int center = 0, rightEdge = 0;
		int len = pInput.length();
		int[] palinLen = new int[len];
		palinLen[0] = 0;

		int reverseI;
		int leftIncre, rightIncre;
		for (int i = 1; i < len - 1; i++) {
			reverseI = 2 * center - i;
			palinLen[i] = (reverseI >= 0) ? Math.min(rightEdge - center,
					palinLen[reverseI]) : 0;

			rightIncre = i + palinLen[i] + 1;
			leftIncre = i - palinLen[i] - 1;
			while (leftIncre >= 0 && rightIncre < len
					&& pInput.charAt(leftIncre) == pInput.charAt(rightIncre)) {
				palinLen[i] += 1;
				rightIncre = i + palinLen[i] + 1;
				leftIncre = i - palinLen[i] - 1;

			}
			//update center and right edge.
			if (center + palinLen[i] > rightEdge) {
				center = i;
				rightEdge = center + palinLen[i];
			}

		}

		int maxLen = 0;
		int maxCen = 0;
		for (int j = 0; j < len - 1; j++) {
			//System.out.println(palinLen[j] + " ");
			if(palinLen[j] > maxLen){
				maxLen = palinLen[j];
				maxCen = j;
			}
		}
		return input.substring((maxCen - 1 - maxLen)/2, maxLen);
	}

	/*
	 * preprocess to consistently treat the odd/even palindrome.
	 */
	public static String preProcess(String input) {
		char[] chars = input.toCharArray();
		int newLen = chars.length * 2 + 1;
		char[] nChars = new char[newLen];

		for (int i = 0; i < chars.length; i++) {
			nChars[2 * i] = '#';
			nChars[2 * i + 1] = chars[i];
		}
		nChars[newLen - 1] = '#';

		return new String(nChars);
	}

}
