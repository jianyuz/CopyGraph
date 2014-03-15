
public class ZigZagConvert {

	
	/**
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

separate insertion of zig pattern from the zag pattern.

	 * @param s
	 * @param nRows
	 * @return
	 */
	public String convert(String s, int nRows) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if( s == null) return null;
        if( s.length() == 0) return "";
        
        if(nRows <=1) return s; //shortcut to avoid memeory overflow.
        
        StringBuilder res = new StringBuilder();
        
        for(int i = 0; i< nRows; i++){
            //go through each row, find all possible char positoin.
            //append it to the res.
            
            int pos = i;
            
            while(pos < s.length()){
                res.append(s.charAt(pos));
                
                //add zag pattern;
                if(i != 0 && i != nRows -1 && pos + 2 * (nRows -1  - i) < s.length()){
                    res.append(s.charAt(pos +  2 *(nRows -1 - i)));
                }
                pos += 2 * nRows -2 ;
            }
        }
        
        return res.toString();
    }
	
	/**
	 * with better varaible tracking.
	 * @param s
	 * @param nRows
	 * @return
	 */
	public String convert1(String s, int nRows) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if( s == null) return null;
        if( s.length() == 0) return "";
        if(nRows <=1) return s;
        
        StringBuilder res = new StringBuilder();
        
        for(int i = 0; i< nRows; i++){
            //go through each row, find all possible char positoin.
            //append it to the res.
            
            int pos = i;
            
            while(pos < s.length()){
                res.append(s.charAt(pos));
               
                //add zag pattern
                if(i != 0 && i != nRows -1 ){
                    int  k = pos + 2 * (nRows -1 -i);
                    if(k < s.length())
                    res.append(s.charAt(k));
                }
                pos += 2 * nRows -2 ;
            }
        }
        
        return res.toString();
    }
}
