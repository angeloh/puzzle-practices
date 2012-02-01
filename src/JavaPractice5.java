import java.util.*;


public class JavaPractice5{
	public static void main (String [] args) {
			System.out.println(Integer.MAX_VALUE == Integer.MAX_VALUE);
	}
	
	public static void dfs (Node root) {
		if (root == null) {
			return;
		}
		System.out.println(root.getValue());
		if (root.left != null) {
			dfs(root.left);
		}
		if (root.right != null) {
			dfs(root.right);
		}
	}
	
	public static void bfs (Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		q.offer(new Node(Integer.MAX_VALUE));
		while(q.peek() != null) {
			Node n = q.poll();
			if (n.getValue() == Integer.MAX_VALUE){ //level end
				System.out.println();
				if (q.peek() != null) {
					q.offer(new Node(Integer.MAX_VALUE));
				}
			}
			System.out.print(n.getValue() + " ");			
			if (n.left != null) {
				q.offer(n.left);
			}
			if (n.right != null) {
				q.offer(n.right);
			}
			
		}
		
	}
	
	/**
	 * @param g
	 * @param start
	 * @param end
	 * Given a directed graph, design an algorithm to find out whether there is a
route between two nodes.
	 */
	public static boolean search (Graph g, int start, int end) {
		if (g == null) {
			return false;
		}
		if (start == end)
			return true;
		int NON_VISITED = 0;
		int VISITING = 1;
		int VISITED = 2;
		Queue<Integer> q = new LinkedList<Integer>();
		int [] visited = new int[g.nVertices];
		q.offer(start);
		while (q.peek() != null) {
			int node = q.poll();
			for (Edge e : g.edges.get(node)) {
				if (visited[e.y] == NON_VISITED) {
					visited[e.y] = VISITING;
					if (e.y == end) {
						return true;
					}
					q.offer(e.y);
				}
			}
			visited[node] = VISITED;
		}
		return false;
		
	}
	
	public static void preOrderRecur(Node root) {
		if (root == null) {
			return;
		}
		System.out.println(root.getValue());
		if (root.left != null) {
			preOrderRecur(root.left);
		}
		if (root.right != null) {
			preOrderRecur(root.right);
		}
	}
	
	public static void preOrderIter(Node root) {
		//use stack
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		
		while(stack.peek() != null){
			Node n = stack.pop(); 
			System.out.println(n.getValue());
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				stack.push(root.left);
			}			
		}
	}
	
	/**
	 * @param array
	 * @param start
	 * @param end
	 * Given a sorted (increasing order) array, write an algorithm to create a binary
tree with minimal height.
	 */
	public static Node createBinaryTree(int [] array, int start, int end) {
		if (array == null)
			return null;
		if (end > start)
			return null;
		int mid = (start + end)/2;
		Node n = new Node(array[mid]);
		n.left = createBinaryTree(array, start, mid-1);
		n.right = createBinaryTree(array, mid+1, end);
		return n;		
	}
	
	/**
	 * @return boolean
	 * check if tree is balanced
	 */
	public static boolean isBalanced(Node n) {
		return (((maxDepth(n) - minDepth(n)) ==  1) || ((maxDepth(n) - minDepth(n)) ==  0)); 
	}
	
	public static int maxDepth(Node n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(n.left), maxDepth(n.right));
	}
	
	public static int minDepth(Node n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.min(maxDepth(n.left), maxDepth(n.right));
	}
	
	
	/**
	 * @param root
	 * @param p
	 * @param q
	 * @return common ancestor
	 * 
	 * If no parent link available in Node or not allowed data structure use in this question,
	 * we need to use this algo (modified DFS) to get common ancestor.
	 */
	public static Node commonAncestor(Node root, Node p, Node q) {
		if (root == null)
			return null;
		if (root == p || root == q)
			return root;
		Node leftNode = null;
		Node rightNode = null;
		if (root.left != null) {
			leftNode = commonAncestor(root.left, p, q);
		}
		if (root.right != null) {
			rightNode = commonAncestor(root.right, p, q);
		}
		
		if (leftNode == p || rightNode == p) {
			if (leftNode == q || rightNode == q) {
				return root; //find common ancestor
			}
		}
		
		return leftNode == null ? rightNode:leftNode;
	}
	
	public static Node commonAncestor2(Node root, Node a, Node b) {
		if (root == null || a == null || b == null) {
			return null;
		}
		
		if (root.equals(a) || root.equals(b))
			return root;
		Node left = commonAncestor(root.left, a , b);
		if (left!=null && !left.equals(a) && !left.equals(b)) //if common ancestor, return immediately
			return left;
		Node right = commonAncestor(root.right, a , b);
		if (left!=null&&right!=null){
			if (left.equals(a)&&right.equals(b))
				return root;
			if (left.equals(b)&&right.equals(a))
				return root;
		}
		return left == null ? right:left;
	}
	
	/**
	 * @param node
	 * @return Next Node
	 * Write an algorithm to find the ‘next’ node (eg, in-order successor) of a given
node in a binary search tree where each node has a link to its parent.
	 */
	
	public static Node nextNodeInOrder(Node node) {
		if (node == null)
			return null;
		Node p = null;
		
		//case 1: input node is the root
		//case 2: input node has right child
		
		if (node.parent == null || node.right != null) {
			p = leftMostChild(node.right);
		} 
		
		//case 3: input node is the left child
		//case 4: input node is the right child
		else {
			Node c = node;
			p = node.parent;
			while(p.left != c) {
				c = p;
				p = p.parent;				
			}
		}
		return p;
	}
	
	private static Node leftMostChild(Node node) {
		Node r = node;
		while(r.left != null) {
			r = r.left;
		}
		return r;
	}
	
	/**
	 * @param head
	 * @param sum
	 * @param buffer
	 * @param level
	 * You are given a binary tree in which each node contains a value. Design an
algorithm which prints all paths which sum up to that value. Note that it can
be any path in the tree - it does not have to start at the root.
		
	 * Ref: Careercup chapter 24
	 */
	public static void findSum(Node head, int sum, int [] buffer, int level) {
		if (head == null)
			return;
		int temp = sum;
		buffer[level] = head.getValue();
		
		for (int i = level; i >=0; i--) {
			temp -= buffer[i];
			if (temp == 0){
				printSumPath(buffer, level, i); //found
			}
		}
		
		findSum(head.left, sum, buffer, level+1);
		findSum(head.right, sum, buffer, level+1);
		
	}
	
	private static void printSumPath(int [] buffer, int start, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = start; i <= end; i++) {
			sb.append(buffer[i]).append("->");
		}
		System.out.println(sb.toString());
		
	}
	
	/**
	 * @param node
	 * @param n
	 * A binary tree contain integer values (both positive and negative) 
	 * in its data field. Given a number N, find a path from root to leaf which sums to N.
	 */
	public static Node containsSum(Node node, int n) {
		
		if (node == null) //not found			 
			return null;
		
		n = n - node.getValue();
		if (n == 0 && node.left == null && node.right == null) { //found a path to leaf which sums to N
			return node;
		} else {
			Node rt = containsSum(node.left, n);
			if (rt == null) {
				rt = containsSum(node.right, n);
			}
			return rt;
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


