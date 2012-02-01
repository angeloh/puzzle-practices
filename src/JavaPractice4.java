import java.io.*;
import java.util.*;

public class JavaPractice4 {	
	
	public static void main (String [] args){
		String maxSub = maxSubPattern("abcabcabc");
		System.out.println("Maximum sub pattern of string 'abcabcabc' is :");
		System.out.println(maxSub);
		
		boolean isRotat = isRotationStr("abcd", "dabc");
		System.out.println("Is 'dabc' a rotation of 'abcd'?");
		System.out.println(Boolean.toString(isRotat));
		
	}
	
	//Write an algorithm such that if an element in an MxN matrix is 0, 
	//its entire row and column is set to 0.
	public static void zeroMatrix(int [][] matrix) {
		int [] row = new int [matrix.length];
		int [] col = new int [matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			row[i] = 0;
		}
		for (int j = 0; j < matrix[0].length; j++) {
			col[j] = 0;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = 1;
					col[j] = 1;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (row[i] == 1 || col[j] == 1) {
					matrix[i][j] = 0;
				}
			}
		}
		
	}
	
	//Given a matrix in which each row and each column is sorted, 
	//write a method to find an element in it.
	//The matrix is supposed sorted in ascending order for a row and a column.
	public static String findInMatrix(int [][] matrix, int target) {
		if (matrix == null || matrix.length != matrix[0].length) 
			return null;
		int i = 0;
		int j = matrix[0].length-1;
		while (i < matrix.length && j > -1) {
			if (matrix[i][j] == target) {
				return "("+i+","+j+")";					
			} else if (matrix[i][j] < target) {
				i++;
			} else {
				j--;
			}			
		}
		return null;
	}
	
	/**
	 * Given an arbitrarily long string, design an algorithm to find the maximum repetitive substring.
	 * For example, the maximum repetitive substring of "abcabcabc" is "abcabc".
	 * Check DataStructure.doc for details.
	 * @param input
	 */
	public static String maxSubPattern(String input) {		
		if (input == null || input.equals(""))
			return null;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++) {
			list.add(input.substring(i));
		}
		Collections.sort(list);
		String maxStr = "";
		for (int i = 0; i < list.size() - 1; i++){
			int j = 0;
			int minL = Math.min(list.get(i).length(), list.get(i+1).length());
			if (minL <= maxStr.length()) //make sure the length of patter we are looking for will be greater than previous found.
				continue;
			while(list.get(i).charAt(j) == list.get(i+1).charAt(j)) {				
				j++;
				if (j == minL) //stop when reaching the end of shorter string.
					break;
			}
			if (j > maxStr.length()) {
				maxStr = list.get(i).substring(0, j);
			}
		}
		return maxStr;		
		
	}
	
	/**
	 * How would you design a stack which, in addition to push and pop, also has a function min which
	 * returns the minimum element? Push, pop and min should all operate in O(1) time.
	 * @author Angelo
	 *
	 */
	class StackWithMin {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		public void push(Integer value) {
			if (value <= min()) {
				s2.push(value);
			}
			s1.push(value);
		}
		public Integer pop() {
			Integer pop = s1.pop();
			if (pop != null && pop.equals(s2.peek())){
				s2.pop();
			}
			return pop;
		}
		public Integer min() {
			Integer min = Integer.MAX_VALUE;
			if (s2.peek() != null) {
				min = s2.peek();
			}
			return min;
		}
	}
	
	/**
	 * Write a C program to sort a stack in ascending order. You should
     * not make any assumptions about how the stack is implemented.
     * The following are the only functions that should be used to write
     * this program:
     * Push | Pop | Top | IsEmpty | IsFull
	 * @param stack
	 */
	public static void sortStack(Stack<Integer> stack) {
		if (stack == null) {
			return;
		}
		Stack<Integer> s1 = new Stack<Integer>(); //in descending order
		Stack<Integer> s2 = new Stack<Integer>();
		
		while(stack.peek() != null) {
			Integer top = stack.pop();
		
			if (s1.peek() == null || top.compareTo(s1.peek()) >= 0) {
				s1.push(top);
			} else {
				while(s1.peek()!=null && top.compareTo(s1.peek()) < 0) {
					s2.push(s1.pop());
				}
				s1.push(top);
				while(s2.peek()!=null) {
					s1.push(s2.pop());
				}
			}	
		}
		while(s1.peek()!=null) {
			stack.push(s1.pop());
		}
	}
	
	/**
	 * Implement an algorithm to determine if a string has all unique characters. What if you can not use
	 * additional data structures?
	 * @param str
	 * @return true or false
	 */
	public static boolean checkUnique(String str) {
		if (str.length() > 256)
			return false;
		char[] chars = str.toCharArray();
		boolean [] bools = new boolean [256];
		for(boolean b : bools)
			b = false;
		for(char c: chars) {
			int value = (int)c;
			if(bools[value] == true)
				return false;
			bools[value] = true;
		}
		return true;
	}
	
	/**
	 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to strstr
	 * (eg, “waterbottle” is a rotation of “erbottlewat”).
	 */
	
	public static boolean isRotationStr(String s1, String s2) {
		if (s1.length()!= s2.length())
			return false;
		String s3 = s1 + s1;
		if (s3.contains(s2))
			return true;
		return false;
	}
	
	/**
	 * check a tree is a balanced tree 
	 * difference between max depth of any leaf node and 
	 * min depth of any leaf node should be less than or equal than 1
	 * @author Angelo
	 *
	 */
	public static int maxDepth(Node root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 0;
		return (1 + Math.max(maxDepth(root.left),maxDepth(root.right)));
	}
	public static int minDepth(Node root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 0;
		return (1 + Math.min(maxDepth(root.left),maxDepth(root.right)));
	}
	public static boolean isBalance(Node root) {
		int minD = minDepth(root);
		int maxD = maxDepth(root);
		if ((maxD - minD) == 1)
			return true;
		return false;
	}
}