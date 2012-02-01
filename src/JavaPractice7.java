import java.util.*;

public class JavaPractice7 {
	public static void main (String [] args) {
//		char [][] mtx = {{'a','b','c','d' },{'e','f','g','h'},{'i','j','k','l'},{'m','n','o','p'}};
		char [][] mtx = {{'a','b','c'},{'e','f','g'},{'i','j','k'}};
		rotateMatrix(mtx);
		
		int [] numbers = {2, 4, 3, 5, 1, 7, 6, 9, 8};
		int [] length = new int[9];
		int [] predecessor = new int[9];
		longestIncreasingSubseq(numbers, length, predecessor, 0);
		System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.toString(length));
		System.out.println(Arrays.toString(predecessor));
		List<Integer> list = longestIncreasingSubseq2(numbers);
		System.out.println("longestIncreasingSubseq2:");
		System.out.println(list.toString());
		
		int [] intList = {1, 3, 5, 6, 8, 9, 11, 12};		
		boolean [] boolList = subsetSum(intList, 10);
		System.out.println(Arrays.toString(intList));
		System.out.println(Arrays.toString(boolList));
		//get all solutions
		subsetSum2(intList, 10);		
		shuffleArray(intList);
		System.out.println("After shuffle:");
		System.out.println(Arrays.toString(intList));
		System.out.println("rand7():");
		for (int i = 0; i < 20; i++) {
			System.out.print(rand7());
		}
		System.out.println();
		longestCommonSubstring("ABAB","BABA");
		
