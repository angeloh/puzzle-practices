import java.util.*;
import java.io.*;

/**
 * @author Angelo Kaichen Huang
 *
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 * http://www.cs.princeton.edu/introcs/96optimization/Knapsack.java.html
 * This solution is using Dynamic Programming method
 * and reduce the puzzle to find the max value of genes' covering
 * O(nlog(n))
 */
public class Gattaca{
	private int dnaLen = 0;
	private int geneSize = 0;
	public int findMaxScore(String fileName) {
		Gene[] geneArr = readFile(fileName);				
		Arrays.sort(geneArr);
		return  binPack(geneArr);
	}
	/**
	 * @param geneList
	 * @param score
	 * @param index
	 * 
	 * Algorithm for knapsack
	 */	
	private int binPack(Gene[] gene){
		int [] sol = new int[dnaLen+1];				
		for (int i = 0; i < gene[0].end; i++)
			sol[i] = 0;
		sol[gene[0].end] = gene[0].score;
		int maxScore = Integer.MIN_VALUE;
		int preEnd = gene[0].end;
		for (int d = 1; d < geneSize; d++) {
			int curEnd = gene[d].end;
			if (preEnd<curEnd) {
				for (int i = preEnd+1; i < curEnd; i++) {
					sol[i] = sol[preEnd];
				}
			}
			int curTake = sol[curEnd-1];
			if (preEnd == curEnd && sol[curEnd]>curTake)
				curTake = sol[curEnd];
			int befStart = gene[d].start-1;
			int take = befStart > -1 ? gene[d].score + sol[befStart]: gene[d].score;
			sol[curEnd] = Math.max(curTake, take);
			if (maxScore < sol[curEnd]) {
				maxScore = sol[curEnd];				
			}
			preEnd = curEnd;			
		}	
		return maxScore;
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
	
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		Gattaca gattaca = new Gattaca();
		int maxScore = gattaca.findMaxScore(fileName);
//		int maxScore = gattaca.findMaxScore("bin/100k.in");
//		int maxScore = gattaca.findMaxScore("bin/10k.in");
//		int maxScore = gattaca.findMaxScore("bin/1mm.in");
//		int maxScore = gattaca.findMaxScore("bin/Gattaca_Input");
		System.out.print(maxScore+"\n");
	}
}

class Gene implements Comparable<Gene>{
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
}
