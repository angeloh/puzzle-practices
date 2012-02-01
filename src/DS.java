import java.util.*;

public class DS {
	//1. A binary tree contain integer values (both positive and negative) in its data field. Given a number N, 
	//find a path from root to leaf which sums to N.
	//bool ContainsSum(btree *root, int N)
	
	class Node {
		Node left;
		Node right;
	    int value = 0;
	    public Node(int v) {
	    	value = v;
	    }
	}
	//DFS
	public boolean containSum(Node currNode, int n) {
		
		if (currNode == null)
			return n == 0;
		
	    n = n - currNode.value;	    
//	    boolean bool = containSum(currNode.left, n);
//	    if (bool==true)
//	    	return true;
//	    bool = containSum(currNode.right, n);
//	    return bool;
	    
	    return (containSum(currNode.left, n) || containSum(currNode.right, n));
	}
	
	public List<Integer> containSum(Node currNode, int n, List<Integer> path) {
		if (currNode == null)
			if (n == 0)
				return path;
			else
				return Collections.<Integer>emptyList();
		n = n - currNode.value;
		
		List<Integer> newPath = new ArrayList<Integer>(path);
		newPath.add(currNode.value);
		List<Integer> leftPath = containSum(currNode.left, n, newPath);
		if (leftPath.size() > 0)
			return leftPath;
		List<Integer> rightPath = containSum(currNode.right, n, newPath);
		return rightPath;
		
	}
	
	public static int pow(int x, int y) {
		if (y == 0)
			return 1;
		int rt = 1;
		int tmp = x;
		int k = y;
		while(k > 0){
			if ((k&1)==1)
				rt *= tmp;
			tmp *= tmp;
			k >>= 1;
		}
		return rt;

	}
	
	public static double pow1(int x, int y) {
		boolean isNeg = false;
		if (y == 0)
			return 1;
		if (y < 0) {
			isNeg = true;
			y *= -1;
		}
		int rt = 1;
		int tmp = x;
		int k = y;
		while(k > 0){
			if ((k&1)==1)
				rt *= tmp;
			tmp *= tmp;
			k >>= 1;
		}
		if (isNeg){
			return (double)1/rt;
		}
		return rt;
	}

	
	public static void main (String [] args) {
		System.out.println("Power result is:" + pow(5, 4));
		System.out.println("Power result is:" + pow1(5, -2));	
	}
	
	
}