import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class JavaPractice1 {
	
	public static void main (String [] args){		
		// 1. reverse linked list
		List<Integer> list = new LinkedList<Integer>();
		Collections.reverse(list);		
		// 2. remove duplicate from an unsorted integer array
		int [] intArray = {2,3,3,5,6,2};
		removeDuplicate(intArray);
		// 3. print a first duplicate number in an length N's interger array with number range from 1 to N		
		printFirstDuplicate(intArray);
		// 4. write a function to evaluate polynomial
		
		// 8. return a list of all integers that are not found in all 3 sets.
		
		int [] s1= { 1, 7, 13, 27 };
		int [] s2= { 1, 7, 13, 21, 31 };
		int [] s3= { 7, 13, 21 };
		List<Integer> result = findNonMerge(s1, s2, s3);
		System.out.println("findNonMerge Solution:");
		System.out.println(result);
		
		result = findMerge(s1, s2, s3, 2);
		System.out.println("findMerge Solution:");
		System.out.println(result);
		
//		double d = 1323232123456.78;
		int i = 1234567;
//		DecimalFormat df = new DecimalFormat("###,##0.00");
//		System.out.println(df.format(d));
		System.out.println(formatCommas(i));
		System.out.println(formatCommasRecur(i));
		
		char [] arrA = {'A', 'B', 'C'};
		char [] arrB = {'D', 'E', 'F'};
		char [] out = new char [6];
		for (int k = 0; k < out.length; k++){
			out[k] = '\u0000';
		}
		permuteTwoSets(arrA, arrB, out, 0, 0, out.length-arrA.length);
		System.out.println("Another Solution:");
		outputAll(arrA, arrB, out, 0, 0, 0);
		//System.out.println(divide(20, 5));
		char [] chars = {'A', 'B', 'C', 'D'};
		out = new char [4];
		boolean [] used = new boolean[4];
		for (int k = 0; k < out.length; k++){
			out[k] = '\u0000';
			used[k] = false;
		}
		
		permuteString(chars, out, used, 0);
		
		//16.
		int [][] matrix =
		       {{1,2,-1},
				{-3,-1,4},
				{1,-5,-2}};
		System.out.println("maxSubMatrix is " + maxSubMatrix(matrix));
		
		//17.
		int np = findFirstNonRepetitive(intArray);
		System.out.println("findFirstNonRepetitive of " + Arrays.toString(intArray) + " is: " + intArray[np]);
		
		//18.
		int [] inputArray = {2,3,3,5,6,2};
		int [] indexArray = {1,4,5,3,2,0};
		int [] output = productExcept(inputArray, indexArray);
		System.out.println("productExcept from:" + Arrays.toString(inputArray) + " is: " + Arrays.toString(output));
		
		// divide 2 use >> 1
		int beforeDivide = 10;
		int afterDivide = beforeDivide>>1;
		System.out.println("Divide " + beforeDivide + " by >> 1 = " + afterDivide);
	    
		
		
	}




//	1. reverse linked list
//	class LinkedListTool{
//		
//		static Node head;
//		class Node {
//			Node mext;
//			int value;
//		}
//
//		//reverse a linked list with recursive
//		Node reverseLL(Node node) {
//		   
//		   if (node.next != null) {
//		       Node next = reverseLL(node.next);
//		       node.next = null;
//		       next.next = node;			   
//		   } else {
//			   head = node;
//           }
//		   return node;
//		}
//		
//		public static void main(String [] args){
//			//construct a linked list first and make head point to the head of list.
//			LinkedListTool llt = new LinkedListTool();
//			Node start = head;
//			llt.reverseLL(start);
//			//after the method, head is pointing to original list's tail.
//		}
//	}


//	2. remove duplicate from an unsorted integer array
	public static void removeDuplicate(int [] arr) {
		Set<Integer> set = new LinkedHashSet<Integer>();
		for (int i : arr){
			set.add(i);
		}

		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			System.out.format("%d", it.next());
			if (it.hasNext()) System.out.format(",");
			else System.out.format("%n");
		}
	}
	
