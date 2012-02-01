import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Angelo Kaichen Huang
 * Main class for reading the input file and then invoking
 * FaceBullImpl to find the solution.
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=1
 */
public class FaceBull{	
	//Maps that convert between pair instance and machine number
	final Map<Integer, Pair> pairMap = new HashMap<Integer, Pair>();
	final Map<Pair, Integer> pairReverse = new HashMap<Pair, Integer>();
	final Set<String> setS = new HashSet<String>();
	final Set<String> setT = new HashSet<String>();
	
	private boolean readFile(String fileName) {
		if (fileName == null)
			return false;
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine;			
			while((currLine = br.readLine())!=null) {
				parsePair(currLine);
			}
		
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void parsePair(String line){
		String [] arr = line.split("\\s+");
		if (arr.length < 4)
			return;
		int index = Integer.parseInt(arr[0].substring(1));
		String s = arr[1] + "s";
		String t = arr[2] + "t";
		setS.add(s);
		setT.add(t);
		int cost = Integer.parseInt(arr[3]);
		Pair p = new Pair(s, t, cost);
		pairMap.put(index, p);
		pairReverse.put(p, index);	
	}

	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
//		String fileName = "bin/FaceBull_Input";
		FaceBull fb = new FaceBull();
		if (fb.readFile(fileName)) {
			FaceBullImpl fbi = new FaceBullImpl(fb.setS, fb.setT, fb.pairMap);
			Iterator<Pair> itp = fbi.compute();
			StringBuilder sb = new StringBuilder();
			int totalCost = 0;
			while(itp.hasNext()) {
				Pair p = itp.next();
				int idx = fb.pairReverse.get(p);
				sb.append(idx);				
				if (itp.hasNext())
					sb.append(" ");
				totalCost+=fb.pairMap.get(idx).cost;
			}
			System.out.println(totalCost);
			System.out.println(sb.toString());
		} else {
			System.out.println("Please provide a vaild file!!");
		}
		
	}
	
}

/**
 * Transform the input edges and nodes to flow network
 * and invoking FordFulkerson with the network object
 * to compute maximum flow with minimum cost. 
 *	
 */
class FaceBullImpl {
	final ArrayList<EdgeInfo> edges = new ArrayList<EdgeInfo>();
	int ctr = 0; //id counter
	
	//Maps that convert between pair instance and machine number
	final Map<Integer, Pair> pairMap;
	
	//Maps that convert between node instances and assigned index
	final Map<String, Integer> map = new HashMap<String, Integer>();
	final Map<Integer, String> reverse = new HashMap<Integer, String>();
	
	final int srcIndex;	
	final int tgtIndex;
	final int numVertices;
	
	public FaceBullImpl(Set<String> setS, Set<String> setT, Map<Integer, Pair> pMap){		
		pairMap = pMap;	
		
		Iterator<Pair> it = pairMap.values().iterator();
		while(it.hasNext()) {
			Pair p = it.next();
			Integer src = map.get(p.start);
			Integer tgt = map.get(p.end);
			
			if (src == null) {
				map.put(p.start, src = ++ctr);
				reverse.put(src, p.start);
			}
			if (tgt == null) {
				map.put(p.end, tgt = ++ctr);
				reverse.put(tgt, p.end);
			}			
			edges.add(new EdgeInfo(src, tgt, 1, p.cost));
		}		
				
		//add extra source and target vertices for flow network
		srcIndex = 0;
		tgtIndex = setS.size()+setT.size()+1;
		numVertices = tgtIndex+1;
		for (String s: setS) {
			edges.add(new EdgeInfo (0, map.get(s), 1, 1));
		}
		for (String t: setT) {
			edges.add(new EdgeInfo (map.get(t), tgtIndex, 1, 1));
		}
	}
	
