import java.util.*;

public class JavaPractice8 {
	public static void main (String [] args) {
		int [][] mtx = {{0,1,1,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}};
		findSubSquareAllOne(mtx);
		findSubRectAllOne(mtx);		
		
	}
	
	/**
	 * @param a
	 * @param b
	 * You are given two sorted arrays, A and B, and A has a large enough buffer at the
	 * end to hold B. Write a method to merge B into A in sorted order.
	 * From CareerCup
	 */
	public static void mergeBToA(int [] a, int [] b, int lastAIdx) {
		int aIdx = lastAIdx;
		int bIdx = b.length - 1;
		int curIdx = a.length - 1;
		
		while(aIdx >= 0 && bIdx >= 0) {
			if (a[aIdx] >= b[bIdx]){
				a[curIdx--] = a[aIdx--];
			} else {
				a[curIdx--] = b[bIdx--];
			}
		}		
		while (bIdx >= 0) {
			a[curIdx--] = b[bIdx--];
		}
		
	}
	
	public static void mergeTwoSortedArray(int [] a, int [] b) {
		int [] newArray = new int [a.length + b.length];
		int aIdx = 0;
		int bIdx = 0;
		int curIdx = 0;
		while(aIdx < a.length && bIdx < b.length) {
			if (a[aIdx] <= b[bIdx]){
				newArray[curIdx] = a[aIdx];
				aIdx++;
				curIdx++;
			} else {
				newArray[curIdx] = b[bIdx];
				bIdx++;
				curIdx++;
			}
		}
		while (aIdx < a.length) {
			newArray[curIdx] = a[aIdx];
			aIdx++;
			curIdx++;
		}
		while (bIdx < b.length) {
			newArray[curIdx] = b[bIdx];
			bIdx++;
			curIdx++;
		}
	}
	
	public static int findMin(int a, int b, int c) {
		return a<b?(a<c?a:c):(b<c?b:c);		
	}
	
	/**
	 * @param mtx
	 * Given: A two-dimensional array b (M rows, N columns) of Boolean values ("0" and "1").
	 * Required: Find the largest (most elements) square containing all ones.
	 * 
	 * From mitbbs & http://www.ddj.com/architect/184410529
	 */
	public static void findSubSquareAllOne(int [][] mtx) {
		if (mtx == null)
			return;
		int [][] tmpMtx = new int [mtx.length][mtx[0].length];
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx[0].length; j++) {
				tmpMtx [i][j] = mtx[i][j];
			}
		}
		
		int maxLength = -1;
		int maxLRX = -1;
		int maxLRY = -1;
		for (int i = 1; i < mtx.length; i++) {
			for (int j = 1; j < mtx[0].length; j++) {
				if (tmpMtx [i][j] == 1) {
					tmpMtx [i][j] = findMin(tmpMtx [i-1][j-1], tmpMtx [i-1][j], tmpMtx [i][j-1]) + 1;
					if (tmpMtx [i][j] >= maxLength) {
						maxLength = tmpMtx [i][j];
						maxLRX = i;
						maxLRY = j;
					}
				}
			}
		}
		if (maxLength > 1) {
			int ulx = maxLRX - maxLength + 1;
			int uly = maxLRY - maxLength + 1;
			System.out.println("UpperLeftX:" + ulx);
			System.out.println("UpperLeftY:" + uly);
			System.out.println("LowerRightX:" + maxLRX );
			System.out.println("LowerRightY:" + maxLRY);
		}
	}
	
	private static int area (int ulx, int uly, int lrx, int lry){
			if(ulx>lrx && uly>lry) {
				return 0;
			} else {
				return (lrx-ulx+1) * (lry-uly+1);
			}
	}
	
	private static void updateCache(int [][] mtx, int [] c, int x) {
		for (int y = 0; y < mtx[0].length; y++) {
			if (mtx[x][y] != 0) {
				c[y]++;
			} else {
				c[y] = 0;
			}
		}
	}
	
	private static int [] findLowerRight(int [] c, int ulx, int uly, int yLen) {
		int maxX = Integer.MAX_VALUE;
		int lrx = ulx;
		int lry = uly;
		int y = uly;
		while(y<yLen && c[y]!=0) {			
			int x = Math.min(ulx+c[y]-1, maxX);
			maxX = x;
			if(area(ulx, uly, x, y) > area(ulx, uly, lrx, lry)){
				lrx = x;
				lry = y;
			}
			y++;
		}		
		return new int []{lrx, lry};
	}
	
	/**
	 * @param mtx
	 * Given: A two-dimensional array b (M rows, N columns) of Boolean values ("0" and "1").
	 * Required: Find the largest (most elements) rectangular containing all ones.
	 * 
	 * From mitbbs & http://www.ddj.com/architect/184410529
	 */
	public static void findSubRectAllOne(int [][] mtx) {
		int [] c = new int[mtx[0].length];
		int bestulx = 0;		
		int bestuly = 0;
		int bestlrx = -1;
		int bestlry = -1;
		for (int ulx = mtx.length-1; ulx >= 0; ulx--) {
			updateCache(mtx, c, ulx);
			for (int uly = 0; uly < mtx[0].length; uly++) {
				if (c[uly] == 0)
					continue;
				int [] arr = findLowerRight(c, ulx, uly, mtx[0].length);				
				int lrx = arr[0];
				int lry = arr[1];
				if(area(ulx, uly, lrx, lry) > area(bestulx, bestuly, bestlrx, bestlry)){
					bestulx = ulx;		
					bestuly = uly;
					bestlrx = lrx;
					bestlry = lry;
				}
			}
		}
		System.out.println("UpperLeftX:" + bestulx);
		System.out.println("UpperLeftY:" + bestuly);
		System.out.println("LowerRightX:" + bestlrx );
		System.out.println("LowerRightY:" + bestlry);
	}
	
	//dynamic programming
	public static int findSubRectAllOne2(int [][] mtx) {
		int m = mtx.length;
		int n = mtx[0].length;
		int [][] ht = new int [m+1][n+1];
		int [][] lt = new int [m+1][n+1];
		int [][] rt = new int [m+1][n+1];
		int max = Integer.MIN_VALUE;
		
		
		for (int j = 0; j <=n; j++) {
			ht[0][j] = 0;
			lt[0][j] = 1;
			rt[0][j] = n;
		}
		
		for (int i = 1; i <= m; i++) {
			int k = 1;
			int tmp = 0;
			//from left to right, find the height(ht[][]) and 
			//the further left cell for the rectangular (lf[][])
			for (int j = 1; j <= n; j++){
				if (mtx[i-1][j-1] == 1) {
					ht[i][j] = ht[i-1][j] + 1;
					if (k > lt[i-1][j]){
						lt[i][j]=k;
					} else {
						lt[i][j]=lt[i-1][j];
					}
				} else {
					ht[i][j] = 0;
					lt[i][j] = 1;
					k = j + 1;
				}
			}
			//from right to left, find the further 
			//right cell for the rectangular (rt[][])
			k = n;
			for (int j=n; j>=1; j--) {
				if(mtx[i-1][j-1] == 1) {
					if(k < rt[i-1][j]){
						rt[i][j] = k;
					} else {
						rt[i][j] = rt[i-1][j];
					}
					tmp = ht[i][j] * (rt[i][j]-lt[i][j]+1);
					//calculate maximum rectangular area so far
					if (tmp > max) {
						max = tmp;
					}
				} else {					
					rt[i][j] = n;
					k = j - 1;
				}
			}
		}
		
		return max;		
	}
	
}
	
	