import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.regex.*;
import java.util.HashMap;

public class JavaPractice3{
	
	
    public static void main(String[] args){
    	//reverse a string
    	String inputStr = "Disney World is no fun";    	
    	StringTokenizer st = new StringTokenizer(inputStr, " ");
    	StringBuffer sb = new StringBuffer();
    	List <String> list = new ArrayList<String>();
    	while (st.hasMoreTokens()) {    		   
    		list.add(st.nextToken());
    	}
    	Collections.reverse(list);
    	Iterator<String> iter = list.iterator();
    	while(iter.hasNext()) {
    		sb.append(iter.next());
    		if(iter.hasNext()) sb.append(" ");
    	}    	
    	System.out.println(sb.toString());    	
    	
    	//check if a hexdecimal is a palindrom
    	int n1 = Integer.parseInt("3000000c", 16);    
    	//int n1 = 805306380;    	
        String isPal = isPalindrome(n1) ? "a palindrom" : "not a plaindrom";         
        System.out.format( "%1$s is %2$s\n", Integer.toHexString(n1), isPal);
        
        //split a string with decimal numbers
        String splitStr = "ab $2,300.58?2,,300.58 123?.235";
        System.out.println(splitStr + " send to split, the result is :");
        ArrayList<String> splitList = splitString(splitStr);
        for (String ss: splitList) {  
        	System.out.println(ss.toString());
        }
        
        //Given a string : abbbccddddeee Encode it to : ab3c2d4e3
        String encodeString = "abbbccddddeee";
        encodeString = encode(encodeString);
        System.out.println(encodeString);
        
        //find the median
        //int [] A = {3, 5, 10, 12};
        //int [] A = {1, 2, 3, 4};
        int [] A = {1, 3, 5, 6, 7};
        //int [] A = {1, 2, 3, 4, 9};
        //int [] B = {1, 2, 4, 8};
        //int [] B = {5, 6, 7, 8};
        int [] B = {2, 4, 8, 9, 10};
        //int [] B = {5, 6, 7, 8, 10};
        
        System.out.println("The median is " + find_median(A, B));
        System.out.println("The median2 is " + find_median2(A, B));
        
        List<String> helloStr = JavaPractice3.formatString2("1234567", 3, 3);
        Iterator<String> it = helloStr.iterator();
        while(it.hasNext()){
        	String next = it.next();
        	System.out.println(next);
        }
        
        consecutiveSum(B);
        
        int [] rotatedArray = {3, 4, 7, 8, 1, 2};
        System.out.println("rotatedBinarySearch:"+rotatedBinarySearch(rotatedArray, 0, 5, 4));
        System.out.println("rotatedBinarySearch2:"+rotatedBinarySearch2(rotatedArray, 4));
             
        int [] arr = {6, 4, 5, 3, 7, 1, 9};
		Node root = buildBinaryTree(arr);
		inOrderIter(root);
		postOrderIter2(root);
		
		int [] arr2 = {4 ,7, 2, 3, 1, 5, 6, 8, 9};
		System.out.println(select(arr2, 4));
    }   
        
    public static boolean isPalindrome(int iint) {
    	
    	for (int i = 0; i <=15; i++) {
    		boolean bit1 = ((iint & (1 << i))>0);
    		boolean bit2 = ((iint & (1 << 31-i))>0);
    		if (bit1 != bit2) return false;    		
    	}
    	return true;
    }
    
    /**
    *"ab $2,300.58?2,,300.58 123?.235"
    * " " "," "." "?" separators
    * result is
    * ab
    * $2,300.58
    * 2
    * 300.58
    * 123
    * 235
    */
    public static ArrayList<String> splitString(String str) {
    	ArrayList<String> list = new ArrayList<String>();
    	Pattern exp = Pattern.compile("(\\$|)([1-9]\\d{0,2}(\\,\\d{3})*|([1-9]\\d*))(\\.\\d{2})?$");
    	StringTokenizer stFirst = new StringTokenizer(str, " ?");
    	final String REPLACE = "***";
    	while (stFirst.hasMoreTokens()) {
    		String tokenStr = stFirst.nextToken();
    		Matcher matcher = exp.matcher(tokenStr);    		
    		ArrayList<String> tempMatches = new ArrayList<String>();
    		StringBuffer repTokenStr = new StringBuffer();    		
    		while(matcher.find()) {    			
    			matcher.appendReplacement(repTokenStr,REPLACE);
    			tempMatches.add(tokenStr.substring(matcher.start(), matcher.end()));    			
    		} 
    		matcher.appendTail(repTokenStr);    		
    		StringTokenizer stSecond = new StringTokenizer(repTokenStr.toString(), ",.");
    		int replIndex = 0;
    		while(stSecond.hasMoreTokens()) {
    			String tokenStrSec = stSecond.nextToken();
    			if (tokenStrSec.equals(REPLACE)) {
    				list.add(tempMatches.get(replIndex));
    				replIndex++;
    			}else {
    				list.add(tokenStrSec);
    			}
    		}
    	}   	
    	
    	return list;
    }
    
