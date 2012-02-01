package backup;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

/**
 * @author Angelo Kaichen Huang
 *
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 * 
 * This solution is using Dynamic Programming method
 * and reduce the puzzle to find the longest increasing subsequence
 * O(nlog(n))
 */
public class Gattaca1{
	private int dnaLen = 0;
	private int geneSize = 0;
	public int findMaxScore(String fileName) {
		Gene[] geneArr = readFile(fileName);
		if (geneArr == null) {
			System.out.println("Failed to read the file!!");
			return -1;
		}
		if (geneSize == 0) {
			System.out.println("Wrong Format!!");
			return -1;
		}			
		Arrays.sort(geneArr);
//		bucketSort(geneArr);
//		Queue<GeneNode> pq = new PriorityQueue<GeneNode>();
//		pq.add(new GeneNode(0, geneArr[0].score));
//		pq = findMax(geneArr, pq);
//		return pq.poll().maxScore;
		
		List<GeneNode> list = new ArrayList<GeneNode>();
		list.add(new GeneNode(0, geneArr[0].score));
		list = findMax2(geneArr,list);
		return list.get(0).maxScore;
		
//		return findMax3(geneArr);
		
//		return findMax4(geneArr);
		
	}
	/**
	 * @param geneList
	 * @param score
	 * @param index
	 * 
	 * Algorithm to find the maximum value of genes
	 */
/*	private Queue<GeneNode> findMax(Gene[] geneArr, Queue<GeneNode> pq){		
		if (geneSize == 0) {
			return null;
		}		
		int maxSubScore = Integer.MIN_VALUE;
		int maxSubIndex = -1;
		Queue<GeneNode> newPQ;
		for (int i = 1; i < geneSize; i++) {			
			newPQ = new PriorityQueue<GeneNode>();
			while(pq.peek()!=null) {
				GeneNode subMax = pq.poll();
				newPQ.add(subMax);
				if (geneArr[subMax.index].end < geneArr[i].start){					
					maxSubIndex = subMax.index;
					maxSubScore = subMax.maxScore;
					break;
				}
			}
			
			int curMax;
			if (maxSubIndex > -1) {
				curMax = maxSubScore + geneArr[i].score;				
			} else {
				curMax = geneArr[i].score;			
			}

			newPQ.add(new GeneNode(i, curMax));
			pq = newPQ;
		}	
		return pq;
	}*/
	
	private List<GeneNode> findMax2(Gene[] geneArr, List<GeneNode> list){		
		if (geneSize == 0) {
			return null;
		}		
		int maxSubScore = Integer.MIN_VALUE;
		int maxSubIndex = -1;	
		for (int i = 1; i < geneSize; i++) {
			int sub = 0;
			Iterator<GeneNode> it = list.iterator();
			while(it.hasNext()) {
				GeneNode subMax = it.next();				
				sub++;
				if (geneArr[subMax.index].end < geneArr[i].start){					
					maxSubIndex = subMax.index;
					maxSubScore = subMax.maxScore;
					break;
				}				
			}						
			int curMax;
			if (maxSubIndex > -1) {
				curMax = maxSubScore + geneArr[i].score;				
			} else {
				curMax = geneArr[i].score;			
			}			
			list.subList(sub, list.size()).clear();
			list.add(new GeneNode(i, curMax));
			Collections.sort(list);			
		}	
		return list;
	}
	
/*	private int findMax3(Gene[] geneArr){		
		if (geneSize == 0) {
			return 0;
		}
		int [] bestScore = new int [geneSize];
		bestScore[0] = geneArr[0].score;
		int preSubBest = 0;                            
		int maxScore = Integer.MIN_VALUE;
			
		for (int i = 1; i < geneSize; i++) {
			int maxSubScore = Integer.MIN_VALUE;
			int maxSubIndex = -1;
			for (int j = i-1; j >= preSubBest; j--) {
				if (geneArr[j].end < geneArr[i].start){					
					if (bestScore[j] > maxSubScore){
						maxSubIndex = j;
						maxSubScore = bestScore[j];
					}					
				}
			}		
							
			int curMax;
			if (maxSubIndex > -1) {
				curMax = maxSubScore + geneArr[i].score;
				preSubBest = maxSubIndex;
			} else {
				curMax = geneArr[i].score;			
			}			
			bestScore[i] = curMax;
			if (curMax>maxScore)
				maxScore = curMax;
		}	
		return maxScore;
	}*/
	
/*	private int findMax4(Gene[] geneArr){		
		if (geneSize == 0) {
			return 0;
		}
		int [] bestScore = new int [geneSize];
		bestScore[0] = geneArr[0].score;		                          
		int maxScore = Integer.MIN_VALUE;
			
		for (int i = 1; i < geneSize; i++) {
			int maxSubScore = Integer.MIN_VALUE;
			int maxSubIndex = -1;
			int beforeStart = binarySearch(geneArr, geneArr[i].start);
			for (int j = beforeStart; j >= 0; j--) {
				if (bestScore[j] > maxSubScore){
					maxSubIndex = j;
					maxSubScore = bestScore[j];
				}				
			}		
							
			int curMax;
			if (maxSubIndex > -1) {
				curMax = maxSubScore + geneArr[i].score;				
			} else {
				curMax = geneArr[i].score;			
			}			
			bestScore[i] = curMax;
			if (curMax>maxScore)
				maxScore = curMax;
		}	
		return maxScore;
	}*/
	
/*	private int binarySearch(Gene[] arr, int start) {
		int left = 0, right = 0, mid = 0;
		right = arr.length-1;
		if (arr[right].end<start){
			return right;
		}
		if (arr[left].end>=start){
			return -1;
		}
		while(left<=right) {
			mid = (left + right)/2;
			if (arr[mid].end > start) {
				right = mid - 1;				
			} else if (arr[mid].end < start) {
				left = mid + 1;			
			} else {
				return mid-1; 
			}
		}
		return right;
	}*/
	
