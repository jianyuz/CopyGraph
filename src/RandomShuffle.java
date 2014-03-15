import java.util.Arrays;
import java.util.Random;


public class RandomShuffle {
	
	public static void main(String[] args){
		int[] cards = new int[] {1,2,3,4,5,6,7,8,9};
		shuffle(cards);
		
		System.out.println(Arrays.toString(cards));
	}
	
	public static void shuffle(int[] cards){
		
		int len = cards.length;
		Random rand = new Random();
		for(int i=0; i< len; i++){
			//select random from i to len-1.
			//len -i
			int j = rand.nextInt(len -i) +i;
			swap(cards, i, j);
		}
	}
	
	/**
	 * implementation in javascript
	 * var cards = [1,2,3,4,5,6,7,8,9];
    shuffle(cards);
		
     console.log(cards);
	
	
      function shuffle(cards){	
		for(var i=0, len = cards.length; i< len; i++){
			var j = random(len -i) +i;
			swap(cards, i, j);
		}
	}
	
     function random(n){
          return Math.floor((Math.random() * n)); 
     }
      function swap(cards, i, j){
		var tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
	}
	 * @param cards
	 * @param i
	 * @param j
	 */
	public static void swap(int[] cards, int i, int j){
		int tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
	}

}