    /**
     * Given a string : abbbccddddeee
     * Encode it to : ab3c2d4e3
     */
//    void encode(char *s)
//    {
//	    int i,j=0,len=strlen(s);
//	    int count;
//	    char buffer[10];
//	    int index;
//	    char n;    
//	 
//	    for(i=0;i<len;i++){
//		    s[j++]=s[i];
//		
//		    if(s[i]==s[i+1]){
//			    count=2;
//			    i++;
//			    index=0;
//			    while(s[i]==s[i+1]){
//			    	i++;count++;
//			    }
//			    while(count){
//				    n=count%10+'0';
//				    count/=10;
//				    buffer[index++]=n;
//			    }
//			
//			    index--;
//			    while(index>=0)
//			    	s[j++]=buffer[index--];
//		    }
    
//	
//	    }
//	    s[j]='\0';
//    }
    /**
     * Given a string : abbbccddddeee
     * Encode it to : ab3c2d4e3
     */
   public static String encode(String s) {
    	char [] charArray = s.toCharArray();
    	char [] stack = new char[10];
    	int count= 0;
    	int i = 0, j = 0;
    	int sIndex = 0;
    	while(i < charArray.length) {
    		char curChar = charArray[i];
    		charArray[j++] = charArray[i++];
    		
    		if (curChar == charArray[i]) {
    			count = 1;
    			while (i < charArray.length && curChar == charArray[i]) {
    				count++;
    				i++;
    			}
    			if (count>9) {
    				sIndex=0;    				
    				while(count>0) {
    					int r = count%10;
    					count/=10;
    					stack[sIndex++] = Integer.toString(r).charAt(0);
    				}
    				sIndex--;
    				while(sIndex>=0) {
    					charArray[j++] = stack[sIndex--];
    				}
    			} else {
    				charArray[j++] = Integer.toString(count).charAt(0);    				
    			}    			
    		}    		
    	}
    	
    	for (i = j; i < charArray.length; i++) {
    		charArray[i] = '\u0000';
    	}
    	return new String(charArray).trim();
    	
    }
   
   public static int find_median(int[] A, int[] B)
   {
	   // A and B are sorted array with length n
	   int n = A.length;
	   int k = n/2;

	   int i = n/2-1;
	   int j = 0;

	   if(n%2 == 0)
		   j = n/2-1;
	   else
		   j = n/2;

	   while(true)
	   {
		   if(A[i] == B[j])
			   return A[i];
		   else if (k == 0) {
			   if(A[i] <= B[j])
				   return A[i+1];
			   if(B[j] <= A[i])
				   return B[j+1];
		   }		   
		   else if(A[i] < B[j])
		   {
			   if(A[i+1] >= B[j])
				   return B[j];
			   else
			   {
				   k = k/2;
				   i += k ;
				   j -= k;
			   }
		   }
		   else
		   {
			   if(B[j+1] >= A[i])
				   return A[i];
			   else
			   {
				   k = k/2;
				   i -= k ;
				   j += k;
			   }
		   }
	   }
   }
   
   // both array are sorted
   // sequentially find the median from two arrays
   public static double find_median2(int[] a, int[] b)
   {
   	int c1 = 0;
   	int c2 = 0;
   	int aLen = a.length;
   	int bLen = b.length;
   	int med = (aLen + bLen)/2;
   	int count = 1; //count from 1
   	double pre = 0;
   	double curr = 0;
   	boolean odd = false;	
   	if ((aLen+bLen)%2 == 1) {
   		odd = true;
   	}
   	if (odd)
   		med++;
   	//ex. for 10 elements median: (#5+#6)/2 ; for 9 elements: #5  so that we want to find until #6
   	while((c1 < aLen) && (c2 < bLen) && (count <= med+1)){
   		if (a[c1] <= b[c2]) {   						
   			pre = curr;
   			curr = (double)a[c1];
   			c1++;
   			count++;
   		} else {   			
   			pre = curr;
   			curr = (double)b[c2];
   			c2++;
   			count++;
   		}
   	}
   	count--;
   	//got result
   	if (count == med+1) {
   		if (odd)
   			return pre;
   		return (pre+curr)/2;
   	}
   	if (c1 == aLen) {
   		while(c2 < bLen && count <= med+1){
   			pre = curr;
   			curr = (double)b[c2];
   			c2++;
   			count++;   			
   		}
   	} else {
   		while(c1 < aLen && count <= med+1){
   			pre = curr;
   			curr = (double)a[c1];
   			c1++;
   			count++;   			
   		}
   	}
   	if (odd)
   			return pre;
   	return (pre+curr)/2;
   }
   
