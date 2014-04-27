package memoryVisibility;

public class StringInSortedArrayWithEmptyInterleaved {

	public static void main(String[] args){
		 String[] input = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		 
		 System.out.println(search(input, "ball"));
		 System.out.println(search(input, "at"));
		 System.out.println(search(input, "ballcar"));
		 System.out.println(search(input, "dad"));
		 System.out.println(search1(input, "ball"));
		 System.out.println(search1(input, "at"));
		 System.out.println(search1(input, "ballcar"));
		 System.out.println(search1(input, "dad"));
	}
	
	/**
	 * too complicated, we may as well just search toward one direction.
	 * @param input
	 * @param a
	 * @return
	 */
	public static int search(String[] input, String a){
		if(input == null || input.length == 0) return -1;
		if(a == null || a.equals("")) return -1;
		int low = 0;
		int high = input.length-1;
		while(low <= high){
			int mid = low + (high - low)/2;
			if(input[mid].equals(a)){
				return mid;
			}else if(input[mid].equals("")){
				//search to left and right boundary.
				int tmp = mid-1;
				while(tmp >=low && input[tmp].equals("")){
					tmp --;
				}
				
				int tmp1 = mid+1;
				while(tmp1 <=high && input[tmp1].equals("")){
					tmp1++;
				}
				
				if(tmp>=low && a.equals(input[tmp])){
					return tmp;
				}
				
				if(tmp1 <= high && a.equals(input[tmp1])){
					return tmp1;
				}
				
				if(tmp >=low && a.compareTo(input[tmp]) <0 ){
					high = tmp-1;
				} else if(tmp1 <= high && a.compareTo(input[tmp1]) > 0){
					low = tmp1+1;
				} else{
					return -1;
				}
			}else if(a.compareTo(input[mid]) < 0){
				high= mid -1;
			}else {
				low = mid + 1;
			}
		}
		
		return -1;
		
	}
	
	
	public static int search1(String[] input, String a){
		if(input == null || input.length == 0) return -1;
		if(a == null || a.equals("")) return -1;
		int low = 0;
		int high = input.length-1;
		while(low <= high){
			int mid = low + (high - low)/2;
			
			//first search toward one direction to make sure we get an non empty element
			//or reach to the boundary.
		    int tmp = mid;
		    while(tmp <=high && input[tmp].equals("") )
		    	tmp ++;
		    
		    if(tmp > high){
		    	//didn't find.
		    	high = mid -1;
		    } else if(input[tmp].equals(a)){
				return tmp;
			} else if(input[tmp].compareTo(a) < 0){
				low = tmp + 1;
				
			}else{
				high = tmp - 1;
			}
		}
		
		return -1;
		
	}
}