// 3. print a first duplicate number in an length N's interger array with number range from 1 to N
	public static void printFirstDuplicate(int [] arr) {
		
		for (int i = 1; i <= arr.length; i++) {
			int j = arr[i-1];
			if(j > 0) {
				int k = arr[j-1];
				if (k > 0) {
					arr[j-1] = 0;
					if(j > i) { // to avoid i == j, otherwise, it will run forever
						arr[i-1] = k;
						i--;
					}
				} else {
					System.out.printf("first duplicate is: %d\n", j);
					break;
				}
			}
		}
	}
	
	class Poly {
		int ct;
		int px;
		int py;
		Poly next;
		public Poly(int c, int x, int y) {
			ct = c;
			px = x;
			py = y;
		}
	}
	
	// 4. write a function to evaluate polynomial ex. 5x^4+6x^3-7x^2-8
	public static int evalPoly(Poly p){
		int sum = 0;
		while(p != null) {
			sum += (p.ct*Math.pow(p.px, p.py));
		}
		return sum;
	}
	
	// 5. given a URL, replace space to %20
	public static String replaceURLSpace(String url) {
		return url.replaceAll(" ", "%20");		
	}
	
	// 6. You are given a Double Link List with one pointer of each node pointing to 
	// the next node just like in a single link list. 
	// The second pointer however CAN point to any node in the list and not just the previous node. 
	// How this can be implemented in in O(n) time to duplicate this list. 
	// That is,a program which will create a copy of this list in O(N) time.

	public static DNode copyDoubleLinkList1(DNode dn) {
		DNode oldNode = dn;
		DNode newList;
		while(oldNode != null) {
			DNode newNode = new DNode(oldNode.value);
			newNode.next = oldNode.other;
			oldNode.other = newNode;
			oldNode = oldNode.next;
		}
		
		oldNode = dn;
		while(oldNode != null) {
			DNode newNode = oldNode.other;
			newNode.other = newNode.next;
			oldNode = oldNode.next;
		}
		
		oldNode = dn;
		newList = oldNode.other;
		while(oldNode != null) {
			DNode newNode = oldNode.other;
			oldNode.other = newNode.next;
			if (oldNode.next != null)
				newNode.next = oldNode.next.other;
			 
			oldNode = oldNode.next;
		}
		return newList;
	}
	public static DNode copyDoubleLinkList2(DNode dn) {
		Map<DNode,DNode> map = new HashMap<DNode,DNode>();
		DNode oldNode = dn;
		DNode newList;
		while(oldNode != null) {
			DNode newNode = new DNode(oldNode.value);
			map.put(oldNode, newNode);
			oldNode = oldNode.next;
		}
		oldNode = dn;
		newList = map.get(oldNode);
		while(oldNode != null) {
			DNode newNode = map.get(oldNode);
			newNode.next = map.get(oldNode.next);
			newNode.other = map.get(oldNode.other);
			oldNode = oldNode.next;
		}
		return newList;
	}
	
// 7. There is a binary tree as below
//	1
//	2 3
//	4 5  6 7
//	8 9
//	10
//
//	given 2 elements say 5, 10 you need to find the common ancestor i., 2 inthis case. how will you go about it. ?
	
	class Path{
		int [] path = new int[100];
		int curIndex = 0;
		public Path() {
			for (int i = 0 ; i < path.length; i++) path[i] = 0;
		}
		void addToPath(int i) {
			path[curIndex++] = i; 
		}
		void removeFromPath(){			
			path[curIndex] = 0; 
			curIndex--;
		}
	}
		
	//recursive
	public static boolean searchNodePath(Node start, int target, JavaPractice1.Path path) {
		if (start!= null) {
			if (start.value == target)
				return true;			
		} else{
			return false;
		}
		path.addToPath(start.value);	
	    if (searchNodePath(start.left, target, path)){
	    	return true;
	    } else if (searchNodePath(start.right, target, path)){
	    	return true;
	    } else {
	    	path.removeFromPath();
	    	return false;
	    }
	}
	
	public static int findLowestCommonAncestor(Node root, int node1, int node2) {
		if (root.value == node1) return -1; //no common parent
		if (root.value == node2) return -1; //no common parent
		if (node1 == node2) return -1; //same node
		JavaPractice1.Path path1 = new JavaPractice1().new Path();
		JavaPractice1.Path path2 = new JavaPractice1().new Path();
		if (searchNodePath(root, node1, path1) && searchNodePath(root, node2, path2)) {
			int [] pathArr1 = path1.path;
			int [] pathArr2 = path2.path;
			for (int i = 0, j = 0; i < path1.curIndex && j < path2.curIndex; i++, j++) {
				if (pathArr1[i] != pathArr2[j])
					return pathArr1[i-1];				
			}			
		}
		return -1; //no common parnet 
	}
	
