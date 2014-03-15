
public class Candy3 {

public static int candy(int[] ratings) {
        
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        
        int len = ratings.length;
        int min = len; //at least one per candidate.
        
        int[] candies = new int[len]; //used to remember the last allocation when scanning from left to right.
        
        int count = 0;
        
        for(int i=1; i< len; i++){
            //compare with left neighbor only
            if(ratings[i] > ratings[i-1]){
                count ++;
            }else{
                count = 0;
            }
            candies[i] = count;
        }
        
        count = 0;
        for(int i=len-2; i>=0; i--){
        	//scan from right to left and max maximum candies required.
        	//compare which previous record.
            if(ratings[i] > ratings[i+1]){
                count ++;
            }else{
                count = 0;
            }
            min += Math.max(count, candies[i]);
        }
        
        min += candies[len-1];
        
        return min;
        
    }
}
