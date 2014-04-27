package memoryVisibility;

public class SortRotatedArray {

	public static void main(String[] args){
		int[] input = {
				15, 16, 19, 20, 25, 1, 3, 4, 5, 7,  10,  14
		};
		
		System.out.println(find(input, 5));
		System.out.println(find(input, 15));
		System.out.println(find(input, 19));
		System.out.println(find(input, 1));
		System.out.println(find(input, 14));
		System.out.println(find(input, 10));
		System.out.println(find(input, 2));
		
		System.out.println();
		
		System.out.println(find1(input, 5));
		System.out.println(find1(input, 15));
		System.out.println(find1(input, 19));
		System.out.println(find1(input, 1));
		System.out.println(find1(input, 14));
		System.out.println(find1(input, 10));
		System.out.println(find1(input, 2));
	}
	
	public static int find(int[] input, int a){
		if(input == null || input.length == 0) return -1;

		int start = 0;
		int end = input.length-1;
		
		return find(input, a, start, end);
	}
	
	
	public static int find1(int[] input, int a){
		if(input == null || input.length == 0) return -1;

		int start = 0;
		int end = input.length-1;
		
		return find(input, a, start, end);
	}
	
	//iteration solution
	public static int find1(int[] input, int a, int start, int end){
		
		
		while(start <= end){
			int mid = start + (end - start)/2;
		
			if(input[mid] == a){
				return mid;
			}
			
			if(input[mid] > input[start] ){
				if( a >= input[start] && a < input[mid]){//sorted lower half
					end = mid -1;
				}else{//mixed upper half.
					end = mid + 1;
				}
			}else{//sorted upper half.
				if(a > input[mid] && a <= input[end]){ //the later part can be changed to a < input[start] same as the condition used here.
					end = mid + 1;
				}else{//mixed lower half.
					end = mid -1;
				}
			}
		}
		
		return -1;
		
	}

	public static int find(int[] input, int a, int start, int end){
		
		if(start > end) return -1;
		int mid = start + (end - start)/2;
		
		if(input[mid] == a){
			return mid;
		}else if(input[mid] < a ){
			if(input[end] > input[mid]){//sorted upper half
				if(input[end] >= a){//in range.
					return find(input, a, mid+1, end);
				}else{
					return find(input, a, start, mid-1); //mixed lower half
				}
			}else{//mixed upper half.
				return find(input, a, mid+1, end);
			}
		}else{
			if(input[start] < input[mid]){//sorted lower half
				if(input[start] <= a){//in range.
					return find(input, a, start, mid-1);
				}else{
					return find(input, a, mid+1, end);
				}
			}else{//mixed lower half.
				return find(input, a, start, mid-1);
			}
		}
		
	}
	
	
}