	private Gene parseGene(String line, int dnaLen) throws Exception{
		String [] arr = line.split("\\s+");
		if (arr.length < 3)
			return null;
		int start = 0;
		int end = 0;
		int score = 0;
		for (int i = 0; i < 3; i++) {
			int t = Integer.parseInt(arr[i]);
			switch(i){
				case 0:
					start = t;
					break;
				case 1:
					end = t;
					break;
				case 2:
					score = t;								
			}
		}
//		if (start < 0 || end >= dnaLen || score < 0)
//			throw new Exception("Gene's data is wrong!!");
		
		return new Gene(start, end, score);
	}
	
	private Gene[] readFile(String fileName) {
		if (fileName == null)
			return null;
		Gene[] geneArr = null;
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine = br.readLine();			
			if (currLine == null) {
				System.out.println("Input file format is wrong!!");				
			} else {
				dnaLen = Integer.parseInt(currLine);
			}
			int totalLinesToSkip = (dnaLen%80==0)?(dnaLen/80)+1:(dnaLen/80)+2;
			int i = 0;
			
			while(i < totalLinesToSkip && (currLine = br.readLine())!=null) {				
				i++;
			}
			if (currLine == null) {
				System.out.println("Input file format is wrong!!");
			} else {
				geneSize = Integer.parseInt(currLine);
			}
			int idx = 0;
			geneArr = new Gene[geneSize];
			while((currLine = br.readLine())!=null) {
				geneArr[idx] = parseGene(currLine, dnaLen);
				idx++;
			}
		
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return geneArr;
	}
	
	public void bucketSort(Gene[] geneArr) {
		//ArrayList[] buckets = new ArrayList[dnaLen]; //lazy initialization
		ArrayList[] buckets = (ArrayList[])Array.newInstance(ArrayList.class, dnaLen);
		for (Gene g : geneArr) {
			if (buckets[g.start] == null) {
				buckets[g.start] = new ArrayList();
			}
			buckets[g.start].add(g);
		}
		int i = 0;
		for (ArrayList b : buckets) {
			if (b != null) {
				Iterator it = b.iterator();
				while(it.hasNext()) {
					geneArr[i++] = (Gene)it.next();  
				}
			}
		}		
	}
	
/*	private static ArrayList<Gene>[] fillBuckets(ArrayList<Gene>[] b) {
		for (int i = 0; i < b.length; i++) {
			b[i] = new ArrayList<Gene>();
		}
	}*/
	
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		Gattaca1 gattaca = new Gattaca1();
		int maxScore = gattaca.findMaxScore(fileName);
//		int maxScore = gattaca.findMaxScore("bin/100k.in");
//		int maxScore = gattaca.findMaxScore("bin/10k.in");
//		int maxScore = gattaca.findMaxScore("bin/1mm.in");
//		int maxScore = gattaca.findMaxScore("bin/Gattaca_Input");
		System.out.print(maxScore+"\n");
	}
}


/*class Gene implements Comparable<Gene>{
	final int start;
	final int end;
	final int score;
	public Gene(int s, int e, int sc){
		start = s;
		end = e;
		score = sc;
	}

	public int compareTo(Gene o) {
		if (start == o.start)
			return 0;
		else if (start < o.start)
			return -1;
		else
			return 1;
	}
}*/

/*class Gene implements Comparable<Gene>{
	final int start;
	final int end;
	final int score;
	public Gene(int s, int e, int sc){
		start = s;
		end = e;
		score = sc;
	}

	public int compareTo(Gene o) {
		if (end == o.end)
			return 0;
		else if (end < o.end)
			return -1;
		else
			return 1;
	}
}*/

/*class GeneNode implements Comparable<GeneNode>{
	final int index;	
	final int maxScore;	
	public GeneNode(int i, int s){
		index = i;
		maxScore = s;
	}	
	public int compareTo(GeneNode o) {	
		//descending sort
		if (maxScore < o.maxScore)
			return 1;
		else if (maxScore > o.maxScore)
			return -1;
		else return 0;
	}
}*/


