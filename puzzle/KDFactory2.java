import java.util.*;

/**
 * @author ahuang
 *	Ref: http://www.codeproject.com/KB/architecture/KDTree.aspx
 *  KDfactory is factory for creating KDTree object.
 */
public class KDFactory2 {
	
	private static final KDFactory2 kdf = new KDFactory2();
	
	private KDFactory2() {		
	}
	
	public static KDFactory2 getInstance(){		 
		return kdf;			
	}
	//construct KDTree
	public KDTree create(List<Point> points) {
		if (points == null || points.isEmpty()) {
			return null;
		}		
		KDTree tree = new KDTree();		
		Point [] pointArr = points.toArray(new Point[points.size()]);
		tree.addPoints(pointArr);		
		return tree;
	}
}

/**
 * Class for KDTree
 *
 */
class KDTree{	
	int kNearest = 1;
	KDNode root;

	protected KDTree(){		
	}	
	
	public boolean addPoints(Point [] pointArr) {
		if (pointArr == null || pointArr.length == 0)
			return false;
		
		root = buildTree(pointArr, 0, pointArr.length-1, 0);		
		return true;
	}
	
	public KDNode buildTree(Point [] pointArr, int start, int end, int depth){
		if (start>end)
			return null;
		int med = (start+end)/2;
		final int axis = depth%2;
		//The range to be sorted extends from index fromIndex, inclusive, to index toIndex, exclusive.
		Arrays.sort(pointArr, start, end+1, new Comparator<Point>(){
			public int compare(Point a, Point b) {
				if (a.coords[axis] < b.coords[axis])
					return -1;
				if (a.coords[axis] > b.coords[axis])
					return 1;
				return 0;
			}
		});		
		
		KDNode kd = new KDNode(pointArr[med].coords, axis, pointArr[med].id);		
		kd.left = buildTree(pointArr, start, med-1, depth+1);
		kd.right = buildTree(pointArr, med+1, end, depth+1);
		return kd;
	}
	
	/**
	 * @param double [] cs
	 * @param int k
	 * @return int []
	 * supported k-nearest-neighbor search 
	 */
	public int [] findNearest(Point point, int k) {
		if (root == null)
			return null;
		kNearest = k;
		final List<KDNodeProxy> minNodes = new ArrayList<KDNodeProxy>();
		checkSubTree(root, point.coords, minNodes);
		int [] ids = new int[minNodes.size()];		
		for (int i = 0; i < minNodes.size(); i++) {
			ids[i] = minNodes.get(i).getKdNode().id;
		}
		return ids;
	}
	
	private void checkSubTree(KDNode node, double [] cs, List<KDNodeProxy> minNodes) {
		
		if (node.left == null && node.right == null) {
			checkNode(node, cs, minNodes);
			return;
		}
		
		int axis = node.axis;
		double distSq = node.coords[axis] - cs[axis];
		distSq*=distSq;
		KDNode nearer = null;
		KDNode further = null;		
		if (node.right == null || (node.left != null && cs[axis] <= node.coords[axis])){
			nearer = node.left;
			further = node.right;
		} else {
			nearer = node.right;
			further = node.left;
		}
		checkSubTree(nearer, cs, minNodes);
		
		if (further!=null) {
			if (minNodes.size() < kNearest || Double.compare(distSq, getLastMinDist(minNodes)) < 0) { //check in both directions
				checkSubTree(further, cs, minNodes);
			}
		}
		
		checkNode(node, cs, minNodes);
	}
	
	private void checkNode(KDNode node, double [] cs, List<KDNodeProxy> minNodes) {		
					
		double d = distance(node.coords, cs);
		
		KDNodeProxy newKDN = null;
		if (minNodes.size() < kNearest) {
			newKDN = new KDNodeProxy(node, d);
			minNodes.add(newKDN);
			Collections.sort(minNodes);
		} else {
			if (Double.compare(minNodes.get(3).distToTarget,d) > 0) {
				newKDN = new KDNodeProxy(node, d);
				minNodes.remove(kNearest-1);
				minNodes.add(newKDN);
				Collections.sort(minNodes);
			}
		}
	}
	
	public double getLastMinDist (List<KDNodeProxy> minNodes){
		if (minNodes.isEmpty()) {
			return 0.0;
		}
		return minNodes.get(minNodes.size()-1).distToTarget;
	}

	/**
	 * @param cos1
	 * @param cos2
	 * @return double
	 * Calculate distance for two nodes in K dimensions
	 */
	public static double distance(double [] cos1, double [] cos2) {
		double a = cos1[0] - cos2[0];
		double b = cos1[1] - cos2[1];				
		double d = a*a + b*b;
		return d;
	}
	
}

/**
 * Create this class to implement Comparable
 * for sorting by distToTarget
 *
 */
class KDNodeProxy implements Comparable<KDNodeProxy>{
	final KDNode kdNode;
	double distToTarget = 0; //distance to target
	protected KDNodeProxy(KDNode node, double dist) {
		kdNode = node;
		distToTarget = dist;
	}
	public double getDistToTarget() {
		return distToTarget;
	}
	public void setDistToTarget(double distToTarget) {
		this.distToTarget = distToTarget;
	}
	public KDNode getKdNode() {
		return kdNode;
	}
	public int compareTo(KDNodeProxy kdn) {
		if (this == kdn) return 0;
		
		if (this.distToTarget < kdn.getDistToTarget()) {
			return -1;
		}
		if (this.distToTarget > kdn.getDistToTarget()) {
			return 1;
		}
		return 0;
	}
	public boolean equals (Object o) {
		if (!(o instanceof KDNodeProxy))
			return false;
		KDNodeProxy kdn = (KDNodeProxy)o;
		if (this.getKdNode() == kdn.getKdNode() &&
				this.getDistToTarget() == kdn.getDistToTarget())
			return true;
		return false;
	}	
}


/**
 * Class for tree node
 *
 */
class KDNode {
	final int id;	
	final int axis;
	final double [] coords;
	KDNode left = null;	
	KDNode right = null;	

	protected KDNode (double [] cs, int ax, int d) {		
		coords = new double [2];
		coords[0] = cs[0];
		coords[1] = cs[1];
		axis = ax;
		id = d;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof KDNode)) {
			return false;
		}
		KDNode kdo = (KDNode)o;
		if (Arrays.equals(this.coords, kdo.coords)) {
			return true;
		}
		return false;
	}
	
}

class Point {	
	final int id;
	final double [] coords;
	public Point(double [] cs, int d) {
		id = d;
		coords = cs;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Point)) {
			return false;
		}
		Point point = (Point)o;
		if (Arrays.equals(this.coords, point.coords) && this.id == point.id) {
			return true;
		}
		return false;
	}
}