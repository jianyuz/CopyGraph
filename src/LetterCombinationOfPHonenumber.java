import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LetterCombinationOfPHonenumber {

	public ArrayList<String> letterCombinations(String digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<String> res = new ArrayList<String>();
        
        //we can use an string array to represent it too.
        Map<String, String[]> m = new HashMap<String, String[]>();
        m.put("2", new String[] {"a", "b", "c"});
        m.put("3", new String[] {"d", "e", "f"});
        m.put("4", new String[] {"g", "h", "i"});
        m.put("5", new String[] {"j", "k", "l"});
        m.put("6", new String[] {"m", "n", "o"});
        m.put("7", new String[] {"p", "q", "r", "s"});
        m.put("8", new String[] {"t", "u", "v"});
        m.put("9", new String[] {"w", "x", "y", "z"});
        
        char[] chars = digits.toCharArray();
        
        doCombo(chars, 0,  m,  "",  res);
        
        return res;
    }
    
    public void doCombo(char[] chars, int pos, Map<String, String[]> m, String s, 
        ArrayList<String> res){
        if(pos == chars.length){
            //add s to res
            res.add(s);
            return;
        }
        
        char c = chars[pos];
        String[] letters = m.get("" + c);
        for(int j=0; j< letters.length; j++){
            doCombo(chars, pos + 1, m, s + letters[j], res);
        }
        
    }
}
