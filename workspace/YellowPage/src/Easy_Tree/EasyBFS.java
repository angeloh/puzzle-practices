package Easy_Tree;

public class EasyBFS {
	
	public static int[] quene;
	public static int front = 0;
	public static int rear = 0;
	
	public EasyBFS() {
		quene = new int[100];
	}	
	
	public static void printBreadthFirst(EasyBinaryTree tree) {
		EasyNode tmpNode;
		int currIndex = 0;
		int w;
		System.out.println("V"+ tree.adjList[currIndex].getValue());
		while(true) {
			tree.visited[currIndex] = true;
			tmpNode = tree.adjList[currIndex].getNext();
			
			while (tmpNode != null) {
				w = tmpNode.getValue();
				
				if(!tree.visited[w]) {
					enQuene(w);
					System.out.println("V"+ w);
					tree.visited[w] = true;
				}
				tmpNode = tmpNode.getNext();
			}
			if (emptyQuene()) {
				return;
			} else {
				currIndex = deQuene();
			}		
		}
	}
	
	public static void enQuene(int v) {
		if (rear >= 100) {
			System.out.println("Quene is full");
		} else {
			quene[rear++] = v;
		}	
	}
	
	public static int deQuene() {
		if (front == rear) {
			System.out.println("Quene is empty");
			return 0;
		} else {
			return quene[front++];
		}
	}
	
	public static boolean emptyQuene() {		
		if (front == rear) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		EasyBinaryTree easyTree = new EasyBinaryTree();
		EasyBFS eBFS = new EasyBFS();
		eBFS.printBreadthFirst(easyTree);
	}
}