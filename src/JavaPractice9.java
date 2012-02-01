import java.util.*;
import java.text.*;

public class JavaPractice9 {
	public static void main(String [] args) {
		String replaced = replaceSpace("a b c d e", "%20");
		System.out.println("replaced:" + replaced);
		
		printSolFor8Queen();
		System.out.println();
		char [] in = {'f', 'a', 'c', 'e'};
		char [] out = new char[4];
		outputAllCombinations(in, out, 0);	
		
		
		System.out.println("findTrailingZeroInFactorial of 130:");
		System.out.println(findTrailingZeroInFactorial(130));
		
		System.out.println("findFirstNonZeroInFactorial of 100:");
		System.out.println(findFirstNonZeroInFactorial(30));
		
		long face = Long.parseLong("FACEB00C", 16);
		face>>=2;
		System.out.println(face);		
		
//		int [] arr = {2, 3, 4, 5, 6, 7, 9};
//		System.out.println(binarySearch(arr, 8));
//		int [] arr2 = {2, 3, 5, 6, 7, 9};
//		System.out.println(binarySearch(arr2, 8));
//		int [] arr3 = {2, 3, 5, 6, 7, 9};
//		System.out.println(binarySearch(arr3, 4));
//		int [] arr4 = {2, 4, 6, 7, 9};
//		System.out.println(binarySearch(arr4, 3));
//		int [] arr5 = {2, 3, 6, 8, 9};
//		System.out.println(binarySearch(arr5, 7));
	}
	