	public Iterator<Pair> compute() {
		FlowNetworkArray network = new FlowNetworkArray(numVertices, srcIndex, tgtIndex, edges.iterator());
		FordFulkerson solver = new FordFulkerson(network, new DFS_SearchArray(network));
		solver.compute(srcIndex, tgtIndex);
		
		List<Pair> pairs = new ArrayList<Pair>();
		for (EdgeInfo ei: edges) {
			if (ei.start != srcIndex && ei.end != tgtIndex) {
				if (ei.getFlow() == 1) {
					pairs.add(new Pair(reverse.get(ei.start), reverse.get(ei.end)));
				}
			}
		}
		return pairs.iterator();
	}
}

/**
 * Ref: Algorithms in a nutshell O'REILLY 2009
 * Solve this puzzle using FordFulkerson for maximum flow 
 */
class FordFulkerson {
	FlowNetwork network;
	Search searchMethod;
	
	public FordFulkerson (FlowNetwork fn, Search s) {
		network = fn;
		searchMethod = s;
	}
	
	public boolean compute (int source, int sink) {
		boolean augmented = false;
		while(searchMethod.findAugmentingPath(network.vertices)){
			processPath(network.vertices);
			augmented = true;
		}
		return augmented;
	}
	
	/**
	 * @param vertices
	 * If there is augmented path, the path will be adjusted here
	 */
	protected void processPath(VertexInfo[] vertices) {
		int v = network.sinkIndex;
		int delta = Integer.MAX_VALUE;
		//find the smallest augmented amount
		while(v != network.sourceIndex) {
			int u = vertices[v].previous;
			int flow;
			if(vertices[v].forward) {
				flow  = network.edge(u, v).capacity - network.edge(u, v).flow;				
			} else {
				flow = network.edge(v, u).flow;				
			}
			if(flow < delta) {
				delta= flow;
			}
			v = u;
		}
		//adjust path
		v = network.sinkIndex;
		while(v != network.sourceIndex) {
			int u = vertices[v].previous;
			if(vertices[v].forward) {
				network.edge(u, v).flow += delta;
			} else {
				network.edge(v, u).flow -= delta;
			}
			v = u;
		}
		Arrays.fill(network.vertices, null);
		
	}	
}

/**
 * common interface for searching path in graph 
 *
 */
interface Search {
	static final boolean FORWARD = true;
	static final boolean BACKWARD = false;
	public boolean findAugmentingPath(VertexInfo[] vertices);
}

/**
 * Use DFS to augment the flow network and PriorityQueue to keep
 * exploring the path from current node with minimum cost
 * 
 *
 */
class DFS_SearchArray implements Search{
	FlowNetwork network;
	public DFS_SearchArray (FlowNetwork fn) {
		network = fn;
	}
	
	/**
	 * @see Search#findAugmentingPath(VertexInfo[])
	 * To search minimum cost for network flow, PriorityQueue is used
	 * here to acquire current node with minimum cost in each step. 
	 */
	public boolean findAugmentingPath(VertexInfo[] vertices) {
		Arrays.fill(vertices, null);
		
		int n = vertices.length;
		Queue<CostNode> pq = new PriorityQueue<CostNode>(n);
		CostNode inQueue[] = new CostNode [n];
		int [] dist = new int [n];
		for (int u = 0; u < n; u++) {
			if (u == network.sourceIndex) {
				dist[u] = 0;
				//add to pq
				CostNode c = new CostNode(u, 0);
				pq.offer(c);
				inQueue[u] = c;
			} else {
				dist[u] = Integer.MAX_VALUE;
			}
		}
		
		while (!pq.isEmpty()) {
			CostNode uNode = pq.poll();
			int u = uNode.index;
			inQueue[u] = null;
			
			if(u == network.sinkIndex) {
				break;
			}
			
			for (int v = 0; v < n; v++) {
				if (v == network.sourceIndex || v == u) {
					continue;
				}
				
				//forward edges
				EdgeInfo cei = network.edge(u, v);				
				if (cei != null && cei.flow < cei.capacity) {
					int newDist = dist[u] + cei.cost;
					if (newDist >= 0 && newDist < dist[v]) {
						vertices[v] = new VertexInfo(u, Search.FORWARD);
						dist[v] = newDist;
						if (inQueue[v]!=null) {
							CostNode c = inQueue[v];
							c.setCurCost(newDist);
							pq.remove(c);
							pq.offer(c);
						} else {
							CostNode c = new CostNode(v, newDist);
							pq.offer(c);
							inQueue[v] = c;
						}
					}
				}
				
				//backward edges				
				cei = network.edge(v, u);				
				if (cei != null && cei.flow > 0) {
					int newDist = dist[u] - cei.cost;
					if (newDist >= 0 && newDist < dist[v]) {
						vertices[v] = new VertexInfo(u, Search.BACKWARD);
						dist[v] = newDist;
						if (inQueue[v]!=null) {
							CostNode c = inQueue[v];
							c.setCurCost(newDist);
							pq.remove(c);
							pq.offer(c);
						} else {
							CostNode c = new CostNode(v, newDist);
							pq.offer(c);
							inQueue[v] = c;
						}
					}
				}
				
			}
		}		
		return dist[network.sinkIndex] != Integer.MAX_VALUE;
	}
}