   /**
    * reverse a string
    * @param str
    * @return
    */
   public static String revStr(String str) {
		char [] charArray = str.toCharArray();
		int left = 0, right = charArray.length;
		while(left<right) {
			char temp = charArray[left];
			charArray[left] = charArray[right];
			charArray[right] = temp;
			left++;
			right--;
		}
		return new String(charArray);
	}
	
   /**
    * reverse words
    * @param str
    * @return
    */
	public static String revWords(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		List<String> list = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		Collections.reverse(list);
		StringBuffer sb = new StringBuffer();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext())
				sb.append(" ");
		}
		return sb.toString();
		
	}
	
	//recursive
	public static void printBinary(int [] out, int curIndex) {
		if (curIndex==out.length) {
			System.out.println(out);
			return;
		}
		out[curIndex] = 0;
		printBinary(out,curIndex+1);
		out[curIndex] = 1;
		printBinary(out,curIndex+1);
	}
	
	//iterative
	public static void printBinary() {
		int [] out = new int [4];
		for (int i = 0; i < out.length; i++)
			out[i] = 0;
		while (true) {
			System.out.println(out);
			for (int i = out.length-1; i>=-1; i--) {
				if (i == -1)//finish all prints
					return;
				if (out[i] == 0){
					out[i] = 1;
					break;
				} else {
					out[i] = 0;
				}
			}
		}	
		
	}
	
	public static void swap(int [] a, int i, int k) {
		int temp = a[i];
		a[i] = a[k];
		a[k] = temp;
	}
	/**
	 * quick sort
	 * @param a
	 * @param left
	 * @param right
	 */
	public static void quickSort(int [] a, int left, int right) {
		if (left>=right)
			return;
		int p = left;
		int base = a[left];		
		for (int i = left+1; i <= right; i++) {
			if (a[i]<=base) {
				p++;
				swap(a, p, i);
			}
		}
		swap(a, left, p);
		quickSort(a, left, p-1);
		quickSort(a, p+1, right);		
	}
	
	public static int partition(int [] arr, int left, int right, int k) {
		int pivotValue = arr[k];
		swap(arr, k, right); //move pivot to right
		int p = left;
		for (int i = left; i < right; i++) {
			if (arr[i] < pivotValue) {
				swap(arr, p, i);
				p++;
			}
		}
		swap(arr, p, right);
		return p;	
	}

	/**
	 * @param arr
	 * @param k
	 * @return k's value
	 * 
	 * quick select for k's value
	 */
	public static int select(int [] arr, int k) {
		if (arr == null || arr.length < k)
			return Integer.MAX_VALUE;
		int left = 0;
		int right = arr.length - 1;
		while(true) {
			int newPivot = partition(arr, left, right, k);
			if (newPivot == k)
				return arr[newPivot];
			if (newPivot < k)
				left = newPivot+1;
			if (newPivot > k)
				right = newPivot-1;
		}
	}
    
	public static Node buildBinaryTree(int [] arr) {
		Node root = null;
		root = insertNode(root, arr[0]); 
		for (int i = 1; i < arr.length; i++) {
			insertNode(root, arr[i]);
		}
		return root;
	}
	
	public static Node insertNode (Node root, int value) {
		if(root == null) {
			root = new Node(value);
		} else if (value < root.value){
			root.left = insertNode(root.left, value);
		} else {
			root.right = insertNode(root.right, value);
		}
		return root;
	}
	
	public static void preOrder(Node start) {
		if (start == null) {
			return;
		}
		System.out.print(start.getValue() + " ");
		preOrder(start.left);
		preOrder(start.right);
	}
	
	
	//iterative preorder
	public static void preOrderIter(Node start) {
		if (start == null) {
			return;
		}
		Node node = start;
		Stack <Node>st = new Stack<Node>();
		st.push(node);
		while(!st.empty()) {
			node = st.pop();
			System.out.printf("%d", node.getValue());
			if (node.right != null)
				st.push(node.right);
			if (node.left != null)
				st.push(node.left);
		}
	}
	
	public static void postOrderIter2(Node root)  {
		if (root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		Node c = root;
		Node pre = null;
		while(!stack.isEmpty() || c!=null){
			if(c!= null) {
				stack.push(c);
				c = c.left;
			} else {
				c = stack.peek();
				if(c.right == null || c.right == pre){
					stack.pop();
					System.out.print(c.getValue() + ",");					
					pre = c;
					c = null;
				} else {
					c = c.right;
				}
			}
		}
		System.out.println();
	}
	
	//iterative
	public static void postOrderIter1(Node root) {
		if (root == null)
			System.out.println("No Nodes Print!!");
		Stack<Node> s = new Stack<Node>();
		Node cursor = root;		
		boolean done = false;
		int lastPrintValue = -1;
		System.out.println("\nPostOrder Print:");
		while(!done) {
			if (cursor != null) {
				s.push(cursor);
				if (cursor.right != null)
					s.push(cursor.right);
				cursor = cursor.left;
			} else {
				if (!s.empty()) {
					cursor = s.pop();
					if (((cursor.right != null) && (cursor.right.value == lastPrintValue)) || 
							((cursor.left != null) && (cursor.left.value == lastPrintValue))) {
						System.out.print(cursor.value+",");
						lastPrintValue = cursor.value;
						cursor = null;
					} else if (cursor.right == null && cursor.left == null){
						System.out.print(cursor.value+",");
						lastPrintValue = cursor.value;
						cursor = null;
					}
				} else {
					done = true;
				}
			}
		}
	}
	//iterative
	public static void	inOrderIter(Node root) {
		if (root == null)
			System.out.println("No Nodes Print!!");
		Stack<Node> s = new Stack<Node>();
		Node cursor = root;		
		boolean done = false;
		System.out.println("\nInOrder Print:");
		while(!done) {
			if (cursor != null) {
				s.push(cursor);
				cursor = cursor.left;
			} else {
				if (!s.empty()) {
					cursor = s.pop();
					System.out.print(cursor.value+",");
					cursor = cursor.right;
				} else {
					done = true;
				}
			}
		}
		System.out.println();
	}
	
	public static void inOrderIter2(Node root, Node target) {
		if (root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		Node node = root;		
		while(node != null || !stack.empty()) {			
			if (node != null) {				
				stack.push(node);
				node = node.left;				
			} else {
				node = stack.pop();
				System.out.print(node.getValue() + ",");				
				node = node.right;				
			}
		}		
	}
	
	/**
	 * @param node
	 * @return Node
	 * Return next node of a given node in InOrder traversal
	 */
	public static Node findNextInOrder(Node node) {
		if (node == null)
			return null;
		Node target = node;
		if (target.right != null) {
			target = target.right;
			while(target.left!=null){
				target = target.left;
			}
		} else {
			Node p = target;
			target = target.parent;			
			while(target != null && target.left!= p){				
				p = target;				
				target = target.parent;
			}			
		}
		return target;
	}
	
	/**
	 * Amazon interview question
	 * @param str
	 * @param maxlength
	 * @param maxline
	 * @return
	 */
	public static List<String> formatString(String str, int maxlength, int maxline){
	     if (str == null) return null;
	     List<String> list = new ArrayList<String>();
	     int i = 0;
	     int index = 0;     
	     while(i < maxline && index < str.length()) {
	        if ((str.length()-index)<=maxlength){
	          String tmp = str.substring(index, str.length()).trim();
	          list.add(tmp);
	          break;
	        } else {
	          String tmp = str.substring(index, index+maxlength).trim();
	          list.add(tmp);
	          index = index + maxlength;
	          i++;
	        }
	     }   
	     return list;
	}
	
	public static List<String> formatString2(String str, int maxLength, int maxLine){
		if (str == null) return null;
		
		List<String> list = new ArrayList<String>();
		
		if (str.length() < maxLength) {
			list.add(str);
			return list;
		}
		
		int lines = str.length()/maxLength;
		if (lines > maxLine) {
			lines = maxLine;
		}
		
		for (int i = 0; i < lines; i++) {
			list.add(str.substring(i*maxLength, (i+1)*maxLength ));
		}
		
		if (lines == maxLine) {
			return list;
		}
		
		if ((str.length()%maxLength)>0)
			list.add(str.substring(lines*maxLength, str.length()));
		return list;

	}
	
//	public static List<String> formatString(String str, int maxLength, int maxLines){
//		if (str == null || str.equals("") || maxLength < 1 || maxLines < 1)
//			return null;
//		List<String> list = new ArrayList<String>();
//		int curPos = 0;
//		int totalLines = 0;
//		int strLength = str.length();
//		while(totalLines < maxLines && curPos < strLength){
//			if ((strLength-curPos) <= maxLength) {
//				String tmp = str.substring(curPos, strLength).trim();
//				list.add(tmp);
//				totalLines++;
//				break;
//			} else {
//				String tmp = str.substring(curPos, curPos+maxLength).trim();
//				list.add(tmp);
//				curPos = curPos+maxLength;
//				totalLines++;
//			}
//		}
//		return list;
//	    
//	}
    
//    class Position
//    {
//	    private int begin;
//	    private int end;
//	    private int len;
//	    public Position(int begin, int end)
//	    {
//		    this.begin=begin;
//		    this.end=end;
//		    this.len=len;
//	    }
//    }
     
//    public void findLongestPalindrom(String text) {
//	    Vector<Position> palindroms = new Vector<Position>();
//	    for(int i=0;i<text.length();i++)
//	      for(int j=i;j<text.length();j++)
//	        if (isPalindrom(text.substring(i,j)))
//	           palindroms.add(new Position(i,j,j-i));
//    }
//     
//    //sort the palindroms Vector by its third field i-j, and then the first position will be the longest(s) palindrom.
//    
//    public static boolean isPalindrom(String word)
//    {
//    int k = 0, lala = 0;
//    if (word.length() % 2 == 0) lala++;
//      for(int i=0;i<word.length()/2-lala;i++)
//       if (!word.charAt(i).equals(word.charAt(word.length()-k++)))
//           return false;
//    return true;
//    }
	
	/**
	 * How do you perform binary search on a rotated sorted array?
     * eg., 75, 77, 85, 91, 10, 19, 26, 29, 33, 45, 67
	 */
	public static int rotatedBinarySearch(int[] arr, int begin, int end, int element)
	{ 
		if(begin > end)
			return -1;

		int mid = (begin + end)/2;

		if(element == arr[mid])
			return mid;
		if(arr[begin] <= arr[mid] && arr[mid] <= arr[end])
		{ // compare with arr[mid]

			if(element > arr[mid])
				return rotatedBinarySearch(arr, mid+1, end, element);
			else
				return rotatedBinarySearch(arr, begin, mid-1, element);
		}
		else if(arr[begin] >= arr[mid] && arr[mid] <= arr[end])
		{ // compare with arr[end]
			if(element > arr[end] || element < arr[mid])
				return rotatedBinarySearch(arr, begin, mid-1, element);
			else
				return rotatedBinarySearch(arr, mid+1, end, element);
		}
		else
		{ 
			if(element < arr[begin] || element > arr[mid])
				return rotatedBinarySearch(arr, mid+1, end, element);
			else
				return rotatedBinarySearch(arr, begin, mid-1, element);
		}	
	}
	
	public static int rotatedBinarySearch2(int [] arr, int x){
		if (arr == null || arr.length <= 0)
			return -1;
		int l = 0;
		int r = arr.length - 1;
		while(l <= r) {
			int m = (1+r)/2;
			if (x == arr[m]) {
				return m;
			}
			if (arr[l] < arr[m]) {
				if (x > arr[m]) {
					l = m + 1;
				} else if (x >= arr[l]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			} else {
				if (x < arr[m]) {
					r = m - 1;
				} else if (x >= arr[l]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
		}
		return -1;
	}
	
	
	
	//Write a function that prints out all sets of consecutive 
	//integers that add up to all and any numbers within a given range.
	/**
	 * 5=2+3
     * 6=1+2+3
	 * 7=3+4
	 */
	public static void consecutiveSum(int [] arr) {
		for (int num: arr) {
			if(num<3)continue;
			int floor = (int)Math.floor(num/2);
			for (int i = 1; i <= floor; i++) {
				int rest = num - sum(i);
				if(rest>0 && (rest%(i+1) == 0)){
					int k = rest/(i+1);
					System.out.printf("%d=", num);
					for(int j=1;j<i+1;j++){
						System.out.printf("%d+",k+j-1);
					}
					System.out.printf("%d\n",k+i);
				}
			}
		}
		
	}
	
	private static int sum(int n){
		int sum = 0;
		if (n>0){
			for(int i = 1; i <= n; i++){
				sum+=i;
			}
		}
		return sum;
	}
    
}
 