	/**
	 * @param str
	 * @param rep
	 * @return String
	 * Write a method to replace all spaces in a string with ‘%20’.
	 */
	public static String replaceSpace(String str, String rep){
		if (str == null || rep == null)
			return null;
		char [] chars = str.toCharArray();
		char [] reps = rep.toCharArray();
		int spaces = 0;
		for (char c: chars) {
			if (c == ' '){
				spaces++;
			}
		}
		int repLen = reps.length;
		int newLen = chars.length+spaces*(rep.length()-1);
		char [] newChars = new char[newLen];
		int strIdx = chars.length - 1;
		int newStrIdx = newLen;
		while (strIdx >= 0) {
			if (chars[strIdx] == ' '){
				for (int i = 1; i<=repLen; i++){
					newChars[newStrIdx-i] = reps[repLen-i]; 
				}
				newStrIdx = newStrIdx - repLen;
			} else {
				newChars[newStrIdx-1] = chars[strIdx];				
			}
			strIdx--;
		}
		
		return new String(newChars);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return boolean
	 * Write a method to decide if two strings are anagrams or not.
	 */
	public static boolean testAnagram(String a, String b){
		if (a.length() != b.length())
			return false;
		
		int [] count = new int[256];
		char [] aChars = a.toCharArray();		
		for (char c: aChars) {						
			count[c]++;
		}
		char [] bChars = b.toCharArray();
		for (char cc: bChars){
			if (count[cc] == 0) {
				return false;
			}
			count[cc]--;			
		}
		return true;
	}
	
	/**
	 * Find one solution for 8-queen
	 */
	public static void printSolFor8Queen(){
		int [] colForRow = new int[8]; 
		find8Queen(0, colForRow); //start from row 0
	}
	
	public static boolean checkSol(int row, int [] colForRow) {
		if (row == 0)
			return true;
		for (int i = 0; i < row; i++) {
			if (colForRow[i] == colForRow[row]){
				return false;
			}
			int colDiff = Math.abs(colForRow[row]-colForRow[i]);
			int rowDiff = Math.abs(row-i);
			if (colDiff == rowDiff){
				return false;
			}
		}
		return true;
	}
	
	public static boolean find8Queen(int row, int [] colForRow) {
		if (row == 8) {
			for (int i = 0; i < colForRow.length; i++) {
				System.out.print(colForRow[i] + " ");
			}
			return true;
		}
		
		for (int i = 0; i < 8; i++) {
			colForRow[row] = i;
			if (checkSol(row, colForRow)){
				boolean b = find8Queen(row+1, colForRow);
				if (b){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * You are given a string e.g."face" and a set of mutation rules, 
	 * e.g. a->@, e->3, e-E. Print all the possible strings that can be generated b
     * y the rules, e.g. f@c3, fac3, etc
	 */
	public static void outputAllCombinations(char [] in, char [] out, int curLevel){
		if (in == null || in.length != out.length)
			return; //wrong input params
		
		if (curLevel == out.length) {
			System.out.println(Arrays.toString(out));
			return;
		}
		
		char [] muts = getMutRule(in[curLevel]);
		for (int i = 0; i < muts.length; i++){
			out[curLevel] = muts[i];
			outputAllCombinations(in, out, curLevel+1);
		}
		
	}
	
	public static char [] getMutRule(char c){
		switch (c){
			case 'f':
				return new char [] {'f','1','t'};				
			case 'a':
				return new char [] {'a'};
			case 'c':
				return new char [] {'c', '@'};				
			case 'e':
				return new char [] {'e'};
			default:
				return new char [] {c};
		}
			
	}
	
	/**
	 * @param n
	 * @return total zero
	 * find n/5.Now,see if Quotient is greater 
	 * than 5,if it is..divide it again by 5 n continue 
	 * till you get a quotient less than 5. 
	 * It's better than dividing by 25,125(5*5*5) n so on..

		Example for 26! as taken above:
		no of 5s= 26/5=5(q)+1(r)
		discard r=1 n divide 5 by 5 again..you'll get 1..
		so answer is 5+1=6
		
		You can use it to find no. of any digit in a factorial
		say you want to find number of 2s in 43!
		
		no. of 2s= 43/2=21(q)
		since 20> 2 therefore divide again by 2..
		so u'll have the series like
		21+10+5+2+1
		39 twos 
	 */
	public static int findTrailingZeroInFactorial(int n) {
		int total = 0;
		while((n/=5)>0) {			
			total += n;
		}		
		return total;
	}
	
	public static int findFirstNonZeroInFactorial(int n){
		if (n < 2) {
			return 1;
		}
		int trailZero = findTrailingZeroInFactorial(n);
		int prod = 1;
		for(int i = 2; i <= n; i++){
			prod*=i;
			while((prod%5)==0){ 
				prod/=5;
			}
			while((prod%2)==0 && trailZero>0){
				prod/=2;
				trailZero--;
			}
			//leave the last digit
			if((prod/10)>0){
				prod %= 10;
			}			
		}
		return prod;
	}
	
	
//	Given 2 lists find the intersection point of the 2 lists?
//	The characteristics of these lists are they at some point will share the same tail.
//			B->A->C->D->E
//			T->F->I->C->D->E//
//			in this example (C->D->E) are the same list with same address space and nodes.			
//			The lists can be of any length and not sorted.
//			1) give any method to solve this
//			2) if performance is a problem can we do this in linear time?
//			3) if space and performance is a problem, give a solution that will solve this in linear time without the auxiliary memory overhead.
//			4) code it
	
	public static DNode findInterset(DNode a, DNode b) {
		if (a == null || b == null) {
			return null;
		}
		
		int aLen = 0;
		int bLen = 0;
		DNode start = null;
		DNode aLast = null;
		DNode bLast = null;
		DNode tmp = a;
		while(tmp != null) {
			aLen++;
			if (tmp.next == null)
				aLast = tmp;
			tmp = tmp.next;		
		}
		tmp = b;
		while(tmp != null) {
			bLen++;
			if (tmp.next == null)
				bLast = tmp;
			tmp = tmp.next;		
		}
		if (aLast.equals(bLast))
			return null;
			
		DNode l = null;
		DNode s = null;
		if (aLen < bLen) {
			l = b;
			int diff = bLen - aLen;
			while(diff>0){
				l = l.next;
				diff--;
			}
			s = a;
		} else {
			l = a;
			int diff = aLen - bLen;
			while(diff>0){
				l = l.next;
				diff--;
			}
			s = b;
		}
		
		while(l!=null && s!=null) {
			if (l.equals(s) && start == null)
				start = l;
			if (!l.equals(s))
				start = null;
		}
		return start;
	}
	
	
//	private static int binarySearch(int[] arr, int start) {
//		int left = 0, right = 0, mid = 0;
//		right = arr.length-1;
//		if (arr[right]<start){
//			return right;
//		}
//		if (arr[left]>=start){
//			return -1;
//		}
//		while(left<=right) {
//			mid = (left + right)/2;
//			if (arr[mid] > start) {
//				right = mid - 1;				
//			} else if (arr[mid] < start) {
//				left = mid + 1;			
//			} else {
//				return mid-1; 
//			}
//		}
//		return right;
//	}
}



/**
 * @author ahuang
 *Coding hasNext() and next() methods for a class
 *that contains a Collection of Collections. 
 *(main collection must not be null or empty, 
 *sub collections must not be null.
 *The only method in Collection that you can use is iterator().
 */
class COfC {
	Collection cofc;
	Iterator topIter = null;
	Iterator subIter = null;
	public boolean hasNext() {
		boolean hasN = false;		
		if (subIter != null) {
			 hasN = subIter.hasNext();
		}
		if (!hasN) { //move to next top collection
			if (topIter == null) {
				topIter = cofc.iterator();
			}
			while(topIter.hasNext()) {
				Collection c = (Collection)topIter.next();
				subIter = c.iterator();
				hasN = subIter.hasNext();
				if (hasN)
					break;
			}
		}
		return hasN;
	}
	
	public Object next() throws NoSuchElementException{
		if(hasNext())
			return subIter.next();

		throw new NoSuchElementException();
	}
}


/**
 * @author ahuang
 * Implement a sparse matrix Java class with a constructor, 
 * set and get method. The matrix has millions of rows and columns 
 * and is at a maximum 15% populated.
 * http://www.docjar.com/html/api/org/gui4j/core/util/SparseMatrix.java.html
 */
class SparseMatrix{
	private final Map matrixMap = new HashMap();
	
	public Object get(Object row, Object col) {
		return matrixMap.get(new Pair2(row, col));
	}
	
	public void set(Object row, Object col, Object value){
		if (row == null || col == null) {
			throw new IllegalArgumentException("row or column may not be null");
		}
		matrixMap.put(new Pair2(row, col), value);
	}

}

class Pair2 {
	private static final int HASH_PRIME = 1000003;
	Object row;
	Object col;
	public Pair2(Object row, Object col){
		this.row = row;
		this.col = col;
	}
	public boolean equals(Object o) {
		if (o instanceof Pair2)
			return false;
		Pair2 p = (Pair2)o;
		if (this == p) {
			return true;
		}
		if (this.row.equals(p.row)&&this.col.equals(p.col)) {
			return true;
		}
		return false;
	}
	public int hashCode() {
		int result = 0;
		result = HASH_PRIME * result + this.row.hashCode();
		result = HASH_PRIME * result + this.col.hashCode();
		return result;
	}
	              
}