//	8. Next question: Given 3 sets containing positive integers as such:
//	S1= { 1, 7, 13, 27 }
//	S2= { 1, 7, 13, 21, 31 }
//	S3= { 7, 13, 21 }
//	Write pseudo-code that will return a list of all integers that are not found in all 3 sets. Running time?
	
	public static List<Integer> findNonMerge(int [] a, int [] b, int [] c) {
		//just look for the merge in shortest two set and see if third set contains them
		Set <Integer> result = new TreeSet<Integer>();
		int minSize = Math.min(a.length, b.length);
		
		int [] merge = new int [minSize];
		int i = 0, j = 0, p = 0;
		while( i < a.length && j < b.length) {
			if (a[i] == b[j]) {
				merge[p++] = a[i];
				i++;
				j++;
			} else if (a[i] < b[j]){
				result.add(a[i]);
				i++;				
			} else {
				result.add(b[j]);
				j++;
			}
		}
		while(i < a.length) {
			result.add(a[i]);
			i++;
		}
		while(j < b.length) {
			result.add(b[j]);
			j++;
		}
		
		i = 0;
		j = 0;
		while( i < p && j < c.length) {
			if (merge[i] < c[j]){
				result.add(merge[i]);
				i++;				
			} else if (merge[i] > c[j]){
				result.add(c[j]);
				j++;
			} else {
				i++;
				j++;
			}
		}
		while(i < p) {
			result.add(merge[i]);
			i++;
		}
		while(j < c.length) {
			result.add(c[j]);
			j++;
		}
		
		return new ArrayList<Integer>(result);
	}
	
	
	public static List<Integer> findMerge(int [] a, int [] b, int [] c, int topK) {
		//just look for the merge in three set and move the index for the smallest number
		if (topK < 1) return Collections.EMPTY_LIST;
		Set <Integer> result = new TreeSet<Integer>();	
		
		int i = 0, j = 0, p = 0;
		while( i < a.length && j < b.length && p < c.length) {
			if ((a[i] == b[j]) && (b[j] == c[p])) {				
				result.add(a[i]);
				i++;
				j++;
				p++;
			} else if ((a[i] <= b[j])&& (a[i] <= c[p])){				
				i++;				
			} else if ((b[j] <= a[i])&& (b[j] <= c[p])){				
				j++;
			} else {
				p++;
			}
			if (result.size() == topK){
				break;
			}
		}		
		
		return new ArrayList<Integer>(result);
	}
	
	// 9. Design Classes For Directed Acyclic Graph 
	// **Be careful of the cyclic path when add new edge.
	static class Vertex {
		int vertex;
		List<Integer> adjlist;
		public Vertex (int v) {
			vertex = v;
			adjlist = new LinkedList<Integer>();			
		}		
	}
	
    static class Graph {
    	
    	private final int NUM_OF_VERTEX;
    	private final Vertex [] adjlists;
    	enum VisitStatus {WHITE, GREY, BLACK};
        
    	public Graph(int numOfVertex) {
    		NUM_OF_VERTEX = numOfVertex;    		
    		adjlists = new Vertex[NUM_OF_VERTEX];
    		for (int i = 0; i < numOfVertex; i++) {    			
    			adjlists[i] = new Vertex(i+1);
    		}
    	}
    	public boolean addEdge (int v, int w) {
    		Vertex vt = adjlists[v-1];
    		List<Integer> adjs = vt.adjlist;
    		if (!adjs.contains(w)) {
    			adjs.add(w);
    		
    		}
    		if (detectCycle()) {
    			adjs.remove(adjs.size()-1);
    			return false;
    		}
    		return true;
    	}
    	
    	public boolean detectCycle() {
    		VisitStatus [] visited = new VisitStatus [NUM_OF_VERTEX];
    		for (int i = 0; i < NUM_OF_VERTEX; i++) {
    			visited[i] = VisitStatus.WHITE;    			
    		}
    		
    		return isCycleInDFS(visited, 1); // start from root   		
    	}
    	
    	
    	public boolean isCycleInDFS(VisitStatus [] visited, int v) {
    		boolean isCycle = false;
    		visited[v-1] = VisitStatus.GREY;
    		List<Integer> adjs = adjlists[v-1].adjlist;
    		Iterator<Integer> it = adjs.iterator();
    		while(it.hasNext()){
    			int adjv = it.next();
    			if (visited[adjv-1].equals(VisitStatus.WHITE)){
    				isCycle = isCycleInDFS(visited, adjv);
    			} else if (visited[adjv-1].equals(VisitStatus.GREY)) {
    				//Cycle Found
    				isCycle = true;    				
    			}
    			if (isCycle) break;
    		}
    		visited[v-1] = VisitStatus.BLACK;
    		return isCycle;
    	}
    }
	
