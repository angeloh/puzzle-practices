package backup;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;


/**
 * @author Angelo Kaichen Huang
 *
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=15
 * http://www.cs.princeton.edu/introcs/96optimization/Knapsack.java.html
 * This solution is using Dynamic Programming method
 * and reduce the puzzle to find the longest increasing subsequence
 * O(nlog(n))
 */
public class Gattaca2{
	private int dnaLen = 0;
	private int geneSize = 0;
	public int findMaxScore(String fileName) {
		Gene[] geneArr = readFile(fileName);				
		Arrays.sort(geneArr);
//		bucketSort(geneArr);
//		Queue<GeneNode> pq = new PriorityQueue<GeneNode>();
//		pq.add(new GeneNode(0, geneArr[0].score));
//		longestIncreasingSubseq(geneArr, pq);
//		return pq.poll().maxScore;
		
//		return  binPack2(geneArr);
		
//		LinkedList[] arrList = readFile(fileName);
//		return  binPack4(arrList);
		return  binPack4(geneArr);
	}
	/**
	 * @param geneList
	 * @param score
	 * @param index
	 * 
	 * Algorithm for knapsack
	 */
	/*private int binPack(Gene[] gene){				
		int [][] opt = new int[geneSize+1][dnaLen+1];		
		int maxScore = Integer.MIN_VALUE;
		for(int g = 1; g <= geneSize; g++ ){
			for (int l = 1; l <= dnaLen; l++) {
				int option1 = opt[g-1][l]; //don't take current gene
				
				int option2 = Integer.MIN_VALUE;
				if(gene[g-1].end < l) //gene array starts from zero
					option2 = gene[g-1].score + opt[g-1][gene[g-1].start];
				
				opt[g][l] = Math.max(option1, option2);
				if (maxScore < opt[g][l])
					maxScore = opt[g][l];
			}
		}		
		return maxScore;
	}*/
	
