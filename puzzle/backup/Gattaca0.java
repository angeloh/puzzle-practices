package backup;

import java.util.*;
import java.io.*;

/**
 * @author Angelo Kaichen Huang
 *
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 * 
 * This solution is using Dynamic Programming method
 * and reduce the puzzle to find the longest increasing subsequence
 */
public class Gattaca0{
	private int geneSize = 0;
	public int findMaxScore(String fileName) {
//		List<Gene> geneList = readFile(fileName);
		Gene[] geneArr = readFile(fileName);
		if (geneArr == null) {
			System.out.println("Failed to read the file!!");
			return -1;
		}
		if (geneSize == 0) {
			System.out.println("Wrong Format!!");
			return -1;
		}			
//		Collections.sort(geneList); // sorted by start position
		Arrays.sort(geneArr);
		Queue<GeneNode> pq = new PriorityQueue<GeneNode>();
//		pq.add(new GeneNode(0, geneList.get(0).score));
//		longestIncreasingSubseq(geneList, pq);
		pq.add(new GeneNode(0, geneArr[0].score));
		longestIncreasingSubseq(geneArr, pq);
		return pq.poll().maxScore;
	}
	/**
	 * @param geneList
	 * @param score
	 * @param index
	 * 
	 * Algorithm to find the longest increasing subsequence
	 */
	private void longestIncreasingSubseq(Gene[] geneArr, Queue<GeneNode> pq){		
		if (geneSize == 0) {
			return;
		}		
		int maxSubScore = Integer.MIN_VALUE;
		int maxSubIndex = -1;
		
		for (int i = 1; i < geneSize; i++) {
			List<GeneNode> tmpList = new ArrayList<GeneNode>();
			while(pq.peek()!=null) {
				GeneNode subMax = pq.poll();
				tmpList.add(subMax);
				if (geneArr[subMax.index].end < geneArr[i].start){					
					maxSubIndex = subMax.index;
					maxSubScore = subMax.maxScore;
					break;
				}
			}
			pq.addAll(tmpList); //put it back
			int curMax;
			if (maxSubIndex > -1) {
				curMax = maxSubScore + geneArr[i].score;				
			} else {
				curMax = geneArr[i].score;			
			}
			pq.add(new GeneNode(i, curMax));
		}
	}
	
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
		if (start < 0 || end >= dnaLen || score < 0)
			throw new Exception("Gene's data is wrong!!");
		
		return new Gene(start, end, score);
	}
	
	private Gene[] readFile(String fileName) {
		if (fileName == null)
			return null;
//		List<Gene> geneList = new ArrayList<Gene>();
		Gene[] geneArr = null;
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine = br.readLine();
			int dnaLen = 0;
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
//				geneList.add(parseGene(currLine, dnaLen));
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
//		return geneList;
		return geneArr;
	}	
	
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		Gattaca0 gattaca = new Gattaca0();
		int maxScore = gattaca.findMaxScore(fileName);
//		int maxScore = gattaca.findMaxScore("bin/100k.in");
//		int maxScore = gattaca.findMaxScore("bin/10k.in");
//		int maxScore = gattaca.findMaxScore("bin/1mm.in");
//		int maxScore = gattaca.findMaxScore("bin/Gattaca_Input");
		System.out.print(maxScore+"\n");
	}
}