//  10. write a function which will take in an int and return a string 
//    with the number delimited by commas. (ie; 123456789 should be converted to 123,456,789)
    //Iterative
    public static String formatCommas(int n){
    	StringBuffer sb = new StringBuffer();
    	while(n > 0) {
    		int re = n % 1000;
    		int quo = n / 1000;
    		if (quo > 0) {
    			sb = new StringBuffer("," + Integer.toString(re)).append(sb);
    		} else {
    			sb = new StringBuffer(Integer.toString(re)).append(sb);
    		}
    		
    		n /= 1000;
    	}
    	return sb.toString();    	
    }
    
    public static String formatCommasRecur(int n) {
    	if (n > 0) {
    		int re = n % 1000;
    		int quo = n / 1000;
    		if (quo > 0) {
    			return formatCommasRecur(quo) + "," + re;
    		} else {
    			return Integer.toString(re);
    		}
    	}
    	return "";
    }
    
    //below two functions allow negative integer
    // to format rem:
    //NumberFormat myFormat = new DecimalFormat("000");    
    public static String insertCommas(int n) {
    	boolean isNeg = false;
    	if (n < 0) {
    		isNeg = true;
    		n*=-1;
    	}
    	if (n<1000)
    		return isNeg?Integer.toString((n*-1)):Integer.toString(n);
    	int quo = 0;
    	int rem = 0;
    	StringBuffer sb = new StringBuffer("");
    	

    	while(n > 0) {
    		rem = n % 1000;		
    		quo = n / 1000;		
    		String remStr;
    		if (rem == 0)
    			remStr = "000";
    		else 
    			remStr = Integer.toString(rem);
    		if (quo>0)
    			sb = new StringBuffer(",").append(remStr).append(sb);
    		else
    			sb = new StringBuffer(remStr).append(sb);
    		n /= 1000;
    	}
    	if (isNeg)
    		return new StringBuffer("-").append(sb).toString();
    	else
    		return sb.toString();
    }

    public static StringBuffer insertCommasRur(int n) {
    	boolean isNeg = false;
    	if (n < 0) {
    		isNeg = true;
    		n*=-1;		
    	}
    	if (n<1000)
    		return isNeg?new StringBuffer((n*-1)):new StringBuffer(n);
    	
    	int rem = n % 1000;
    	String remStr;
    	if (rem == 0)
    		remStr = "000";
    	else 
    		remStr = Integer.toString(rem);
    	if (isNeg)
    		return insertCommasRur(-1*n/1000).append(",").append(remStr);
    	else
    		return insertCommasRur(n/1000).append(",").append(remStr);

    	
    }