		//http://www.mitbbs.com/article_t/JobHunting/31470103.html
		//http://www.mitbbs.com/article_t1/JobHunting/31460765_0_1.html
		System.out.println("minimum number of characters to be a palindrome: ADAXDA");
		System.out.println(palindrome("ADAXDA"));
	}
	public static void setMatrixZero (int [][] mtx){
		int [] row = new int[mtx.length];
		int [] col = new int[mtx[0].length];
		
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx[0].length; i++) {
				if (mtx[i][j] == 0) {
					row[i] = 1;
					col[j] = 1;
				}					
			}
		}
		
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx[0].length; i++) {
				if (row[i]==1 || col[j]==1) {
					mtx[i][j] = 0;
				}					
			}
		}
	};
	
	/**
	 * @param mtx
	 * Given an image represented by a matrix, where each pixel in the image is 4
	 * bytes, write a method to rotate the image by 90 degrees. Can you do this in
	 * place?
	 */
	public static void rotateMatrix(char [][] mtx){
		if (mtx == null)
			return;
		if (mtx.length != mtx[0].length)
			return;
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx.length; j++) {
				System.out.print(mtx[i][j]);
			}
			System.out.println();
		}
		
		int n = mtx.length;
		for (int layer = 0; layer < n/2; layer++){
			int first = layer;
			int last = n - layer - 1;
			for (int i = 0; i < last-first; i++) {
				char leftTop = mtx[last-i][first]; //preserve the top row from left column
				mtx[last-i][first] = mtx[last][last-i]; // left column
				mtx[last][last-i] = mtx[first+i][last]; //bottom row
				mtx[first+i][last] = mtx[first][first+i]; //right column				
				mtx[first][first+i] = leftTop;
			}
		}
		System.out.println();
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx.length; j++) {
				System.out.print(mtx[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void findElemInSortedMatrix(int [][] mtx, int elem) {
		if (mtx == null)
			return;
		int row = 0;
		int	col = mtx[0].length -1;
		
		while (row < mtx.length && col >= 0) {
			if (mtx[row][col] == elem) {
				System.out.println("at(" + row + "," + col + ")");
				return;
			} else if (mtx[row][col] > elem){
				col--;
			} else {
				row++;
			}
		}
		System.out.println("not found");
	}
	
	/**
	 * @param mtx
	 * Imagine you have a square matrix, where each cell is filled with either black or
	 * white. Design an algorithm to find the maximum subsquare such that all four 
	 * borders are filled with black pixels. O(N^3)
	 */
	public static int subBlackSquare(int [][] mtx) {
		if (mtx == null)
			return -1;
		int minLen = mtx.length < mtx[0].length ? mtx.length:  mtx[0].length;
		
		//suppose 1 is black and 0 is white in mtx
		
		//a matrix to store row sum
		int [][] rowSum = new int[mtx.length][mtx[0].length];
		//a matrix to store column sum
		int [][] colSum = new int[mtx.length][mtx[0].length];
		//cumulate row
		for (int i = 0; i < mtx.length; i++){
			int temp = 0;
			for (int j = 0; j < mtx[0].length; j++) {
				temp += mtx[i][j];
				rowSum[i][j] = temp;
			}
		}		
		//cumulate column
		for (int j = 0; j < mtx[0].length; j++){
			int temp = 0;
			for (int i = 0; i < mtx.length; i++) {
				temp += mtx[i][j];
				colSum[i][j] = temp;
			}
		}
		int maxK = 0;
		for (int i = 0; i < mtx.length; i++){			
			for (int j = 0; j < mtx[0].length; j++) {
				for (int k = 0; k < minLen; k++) {
					if ((rowSum[i][j+k] - rowSum[i][j] == k) &&
						(rowSum[i+k][j+k] - rowSum[i+k][j] == k)&&
						(colSum[i+k][j] - colSum[i][j] == k) &&
						(colSum[i+k][j+k] - colSum[i][j+k] == k)) {
						if(maxK < k)
							maxK = k;
					}
				}
			}
		}	
		return maxK+1;
	}
	
	/**
	 * @param arr
	 * @return max
	 * O(N)
	 */
	public static int findMaxInArray(int [] arr) {
		if (arr == null)
			return Integer.MIN_VALUE;
		int max = 0;
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++){
			sum += arr[i];
			if (max < sum)
				max = sum;
			if (sum < 0)
				sum = 0;
		}
		return max;
	}
	
	/**
	 * @param mtx
	 * @return max
	 * O(N^3)
	 */
	public static int findMaxInMatrix(int [][] mtx){
		if (mtx == null)
			return Integer.MIN_VALUE;
		int gMax = Integer.MIN_VALUE;
		for (int i = 0; i < mtx.length; i++){
			int [] sum = new int[mtx[0].length];
			for (int j = i; j < mtx.length; j++){				
				for (int k = 0; k < mtx[0].length; k++){
					sum[k] += mtx[j][k];
				}
				int tmpMax = findMaxInArray(sum);
				if (tmpMax > gMax) {
					gMax = tmpMax;
				}
			}
		}
		return gMax;
	}
	
	/**
	 * @param numbers
	 * @param length
	 * @param predecessor
	 * @param index
	 * Longest Increasing Subsequence. Given a sequence of n real numbers A(1) ... A(n), 
	 * determine a subsequence (not necessarily contiguous) 
	 * of maximum length in which the values in the subsequence 
	 * form a strictly increasing sequence. 
	 */
	public static void longestIncreasingSubseq(int [] numbers, int [] length, int [] predecessor, int index){		
		if (index < 0) {
			return;
		}		
		int maxSubLen = Integer.MIN_VALUE;
		int maxSubIndex = -1;
		for (int i = index-1; i>=0; i--){
			if (numbers[i] < numbers[index]){
				if (length[i] > maxSubLen) {
					maxSubIndex = i;
					maxSubLen = length[i];
				}				
			}
		}
		if (maxSubIndex > -1) {
			length[index] = maxSubLen + 1;
			predecessor[index] = maxSubIndex;
		} else {
			length[index] = 1;
			predecessor[index] = -1;
		}
		if (index < numbers.length-1) {
			longestIncreasingSubseq(numbers, length, predecessor, index+1);
		}
	}
	
	/**
	 * @param numbers
	 * @return list
	 * http://www.algorithmist.com/index.php/Longest_Increasing_Subsequence.cpp
	 */
	private static List<Integer> longestIncreasingSubseq2(int [] numbers){		
	
		List<Integer> result = new ArrayList<Integer>();
		int len = numbers.length;
		int [] parent = new int[numbers.length];
		result.add(0);
		for (int i = 1; i < len; i++) {
			int lastI = result.size()-1;
			if (numbers[result.get(lastI)] < numbers[i]){
				parent[i] = result.get(lastI);
				result.add(i);
				continue;
			}
			int u, v;
			for (u = 0, v = lastI; u < v;) {
				int c = (u + v) / 2;
				if (numbers[result.get(c)] < numbers[i]) {
					u = c+1;					
				} else {
					v = c;
				}
			}
			if (numbers[i] < numbers[result.get(u)]) {
				if (u > 0)
					parent[i] = result.get(u-1);
					result.remove(u);
					result.add(u, i);
			}
		}
		int u, v;
		for (u = result.size()-2; u >= 0; u--) {
			v=result.get(u+1);
			result.remove(u);
			result.add(u, parent[v]);
		}
		
		return result;
	}
	
	
	/**
	 * @param a
	 * @param b
	 * longest continuous common string
	 * 
	 * LCSubString(i, j) = LCSubString(i-1, j-1) + 1    if (i , j) are equal 
	 * LCSubString(i, j) = 0    if (i , j) are not equal
	 * 
	 * longest common subsequence
	 * LCLCSubSequence(i, j) = LCLCSubSequence(i-1, j-1) + 1    if (i , j) are equal
	 * LCLCSubSequence(i, j) = 
	 * max(LCLCSubSequence(i-1, j), LCLCSubSequence(i, j-1))    if (i , j) are not equal
	 * 
	 */
	public static void longestCommonSubstring(String a, String b) {
		if (a == null || b == null) {
			return;
		}
		
		int [][] mtx = new int [a.length()+1][b.length()+1];
		int maxSoFar = -1;
		int maxIndex = 0;
		
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i-1) == b.charAt(j-1)) {
					mtx[i][j] = mtx[i-1][j-1]+1;
					if (mtx[i][j] > maxSoFar) {
						maxSoFar = mtx[i][j];
						maxIndex = i;
					}
				} else {
					mtx[i][j] = 0;
				}
			}
		}
		System.out.println("longestCommonSubstring of '" + a + "' and '" + b + "' :");
		System.out.println(a.substring(maxIndex-maxSoFar, maxIndex));
	}
	
	/**
	 * @param intList
	 * @param sum
	 * @return array
	 * find the elements in a array which sum to a given number
	 */
	public static boolean [] subsetSum(int [] intList, int sum) {
		if (sum == 0 || intList == null)
			return null;
		
		//array should be sorted, otherwise sort here.
		
		int total = 0;
		for (int i = 0; i < intList.length; i++){
			total += intList[i];
		}
		if (total < sum)
			return null;
		
		boolean [] boolList = new boolean[intList.length];
		int med = sum/2;
		for (int i = 0; i < intList.length; i++){
			// no need to call the function if the first number is bigger than the median of sum
			// since it is not possible to find the solution.
			if (intList[i] < med){
				boolList[i] = true;
				boolean found = findSubSet(intList, boolList, i, sum);
				if(found)
					return boolList;
				boolList[i] = false;
			}
		}
		return null;
	}
	
	public static boolean findSubSet(int [] intList, boolean [] boolList, int index, int sum) {
		if (index == intList.length)
			return false;
		int res = sum - intList[index];
		
		//got it
		if (res == 0)
			return true;
		
		//because of sorted array, no need to search the rest when no smaller number available
		if (res < 0 || res < intList[index]) 
			return false;
		
		for (int i = index+1; i < intList.length; i++){
			boolList[i] = true;
			boolean found = findSubSet(intList, boolList, i, res);
			if(found)
				return true;
			boolList[i] = false;
		}
		return false;
	}
	
	public static void subsetSum2(int [] arr, int sum) {
		if (arr == null) {
			return;
		}
		//array should be sorted, otherwise sort here.
		//Arrays.sort(arr);
		//test if total < sum
		int total = 0;
		for (int i : arr)
			total+=i;
		if(total<sum)
			return;
		boolean [] boolArr = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			
			boolean b = findSum2(arr, boolArr, sum, i);
			if(b)
				System.out.print(Arrays.toString(boolArr));
			Arrays.fill(boolArr, false);
		}
	}

	public static boolean findSum2(int [] arr, boolean [] boolArr, int sum, int index) {
		boolArr[index] = true;
		int res = sum - arr[index];
		if(res == 0) {
			return true;
		}
		
		for( int i = index-1; i>=0; i--){		
			boolean r = findSum2(arr, boolArr, res, i);
			if (r) {
				return true;
			}
		}
		
		boolArr[index] = false;
		return false;
	}
	
	/**
	 * @param array
	 * randomly shuffle an array
	 */
	public static void shuffleArray(int [] array) {		
		//no need to shuffle the last index
		for (int i = 0; i < array.length - 1; i++){
			Random r = new Random();
			int rInd = r.nextInt(array.length-i) + i;
			int tmp = array[i];
			array[i] = array[rInd];
			array[rInd] = tmp;			
		}
	}
	
	/**
	 * @param array
	 * @param m
	 * Write a method to randomly generate a set of m integers from an array of size n. Each
	 * element must have equal probability of being chosen.
	 */
	public static int [] nChooseM(int [] array, int m){
		if (array == null || array.length < m){
			return null;
		}
		int [] rArray = new int [m];
		for (int i = 0; i < m; i++){
			Random r = new Random();
			int rInd = r.nextInt(m-i) + i;
			rArray[i] = array[rInd];
			array[rInd] = array[i]; 
			array[i] = rArray[i]; 
		}
		return rArray;
	}
	
	/**
	 * @return random 1 - 5
	 */
	public static int rand5() {
		Random r = new Random();
		return r.nextInt(5) + 1;
	}
	
	/**
	 * @return 0 or 1
	 */
	public static int rand2() {
		while(true){
			int r = rand5();
			if (r == 5)
				continue;
			else {
				return r % 2;
			}
		}
	}
	
	/**
	 * @return 1 - 7
	 */
	public static int rand7() {
		int r = rand5() + rand5() + rand5() + rand5() + rand5();
		return ((r-5) % 7) + 1;
	}
	
	
	/**
	 * @param str
	 * @return min steps
	 * Find minimum number of characters that need to be inserted 
	 * into a string (anywhere in the string) to make it a palindrome.
	 * (Hint: Interviewer expected a Dynamic Programming kind of solution)
	 */
	public static int palindrome(String str){
		if (str == null) {
			return -1;
		}
		char [] strA = str.toCharArray();
		int len = str.length();
		int [][] mtx = new int [len+1][len+1];
			    
	    for(int i = 0; i <= len; i++)
	    {
	        mtx[0][i] = 0;
	    }

	    for(int i = 0; i <= len; i++)
	    {
	        mtx[i][0] = 0;
	    }
		
		for (int k = 0; k <len; k++) {			
			for (int i = 1, j = i+k; i<=len && j<=len; i++, j++) {
				if (k==0)
					mtx[i][j] = 0;
				else if (strA[i-1] == strA[j-1])
					mtx[i][j] = mtx[i+1][j-1];
				else
					mtx[i][j] = Math.min(mtx[i][j-1]+1, mtx[i+1][j]+1);
				    //                   insert j to i  insert i to j
			}
		}
		return mtx[1][len];
	}
}














