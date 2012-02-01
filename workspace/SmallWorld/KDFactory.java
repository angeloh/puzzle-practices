import java.util.*;

/**
 * @author ahuang
 *	Ref: http://www.codeproject.com/KB/architecture/KDTree.aspx
 *  KDfactory is factory for creating KDTree object.
 */
public class KDFactory {
	
	private static final KDFactory kdf = new KDFactory();
	
	private KDFactory() {		
	}
	
	public static KDFactory getInstance(){		
		return kdf;			
	}
	//construct KDTree
	public KDTree create(int dimension, List<Point> points) {
		KDTree tree = new KDTree(dimension);		
		for (Point point: points) {
			tree.add(point.coords, point.id);
		}
		return tree;
	}
}

/**
 * Class for KDTree
 *
 */
class KDTree{
	public final int dimension;
	public int kNearest = 1;
	KDNode root;
	List<KDNode> checkedNodes = null;
	
	protected KDTree(int ds){
		dimension = ds;
	}
	
	public boolean add(double [] cs, int id) {
		if (cs == null || cs.length != dimension ||id < 0)
			return false;
		if(root == null) {
			root = new KDNode(this, cs, 0, id);
		} else {
			root.insert(cs, id);
		}
		return true;
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
		KDNode parent = root.findParent(point.coords);		
		double minDist = distance(point.coords, parent.getCoords());
		final List<KDNodeProxy> minNodes = new ArrayList<KDNodeProxy>();
		minNodes.add(new KDNodeProxy(parent, minDist));		
		checkedNodes = new ArrayList<KDNode>();
		checkSubTree(root, point.coords, minNodes);		
		uncheckedAllNotes();

		int [] ids = new int[minNodes.size()];		
		for (int i = 0; i < minNodes.size(); i++) {
			ids[i] = minNodes.get(i).getKdNode().id;
		}
		return ids;
	}
	
	private void checkSubTree(KDNode node, double [] cs, List<KDNodeProxy> minNodes) {
		if (node==null || node.isChecked()) {
			return;
		}
		node.setChecked(true);
		checkedNodes.add(node);
		checkNode(node, cs, minNodes);
		int axis = node.getAxis();
		double distSq = node.getCoords()[axis] - cs[axis];
		distSq*=distSq;
		if(Double.compare(distSq, getLastMinDist(minNodes)) > 0) { //only need to check in one direction
			if (node.getCoords()[axis] > cs[axis]){
				checkSubTree(node.getLeft(), cs, minNodes);
			} else {
				checkSubTree(node.getRight(), cs, minNodes);
			}
		} else {
			checkSubTree(node.getLeft(), cs, minNodes);
			checkSubTree(node.getRight(), cs, minNodes);
		}		
	}
	
	private void checkNode(KDNode node, double [] cs, List<KDNodeProxy> minNodes) {		
		double d = 0;
		for (int i = 0; i < dimension; i++) {
			double dx = node.getCoords()[i] - cs[i]; 
			d += dx*dx;
		}
		KDNodeProxy newKDN = null;
		if (minNodes.size() < kNearest) {
			newKDN = new KDNodeProxy(node, d);
			if (!minNodes.contains(newKDN)) {
				minNodes.add(newKDN);			
				Collections.sort(minNodes);
			}
		} else {
			if (Double.compare(getLastMinDist(minNodes),d) > 0) {
				newKDN = new KDNodeProxy(node, d);
				// avoiding inserting same node when target node existed in tree
				if (!minNodes.contains(newKDN)) {
					minNodes.remove(kNearest-1);
					minNodes.add(newKDN);
					Collections.sort(minNodes);
				}
			}
		}
	}
	
	public double getLastMinDist (List<KDNodeProxy> minNodes){
		if (minNodes.isEmpty()) {
			return 0.0;
		}
		return minNodes.get(minNodes.size()-1).getDistToTarget();
	}
	
	private void uncheckedAllNotes() {
		for (KDNode kdn : checkedNodes){
			kdn.setChecked(false);
		}
	}
	
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * @param cos1
	 * @param cos2
	 * @return double
	 * Calculate distance for two nodes in K dimensions
	 */
	public static double distance(double [] cos1, double [] cos2) {
		if (cos1.length != cos2.length) {
			return 0;
		}
		double distSquare = 0;
		for (int i = 0; i < cos1.length; i++) {
			distSquare+=((cos1[i]-cos2[i])*(cos1[i]-cos2[i]));
		}
		return distSquare;
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
	boolean checked;
	KDTree kdt = null;
	KDNode left = null;	
	KDNode right = null;
	KDNode parent = null;

	protected KDNode (KDTree tree, double [] cs, int ax, int d) {
		kdt = tree;
		coords = new double [kdt.getDimension()];
		for (int i = 0; i < kdt.getDimension(); i++) {
			coords[i] = cs[i];
		}		
		axis = ax;
		id = d;
	}
	
	public KDNode findParent(double [] cs) {
		if (Arrays.equals(getCoords(), cs)){
			return this;
		}
		KDNode parnt = null;
		KDNode next = this;
		int split;
		while(next != null) {
			split = next.getAxis();
			parnt = next;
			if (cs[split] > next.getCoords()[split]){
				next = next.getRight();
			} else {
				next = next.getLeft();
			}
		}
		return parnt;
	}
	
	public KDNode insert(double [] cs, int id) {
		KDNode parnt = findParent(cs);
		if (Arrays.equals(parnt.getCoords(), cs)){
			return null;
		}
		int curAxis = (parnt.getAxis()+1)<kdt.getDimension()?parnt.getAxis()+1:0;
		KDNode newNode = new KDNode(kdt, cs, curAxis, id);
		newNode.setParent(parnt);
		if(cs[parnt.getAxis()] > parnt.getCoords()[parnt.getAxis()]){
			parnt.setRight(newNode);
		} else {
			parnt.setLeft(newNode);
		}
		return newNode;		
	}
	
	public KDTree getKdt() {
		return kdt;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAxis() {
		return axis;
	}

	public double[] getCoords() {
		return coords;
	}
	
	public KDNode getLeft() {
		return left;
	}
	
	public KDNode getRight() {
		return right;
	}
	
	public KDNode getParent() {
		return parent;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setLeft(KDNode left) {
		this.left = left;
	}
	
	public void setRight(KDNode right) {
		this.right = right;
	}

	public void setParent(KDNode p) {
		parent = p;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
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