abstract class FlowNetwork{
	final int sourceIndex;
	final int sinkIndex;
	final int numVertices;
	final VertexInfo[] vertices;
	public FlowNetwork(int n , int s, int t) {
		numVertices = n;
		sourceIndex = s;
		sinkIndex = t;
		vertices = new VertexInfo[numVertices];
	}
	
	public abstract EdgeInfo edge(int u, int v);
}

/**
 * Class to represent a flow network
 *
 */
class FlowNetworkArray extends FlowNetwork{
	final EdgeInfo[][] info;
	
	public FlowNetworkArray(int n , int s, int t, Iterator<EdgeInfo> edges) {
		super(n ,s ,t);
		info = new EdgeInfo[n][n];
		while(edges.hasNext()) {
			EdgeInfo ei = edges.next();
			info[ei.start][ei.end] = ei;
		}
	}
	
	public EdgeInfo[][] getEdegeStructure() {
		return info;
	}
	
	public EdgeInfo edge(int u, int v) {
		return info[u][v];
	}
}


/**
 * Class for CostNode used in PriorityQueue 
 * which requires implementing Comparable
 *
 */
class CostNode implements Comparable<CostNode>{
	final int index;
	int	curCost;
	
	public CostNode(int i , int c) {
		index = i;
		curCost = c;
	}	
	
	public int compareTo(CostNode cn) {
		if (this.curCost < cn.curCost) {
			return -1;
		} else if (this.curCost > cn.curCost){
			return 1;
		}
		return 0;
	}

	public int getCurCost() {
		return curCost;
	}

	public void setCurCost(int curCost) {
		this.curCost = curCost;
	}
}

/**
 * Class for Vertex
 *
 */
class VertexInfo{
	final int previous;
	final boolean forward;
	public VertexInfo(int prev, boolean f){
		previous = prev;
		forward = f;
	}
}

/**
 * Class for Edge
 *
 */
class EdgeInfo{
	final int start;
	final int end;
	final int capacity;
	final int cost;
	int flow;
	
	public EdgeInfo(int s, int e, int c, int co) {
		start = s;
		end = e;
		capacity = c;
		cost = co;
	}
	
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
}

/**
 * Class for Pair (Edge)
 * Overrides equals and hashCode for hashing.  
 */
class Pair {
	private static final int HASH_PRIME = 1000003;
	final String start;
	final String end;
	final int cost;
	public Pair(String s, String e, int c) {
		start = s;
		end = e;
		cost = c;
	}
	public Pair(String s, String e) {
		start = s;
		end = e;
		cost = 0;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Pair))
			return false;
		Pair op = (Pair)o;
		if (this.start.equals(op.start) && this.end.equals(op.end)){
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		int result = 0;
		result = HASH_PRIME * result + this.start.hashCode();
		result = HASH_PRIME * result + this.end.hashCode();
		return result;
	}
}

