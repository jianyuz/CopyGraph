
public class DistinctiveSubSeq {

	public static void main(String[] args){
		String S = "rabbbit", T = "rabbit";
		System.out.println(numDistinct(S, T));
		System.out.println(distinctSeuence(S, T));
		System.out.println(distincSequence1(S, T));
		
	}
	
	public static int numDistinct(String S, String T) {
        int[] occurence = new int[T.length() + 1];
        occurence[0] = 1;
        for(int i = 0; i < S.length(); i++){
            for(int j = T.length() - 1; j >= 0 ; j--)
                if(S.charAt(i) == T.charAt(j)){
                    if(occurence[j] > 0)
                        occurence[j + 1] += occurence[j];
                }
        }
        return occurence[T.length()];
    }
	
	
	/**
	 * find distinc sequence T in S
	 * f(i, j) distinct T[0:j} in S[0:i]
	 * f(i, j) = f(i-1, j) + s[i] == s[j]? f(i-1, j-1): 0
	 * @param s
	 * @param t
	 * @return
	 */
	public static int distinctSeuence(String s, String t){
		if(s == null || t== null || s.length() < t.length()) return 0;
		int sLen = s.length();
		int tLen = t.length();
		
		int[][] f = new int[sLen+1][tLen+1];
		for(int i=0; i<= sLen; i++){
				f[i][0] = 1;
		}
		
		
		for(int i=1; i<= sLen; i++){
			for(int j=1; j<= tLen; j++){
				f[i][j] = f[i-1][j];
				if(s.charAt(i-1) == t.charAt(j-1)){
					f[i][j] += f[i-1][j-1];
				}
			}
		}
		return f[sLen][tLen];
	}
	
	/**
	 * In the above algorithm, in its i-th iteration, it only needs results in (i-1)-th iteration. So, we can use O(j) space rather than O(i*j).

When we use an 1-dimension array, at the beginning of i-th iteration, recurs[j] means the number of recurrences of T(0:j) in S(0:i-1) and it can be updated as recurs[j]+=recurs[j-1] if the current character matches.
We have to run from T.length down to 0 since we don't want to overwrite recurs[j] which would be used for recurs[j+1] later.
	 * @param s
	 * @param t
	 * @return
	 */
	public static int distincSequence1(String s, String t){
		if(s == null || t== null || s.length() < t.length()) return 0;
		int sLen = s.length();
		int tLen = t.length();
		int[] f = new int [tLen +1];
		f[0] =1;
		for(int i=1; i<= sLen; i++){
			for(int j=tLen; j>=1; j--){
				if(s.charAt(i-1) == t.charAt(j-1)){
					f[j] += f[j-1];
				}
			}
		}
		return f[tLen];
	}

}
