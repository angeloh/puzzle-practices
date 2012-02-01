import java.util.Arrays;
public class BinarySearch{
	private final static int NOT_FOUND = -1;
	
	/**
	 * Binary Search with input int array and target number
	 * @param arr
	 * @param target
	 * @return position
	 */
	public static int binarySearch (int [] arr, int target) {
		int left = 0, right = 0, mid = 0;
		right = arr.length-1;
		while(left<=right) {
			mid = (left + right)/2;
			if (arr[mid]> target) {
				right = mid - 1;				
			} else if (arr[mid] < target) {
				left = mid + 1;			
			} else {
				return mid; 
			}
		}
		return NOT_FOUND;
	}
	
	public static void main (String [] argv) {
		int [] intArr = {1, 2, 4, 6, 7, 12, 15, 16, 29};
		System.out.println("The given array is " + Arrays.toString(intArr));
		int target = 12;
		int resultIdx = binarySearch(intArr, target);
		System.out.println("The position of '" + target + "' is at '" + resultIdx + "'");	    
	}
}