import java.util.*;
import java.io.*;

public class JavaPractice6 {
	String title;
	enum abc {A, B, C}
	public static int A = 5;
	
	
	public static void main (String [] args) {
			
	/*		Rank rank1 = new Rank(1);
			Rank rank2 = new Rank(2);
			Rank rank3 = new Rank(3);
			Rank rank4 = new Rank(4);
			Rank rank5 = new Rank(5);
			NavigableSet<Rank> ranks = new TreeSet<Rank>();
			ranks.add(rank2);ranks.add(rank3);ranks.add(rank1);
			ranks.add(rank5);ranks.add(rank4);
			System.out.println(ranks);
			ranks.pollFirst();
			ranks.pollLast();
			System.out.println(ranks.pollLast().toString());
			System.out.printf("%f %b", Math.PI, Math.E);
			List <String> strings = new ArrayList<String>();
			strings.add("aAaA");
			strings.add("AaA");
			strings.add("aAa");
			strings.add("AAaa");
			Collections.sort(strings);
			for (String s: strings) {
				System.out.print(s+" ");
			}*/
			getPrimes(100);
			getprimes2(100);
			System.out.println(add_no_arith(8, 12));
			
	//		int [] array = {1,1,1,1,2,2,2,2};
	//		int [] array = {1,1,1,2,2,2};
			int [] array = {1,1,1,1,1,2,2,2,2,2};
			shuffleArray(array);
			
			//remove duplicate chars in a string
			String noDupStr = removeDeplicates("abcdacdefg");
			System.out.println(noDupStr);
			
			int shift = -5;
			System.out.println("-5: " + Integer.toBinaryString(shift));
			shift = -5 >> 1;
			System.out.println(">> -5: " + Integer.toBinaryString(shift));
			shift = -5 >>> 1;
			System.out.println(">>> -5: " + Integer.toBinaryString(shift));
			
			System.out.println("5: " + Integer.toBinaryString(5));
			
			int swapReqd = bitSwapReqd(-6, 8);
			System.out.println("reqd bits to swap:" + swapReqd);
			
			int max = ~0;
			System.out.println("~0: " + Integer.toBinaryString(max));
			int left = max - ((1 << 6) - 1); /* 1’s through position j, then
			all 0’s */
			System.out.println("left: " + Integer.toBinaryString(left));
			int right = ((1 << 2) - 1); /* 1’s after position i */
			System.out.println("right: " + Integer.toBinaryString(right));
			int mask = left | right; /* 1’s, with 0s between i and j */
			System.out.println("mask: " + Integer.toBinaryString(mask));
			boolean [] used = new boolean[5];
			char [] outStr = new char[5];
			char [] permuStr = "abcde".toCharArray();			 
			permuteString(used, outStr, permuStr, 0);
			System.out.println();
			char [] paranChars = new char[6];
			printParan(3, 3, paranChars, 0);
			char [] sets= {'a','b','c'};
			printAllSubSets(sets);
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(2);
			list.add(3);
			System.out.println(printK(list, 3, 4));
			
			System.out.println("31 is prime:");
			System.out.println(isPrime3(31));
	}
	public JavaPractice6(){
		title = title.substring(2);
	}
	/**
	 * @param list
	 * @param n
	 * @param k
	 * @return String
	 * 
	 * For a set {1, 2, 3, ..., n},we can get n! different permutations.
	 * When we print the list out with index:
     * 0.123 
     * 1.132 
     * 2.213 
     * 3.231 
     * 4.312 
     * 5.321 
     * Now please write a function void print (int n, int k)
	 * to return kth permutation.
	 */
	public static String printK (ArrayList<Integer> list, int n, int k) {		
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for (Integer ii : list) {
			pool.add(new Integer(ii));
		}
		
		int[] fact = new int[n+1];
		fact[0] = 1;
		for (int i = 1 ; i <= n; i++) {
			fact[i] = fact[i-1] * i;
		}
		
		if (k >= fact[n]) {//k is 0 based
			return null;//no way to print a permutation which is larger than n!
		}
		StringBuilder sb = new StringBuilder("");
		for (int j = n-1; j > 0; j--) {
			int d = k / fact[j];
			sb.append(list.get(d));
			list.remove(d);
			k = k % fact[j];
		}
		sb.append(list.get(0));
		return sb.toString();
	}
	
	
	class Line {
		int min = 0;
		int max = 0;
		public int getMin() {
			return min;
		}
		public void setMin(int min) {
			this.min = min;
		}
		public int getMax() {
			return max;
		}
		public void setMax(int max) {
			this.max = max;
		}
		
	}
	/**
	 * @param list
	 * @return int
	 * There are n lines in one-dimension plane. The end points of lines are known.
	 * Please design a algorithm to calculate the total cover length.
	 * For example, if Line A's coordinate  is [4,8] and Line B's coordinate [1,5.1].
	 * Then the total cover length of A and B is 7 (8-1).
	 */
	public static int calcMaxCover(List<Line> list) {
		// Suppose the list of lines are sorted by start points. 
		// If not, need to sort first.
		Iterator<Line> iter = list.iterator();
		Line first = iter.next();
		int curMax = first.getMax();
		int totalCover = first.getMax() - first.getMin();
		Line curLine;
		while(iter.hasNext()) {
			curLine = iter.next();
			if (curLine.getMin() < curMax && curLine.getMax() < curMax){
				//do nothing
			} else if (curLine.getMin() > curMax) {				
				totalCover += curLine.getMax() - curLine.getMin();
				curMax = curLine.getMax();
			} else {
				totalCover += curLine.getMax() - curMax;
				curMax = curLine.getMax();
			}			
		}
		return totalCover;
	}
	
	
	/**
	 * @param array
	 * Suppose we have an array a1, a2, ..., an, b1, b2, ..., bn. 
	 * Implement an algorithm to change this array
	 * to a1, b1, a2, b2, ..., an, bn.
	 */
	public static void shuffleArray(int [] array) {
		if (array == null || array.length == 0){
			return;
		}
		System.out.print("Before:");
		System.out.println(Arrays.toString(array));
		int n = array.length/2;
		for (int i = 0; i < n - 1; i++){
			for (int j = i+1; j < n; j++){
				swapArray(array, j, n+j-i-1);
			}
			System.out.println("*****");
			System.out.println(Arrays.toString(array));
			System.out.println("*****");
		}
		System.out.print("After:");
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void swapArray(int [] array, int x, int y){
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	/**
	 * @param str
	 * @return String
	 * Design an algorithm and write code to remove the duplicate characters in a
	 * string without using any additional buffer.
	 */
	public static String removeDeplicates(String str) {
		if (str == null || str.length() == 0){
			return "";			
		}
		char [] chars = str.toCharArray();
		int curP = 1;
		for (int i = 1; i < chars.length; i++){			
			//search found chars to check if any deplicates
			int j = 0;
			for (j = 0; j < curP; j++){
				if (chars[j] == chars[i]) {
					break;
				}
			}
			//if no deplicate, then insert to position curP
			if (j == curP){
				chars[curP] = chars[i];
				curP++;
			}
		}
		//fill in empty characters for the rest of string
		for (int i = curP; i < chars.length; i++){
			chars[i] = '\0';
		}		
		
		return new String(chars).trim();
	}
	
	/**
	 * @param max
	 * get prime number below an integer number
	 * http://www.mitbbs.com/article_t/JobHunting/31435581.html
	 */
	public static void getPrimes(int max){
		if (max <= 1){
			return;
		}
		List<Integer> prime = new ArrayList<Integer>();	
		boolean [] nonPrimeArray = new boolean[max+1];
		for(int i = 3; i <= max; i +=2){
			if (!nonPrimeArray[i]){
				prime.add(i);
				for(int j = i*i; j <= max; j+=i){
					nonPrimeArray[j] = true;
				}
			}
		}
		Iterator<Integer> it = prime.iterator();
		System.out.print("2 ");
		while(it.hasNext()){
			System.out.print(""+it.next()+" ");
		}
		System.out.println();
	}
	
	public static void getprimes2(int max){
		if (max <= 1) {
			return;
		}
		System.out.print("2 ");
		if(max >= 3)
			System.out.print("3 ");
		for (int i=1, j=1; (j=6*i+1)<=max; i++){
			if (isPrime(j-2))
				System.out.print((j-2)+" ");
			if (isPrime(j))
				System.out.print(j+" ");			
		}
		System.out.println();
	}
	
	public static boolean isPrime(int num){
		if (num <= 1)
			return false;
		if(num == 2)
			return true;
		if (num % 2 == 0)
			return false;
		int i = 3;
		while(i*i <= num){//we only need to check the number below square root of input
			if (num % i == 0) {
				return false;
			}
			i += 2;
		}
		return true;
		
	}
	
	public static boolean isPrime2(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		
		int sqrt = (int)Math.round(Math.sqrt(n));
		for (int i = 3; i <= sqrt; i+=2){
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrime3(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		if (n % 3 == 0)
			return false;
		int sqrt = (int)Math.round(Math.sqrt(n));
		//test if any prime factors (prime is 6k+1 or 6k-1)
		int k = 1;
		int factor;
		while((factor = 6*k-1)<=sqrt){
			if(n % factor == 0)
				return false;
			factor = 6*k+1;
			if(n % factor == 0)
				return false;
			k++;
		}
		return true;
	}

	
	public static int add_no_arith(int a , int b){
		if (b == 0)
			return a;
		int sum = a^b;
		int carry = (a&b) << 1;
		return add_no_arith(sum, carry);
	}
	
	public static int bitSwapReqd(int a, int b){
		int xor = a ^ b;
		int count = 0;
		for (int i = xor; i != 0; i = i>>>1){
			count += i & 1;
		}
		return count;
	}
	
	public static int swapOddEvenBits(int x){
		return (((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1));
	}
	
	public static void permuteString(boolean [] used, char [] out, char [] input, int level) {
		if (used == null || out == null || input == null)
			return;
		if (used.length != input.length || out.length != input.length)
			System.out.println("input arrays need to be same size!!");
		if (level == out.length)
			System.out.println(Arrays.toString(out));
		for (int i = 0 ; i < used.length; i++){
			if (used[i] == false) {
				out [level] = input[i];
				used[i] = true;
				permuteString(used, out, input, level+1);
				used[i] = false;
			}
		}
	}
	
	class Edge {
		int y = 0;
		int weight = 0;		
	}
	
	class Graph{
		final static int MAX_NODES = 20;
		ArrayList<List<Edge>> edges;
		int [] degree; //out degrees of each vertex
		int nVertices;
		int nEdges;
		boolean directed;		
	}
	
	public static void dijkstra (Graph g, int start) {
		boolean [] inTree = new boolean[Graph.MAX_NODES];
		int [] distance = new int[Graph.MAX_NODES]; //distance array from start for each vertex
		int [] parent = new int[Graph.MAX_NODES];
		int v;
		
		for (int i = 0; i < Graph.MAX_NODES; i++){
			inTree[i] = false;
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		
		distance[start] = 0;
		v = start;
		
		while(!inTree[v]) {
			inTree[v] = true;
			List<Edge> adjList = g.edges.get(v);
			Iterator<Edge> it = adjList.iterator();
			while(it.hasNext()) {
				Edge e = it.next();
				int w = e.weight + distance[v];
				if (distance[e.y] > w) {
					distance[e.y] = w;
					parent[e.y] = v;
				}
			}
			
			int minDist = Integer.MAX_VALUE;
			for (int i = 0; i < Graph.MAX_NODES; i++){
				if (!inTree[i] && minDist > distance[i]){
					minDist = distance[i];
					v = i;
				}
			}
		}
	}
	
	/**
	 * @param l
	 * @param r
	 * @param out
	 * @param count
	 * 
	 * Implement an algorithm to print all valid (eg, properly opened and closed)
	 * combinations of n-pairs of parentheses.
	 */
	public static void printParan(int l, int r, char [] out, int count) {
		if (l < 0 || r < 0)
			return; // wrong param
		if (l == 0 && r == 0) {
			System.out.println(Arrays.toString(out));
		}
		if (l > 0) {
			out[count] = '(';
			printParan(l-1, r, out, count+1);
		}
		if (l < r && r > 0) {
			out[count] = ')';
			printParan(l, r-1, out, count+1);
		}
	}
	
	/**
	 * @param chars
	 * Write a method that returns all subsets of a set.
	 * 
	 * Just like printing out each possible bit number form 0 to 2^n
	 */
	public static void printAllSubSets(char [] chars){
		if (chars == null) 
			return;
		int len = chars.length;
		int num = 1 << len;
		for (int i = 0; i < num; i++) {
			StringBuffer sb = new StringBuffer();
			int tmp = i;
			int c = 0;
			while (tmp > 0) {
				if ((tmp & 1) == 1) {
					sb.append(chars[c]);
				}
				tmp = tmp >> 1;
				c++;
			}
			System.out.println(sb.toString());
		}
	}
	
	
	class Parent<E>
	{
	public E get() { return null;}
	public void set(E e) { }
	}
	class Child extends Parent<Number>
	{
		public void set(Long arg){
			
		}
	}
	class MyNavigableMap extends TreeMap<Integer, Integer>{
		public Integer put(Integer key, Integer value){
			return super.put(value, key);
		}
	}
	class MyNavigableSet extends TreeSet<String>{
		public boolean add(String value){
		return super.add(value);
		}
	}
	static class Rank implements Comparable<Rank>{
		private int rank;
		public Rank(int rank){
		this.rank = rank;
		}
		@Override
		public int compareTo(Rank second) {
		return -1;
		}
		public String toString(){
		return rank + "";
		}
	}
	
//	class A {
//		public void doing (int num) {
//			
//		}
//	}
//	class B extends A {
//		public void doing (int num) throws IOException{			
//		}
//	}

	interface DeclareStuff {
		public static final int EASY = 3;
		void doStuff();
	}

	static class Drink implements Comparable {
		int i = 5;
		public Drink(int ii){
			i = ii;
		}
		public int compareTo(Object o) {
			return 0;
		}
	}
	
}