	/*private int binPack2(Gene[] gene){
		int [] prevSol = new int[dnaLen+1];
		int [] curSol = new int[dnaLen+1];		
		int maxScore = Integer.MIN_VALUE;
		for(int g = 1; g <= geneSize; g++ ){
			for (int l = 1; l <= dnaLen; l++) {
				int notTake = prevSol[l]; //don't take current gene
				
				int take = Integer.MIN_VALUE;
				if(gene[g-1].end < l) //gene array starts from zero
					take = gene[g-1].score + prevSol[gene[g-1].start];
				
				curSol[l] = Math.max(notTake, take);
				if (maxScore < curSol[l]) {
					maxScore = curSol[l];
					for (int c = l+1; c <= dnaLen; c++){
						curSol[c] = curSol[l]; 
					}
					break;
				}
			}	
			prevSol = Arrays.copyOf(curSol, dnaLen+1);
		}		
		return maxScore;
	}*/
	
	
/*	private int binPack3(Gene[] gene){
		int [] prevSol = new int[dnaLen+1];
		int [] curSol = new int[dnaLen+1];		
		int maxScore = Integer.MIN_VALUE;
		for(int g = 1; g <= geneSize; g++ ){
			for (int l = 1; l <= dnaLen; l++) {
				int notTake = prevSol[l]; //don't take current gene
				
				int take = Integer.MIN_VALUE;
				if(gene[g-1].end < l) //gene array starts from zero
					take = gene[g-1].score + prevSol[gene[g-1].start];
				
				curSol[l] = Math.max(notTake, take);
				if (maxScore < curSol[l]) {
					maxScore = curSol[l];
					for (int c = l+1; c <= dnaLen; c++){
						curSol[c] = curSol[l]; 
					}
					break;
				}
			}

			prevSol = Arrays.copyOf(curSol, dnaLen+1);
		}		
		return maxScore;
	}*/
	private int binPack4(Gene[] gene){
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
	
/*	private int binPack4(Map<Integer, List<Gene>> map){
		int [] sol = new int[dnaLen+1];				
//		for (int i = 0; i < gene[0].end; i++)
//			sol[i] = 0;
		sol[0] = 0;
		int maxScore = Integer.MIN_VALUE;
		
		for (int l = 0; l <= dnaLen; l++) {
			int notake = sol[l-1]; //don't consider current cell
			int take = Integer.MIN_VALUE;
//			int idx = binarySearch(gene, l);
			if(idx > -1) {//consider current cell
				int befStart = gene[idx].start-1;
				take = befStart > -1 ? gene[idx].score + sol[befStart]: gene[idx].score;				
			}
			sol[l] = Math.max(notake, take);
			if (maxScore < sol[l]) {
				maxScore = sol[l];				
			}
		}				
		return maxScore;
	}*/
	
//	private int binPack4(Map<Integer, List<Gene>> map){
//		int [] sol = new int[dnaLen+1];				
//		sol[0] = 0;
//		int maxScore = Integer.MIN_VALUE;
//		
//		for (int l = 0; l <= dnaLen; l++) {
//			int noTake = l > 0 ? sol[l-1] : 0; //don't consider current cell
//			int bestTake = Integer.MIN_VALUE;
//			List<Gene> list = map.get(l);
//			if(list != null) {//consider current cell
//				for (Gene g : list) {
//					int befStart = g.start-1;
//					int take = befStart > -1 ? g.score + sol[befStart]: g.score;
//					if (take > bestTake)
//						bestTake = take;
//				}
//			}
//			sol[l] = Math.max(noTake, bestTake);
//			if (maxScore < sol[l]) {
//				maxScore = sol[l];				
//			}
//		}				
//		return maxScore;
//	}
	
/*	private int binPack4(LinkedList[] arrList){
		int [] sol = new int[dnaLen+1];				
		sol[0] = 0;
		int maxScore = Integer.MIN_VALUE;
		
		for (int l = 0; l <= dnaLen; l++) {
			int noTake = l > 0 ? sol[l-1] : 0; //don't consider current cell
			int bestTake = Integer.MIN_VALUE;
//			List<Gene> list = map.get(l);
			if(arrList[l] != null) {//consider current cell
				for (Object o : arrList[l]) {
					Gene g = (Gene)o;
					int befStart = g.start-1;
					int take = befStart > -1 ? g.score + sol[befStart]: g.score;
					if (take > bestTake)
						bestTake = take;
				}
			}
			sol[l] = Math.max(noTake, bestTake);
			if (maxScore < sol[l]) {
				maxScore = sol[l];				
			}
		}				
		return maxScore;
	}*/
	
	private int binarySearch(Gene[] arr, int end) {
		int left = 0, right = 0, mid = 0;
		right = arr.length-1;
		if (arr[right].end<end){
			return -1;
		}
		if (arr[left].end>end){
			return -1;
		}
		while(left<=right) {
			mid = (left + right)/2;
			if (arr[mid].end > end) {
				right = mid - 1;				
			} else if (arr[mid].end < end) {
				left = mid + 1;			
			} else {
				return mid; 
			}
		}
		return -1;
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
	
	/*private LinkedList[] readFile(String fileName) {
		if (fileName == null)
			return null;
//		Gene[] geneArr = null;
//		Map<Integer, List<Gene>> map = new HashMap<Integer, List<Gene>>();
		LinkedList[] listArr = null;
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
//			geneArr = new Gene[geneSize];
//			while((currLine = br.readLine())!=null) {				
//				geneArr[idx] = parseGene(currLine, dnaLen);				
//				idx++;				
//			}
			listArr = new LinkedList[dnaLen+1];
			Gene gene;
			while((currLine = br.readLine())!=null) {				
				gene = parseGene(currLine, dnaLen);				
//				List<Gene> list = listArr[gene.end];
				if (listArr[gene.end] == null) {
					listArr[gene.end] = new LinkedList();					
				}				
				listArr[gene.end].add(gene);
			}		
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		return listArr;
	}*/
	
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		Gattaca2 gattaca = new Gattaca2();
		int maxScore = gattaca.findMaxScore(fileName);
//		int maxScore = gattaca.findMaxScore("bin/100k.in");
//		int maxScore = gattaca.findMaxScore("bin/10k.in");
//		int maxScore = gattaca.findMaxScore("bin/1mm.in");
//		int maxScore = gattaca.findMaxScore("bin/Gattaca_Input");
		System.out.print(maxScore+"\n");
	}
}



