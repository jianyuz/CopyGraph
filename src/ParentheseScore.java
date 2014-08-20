import java.util.Stack;


/**
 * calculate the score of parenthese
 * if () is 1
 * if (L) is score * 2   //parenthese in stack.
 * if L1 L2 is score 1 + score 2  //use stack to represent
 * @author zhouzhou
 *
 */
public class ParentheseScore {
	
	public static void main(String[] args){
		String input = "(())()()";
		input = "((())(()))";
		System.out.println(calculateScore1(input));
	}
	
	
	/**
	 * master theorem
	 * 
	 * O(n) =2 o(n/2) + n
	 * a = 2
	 * b =2 
	 * c = 1
	 * 
	 *log b a 
	 *c
	 *
	 *nc log k n
	 *nlogn
	 * 
	 * @param input
	 * @return
	 */
	public static int calculateScore1(String input){
		if(input == null || input.length() == 0){
			return 0;
		}
		if(input.length() == 2){
			return 1;
		}
		
		int pairCount = 0;
		int index = 0;
		for(index = 0; index< input.length(); index++){
			if(input.charAt(index) == '('){
				pairCount ++;
			}else{
				pairCount --;
			}
			if(pairCount == 0) break;
		}
		
		
		
		if(index == input.length() -1){
			return calculateScore1(input.substring(1, input.length()-1)) * 2;
		}else{
			//System.out.println("index " + index + " input " + input );
			return calculateScore1(input.substring(0, index+1)) + calculateScore1(input.substring(index+1, input.length()));
		}
		
		
	}
	
	public static  int calculateScore(String input){
		if(input == null || input.length() == 0) return 0;
	
		Stack<Object> s = new Stack<Object>();
		
		char[] chars = input.toCharArray();
		for(char c : chars){
			if(c == '('){
				s.push("(");
			}else if(c == ')'){
				if((s.peek() instanceof String)){
					String temp = (String)s.peek();
					//System.out.println("peek " + temp);
					if(temp.charAt(0) == '('){
						s.pop();
						s.push(1);
					}
				}else{
					//it is integer in
					int sum = 0;
					while(!s.isEmpty() && (s.peek() instanceof Integer)){
						sum += (Integer)s.pop();
					}
					//System.out.println("sum " + sum);
					s.pop();
					s.push(sum * 2);
				}
			}
		}
		
		int score = 0;
		while(!s.empty()){
			score += (Integer)s.pop();
		}
		
		return score;
	}

}
