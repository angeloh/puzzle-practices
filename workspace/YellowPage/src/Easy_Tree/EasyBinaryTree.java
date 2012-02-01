package Easy_Tree;

import BFS.pBreadthFirstTraversal;

public class EasyBinaryTree {
	
	public EasyNode[] adjList;
	public boolean[] visited;
	public int totalVertex;
	
	public int[] quene;
	public int[] front;
	public int[] rear;
	
	public EasyBinaryTree() {
		build_adjList();
		
	}
	public void build_adjList() {
		int[][] vexArray = {{0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
		{1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},      
		{0, 1, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
		{0, 0, 1, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}};
		
		totalVertex = 10;
		visited = new boolean[totalVertex];
		adjList = new EasyNode[totalVertex];
		EasyNode lastNode;
		for (int i= 0; i < totalVertex; i++) {
			adjList[i] = new EasyNode(i);
			visited[i] = false;
			adjList[i].setNext(null);		
		}
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (vexArray[x][y]!=0) {
					EasyNode en = new EasyNode(y);
					en.setNext(null);
					lastNode = searchLast(adjList[x]);
					lastNode.setNext(en);
				}			
			}		
		}		
	}
	
	public EasyNode searchLast(EasyNode tmpNode){
		EasyNode lastNode = tmpNode;
		
		while (lastNode.getNext() != null) {
			lastNode = lastNode.getNext();
		}
		
		return lastNode;		
	}
	
	public static void main(String[] args) {
		
	}
}