//	11. Given a set of line segments in a single axis plane. 
//    Write a function that finds if these line segments intersect.
    
    class Line{
    	private int left = 0;
    	private int right = 0;
    	public Line(int l, int r) {
    		left = l;
    		right = r;
    	}
    	public int getLeft(){
    		return left;
    	}
    	public int getRight() {
    		return right;
    	}
    }
    
    public static boolean isInterset (Line [] lines) {
    	for (int i = 0 ; i < lines.length - 1; i++) {
    		for (int j = i + 1; j < lines.length; j++) {
    			if ( !((lines[i].getLeft() > lines[j].getRight()) 
    					|| (lines[i].getRight() < lines[j].getLeft()))) {
    				return true;
    			} 
    		}
    	}
    	return false;
    }
    
    //12. coding: two set "ABC" "XYZ", generate all possible words includes all 
    //character from both sets but that still keep sequence from its original 
    //sets like "ABCXYZ" "XAYBCZ"
    public static void permuteTwoSets(char [] arrA, char [] arrB, char [] out, 
    		int charLevel, int start, int end) {
    	if (charLevel == arrA.length) {
    		int p = 0; 
    		for (int k = 0; k < out.length; k++){
    			if (out[k] == '\u0000'){
    				out[k] = arrB[p];
    				p++;
    			}
    		}
    		System.out.println(out);
    		p = 0;
    		for (int k = 0; k < out.length; k++){
    			if (out[k] == arrB[p]){
    				out[k] = '\u0000';
    				p++;
    				if (p == arrB.length) break;
    			}
    		} 
    	} else {
    		for (int i = start; i <= end; i++) {
    			out[i] = arrA[charLevel];
    			permuteTwoSets(arrA, arrB, out, charLevel+1, i+1, end+1);
    			out[i] = '\u0000';
    		}
    	}
    }
    
    public static void permuteString(char [] chars, char [] out, boolean [] used, int curLevel) {
    	if (curLevel == chars.length)
    		System.out.println(out);
    	for (int i = 0; i < chars.length; i++) {
    		if (used[i] == false) {
    			out[curLevel] = chars[i];
    			used[i] = true;
    			permuteString(chars, out, used, curLevel+1);
    			used[i] = false;
    		}
    	}
    }
    
    /*
    void output_all(char *stra, char *strb, char *output, int outlen, int alen, int blen)
    {
        int i, j;
        if (alen == 0) {
            for (i=0; i
                output[outlen++] = *(strb+i);
            output[outlen] = '\0';
            printf("%s\n", output);
            return; 
        } 
        if (blen == 0) {
            for (i=0; i
                output[outlen++] = *(stra+i);
            output[outlen] = '\0';
            printf("%s\n", output);
            return;
        }
        
        output[outlen] = *stra;
        output_all(stra+1, strb, output, outlen+1, alen-1, blen);
        output[outlen] = *strb;
        output_all(stra, strb+1, output, outlen+1, alen, blen-1);
    }*/

    public static void outputAll (char [] arrA, char [] arrB, char [] out, int outlen, int alen, int blen) {
    	int i = 0, j = 0;
    	if (alen == 3) {
    		for (i = blen; i < arrB.length; i++) {
    			out[outlen] = arrB[i];
    			outlen++;
    		}
    		System.out.println(out);
    		return;
    	}
    	if (blen == 3) {
    		for (j = alen; j < arrA.length; j++) {
    			out[outlen] = arrA[j];
    			outlen++;
    		}
    		System.out.println(out);
    		return;
    	}
    	
    	out[outlen] = arrA[alen];
    	outputAll (arrA, arrB, out, outlen + 1, alen + 1, blen);
    	out[outlen] = arrB[blen];
    	outputAll (arrA, arrB, out, outlen + 1, alen, blen + 1);
    }
    
    //13. coding implement divide without / operator
    public static int divide(int dividend, int divisor) {
    	int count = 0;
    	while (dividend > 0) {
    		dividend -= divisor;
    		count++;
    	}
    	return count;    		
    }
    //14. write an algorithm to traverse a knight covering all the squares 
    //on a chessboard starting at a particular point.
    //   Ans: there are eight possible direction for a knight. Use a stack to track the
    //   path, if it is valid step, push to stack and continue. Otherwise, go back to last step
    //   in the stack and use a new direction to try. If all directions are used, then go back
    //   to last step until you find a new valid step. Until you find a possible path to
    //   traverse all squares, you can stop. Or return failed for no possible solution.
    
    // 15. Max subset sum in array
    public static int maxSubArray(int [] arr) {
    	int sum = 0;
    	int max = 0;
    	for (int i = 0; i < arr.length; i++) {
    		sum += arr[i];
    		if (sum < 0) sum = 0;
    		if (sum > max)
    			max = sum;
    	}
    	return max;
    }
    
    // 16. Max subset sum in matrix
    public static int maxSubMatrix(int [][] matrix) {
    	int curMax = 0;
    	int max = 0;
    	int [] arr = new int [matrix[0].length];
    	
    	for (int i = 0; i < matrix.length; i++) {
    		for (int r = 0; r < arr.length; r++) arr[r] = 0;
    		for (int j = i; j < matrix.length; j++) {    			
    			for (int k = 0; k < arr.length; k++) {
    				arr[k] += matrix[j][k];
    			}
    			curMax = maxSubArray(arr);
    			if(curMax > max)
    				max = curMax;
    		}
    	}
    	return max;
    }
    
    // 17. Given an integer array {1, -20, 29, 9, 1, 100, ..., 29), 
    // please return the index of the first non-repetitive element in the array.
    
    public static int findFirstNonRepetitive (int [] arr) {
    	Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
    	for (int a : arr) {
    		if (map.containsKey(a)){
    			Integer i = (Integer)map.get(a);
    			map.put(a, i+1);
    		} else {
    			map.put(a, 1);
    		}    		
    	}
    	
//    	Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
//    	while(it.hasNext()){
//    		Map.Entry<Integer, Integer> en = it.next();
//    		if (en.getValue().compareTo(1) == 0) {
//    			return en.getKey().intValue();
//    		}
//    	}
    	for (int i = 0; i < arr.length; i++) {
    		Integer v = (Integer)map.get(arr[i]);
    		if (v.compareTo(1) == 0) {
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    // 18. Write a function that takes as input two integer arrays of length n, input 
    //and index, and generates a third array, result, such that:
    //	result[i] = product of everything in input except input[index[i]]
    public static int [] productExcept (int [] input, int [] index) {
    	int [] result = new int [input.length];
    	int numOfZero = 0;
    	int product = 1;
    	for (int a : input) {
    		if (a != 0) {
    			product *= a;
    		} else {
    			numOfZero++;
    		}
    	}
    	for (int i = 0; i < input.length; i++) {
    		if (numOfZero >= 2) {
    			result[i] = 0;
    		} else if (numOfZero == 1) {
    			if (input[index[i]] == 0)
    				result[i] = product;
    			result[i] = 0;
    		} else {
    			result[i] = product / input[index[i]]; 
    		}
    	}
    	
    	return result;
    }
    
    // 20. Imagine you have an unbalanced binary search tree. Design an
    // algorithm which creates a linked list of all the nodes at each depth
    class NewNode{
		  private int value;
		  public int getValue(){return value;}
		  NewNode left = null;
		  NewNode right = null;
		  NewNode prev = null;
		  NewNode next = null;
	}
    public static void convertTree(NewNode root) {
    	if (root == null) return;
    	Queue<NewNode> queue = new LinkedList<NewNode>();
    	NewNode levelNode = null;
    	NewNode prevNode = null;
    	queue.offer(levelNode);
    	if (root.left != null) queue.offer(root.left);
    	if (root.right != null) queue.offer(root.right);
    	while(!queue.isEmpty()) {
    		NewNode curNode = queue.poll();
    		if (curNode == levelNode) {
    			if (!queue.isEmpty()) //only queue contains other level nodes, we add levelNode again
    				queue.offer(levelNode);
    			prevNode = null;
    		} else {
    			curNode.prev = prevNode;
    			curNode.next = queue.peek();
    			prevNode = curNode;
    			if (curNode.left != null) queue.offer(curNode.left);
    	    	if (curNode.right != null) queue.offer(curNode.right);
    		}
    	}
    	
    }
    
    // 21. Given n unsigned integer, output 2 integers which has the maximum result 
    // after XOR.    
    // 22. Given a string, output the letters and digits separately in an ordered way.
    // 23. Remove duplicate from the input string
    // 24. Substring search problem
    // 25. Given a string, count how many times each character occurs.    
}

class DNode{
	int value;
	DNode next;
	DNode other;
	public DNode(int v) {
		value = v;
	}
}

class Node{
	  public int value;
	  public int getValue(){return value;}
	  Node left = null;
	  Node right = null;
	  Node parent = null;
	  public Node(int v) {
		  value = v;
	  }
}
