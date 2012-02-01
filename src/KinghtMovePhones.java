/**
 * Bigfix interview questions
 * 
 * @author ahuang
 *	A phone company wants to begin a promotion geared towards chess enthusiasts
 *	wherein they will begin issuing "Knight's move" phone numbers. Given
 *	a standard 10-key telephone keyboard, design an algorithm to find the number os possible
 *	sequences of length n >= 1 where each number k (2...n) is a knight's move 
 *	(x+-1, y+-2) or (x+-2, y+-1) from k-1
 */
public class KinghtMovePhones{
	static final int [] _0_MOVES = {5,7};
	static final int [] _1_MOVES = {6,8};
	static final int [] _2_MOVES = {3,7};
	static final int [] _3_MOVES = {2,8,9};
	static final int [] _4_MOVES = {};
	static final int [] _5_MOVES = {0,6,9};
	static final int [] _6_MOVES = {1,5};
	static final int [] _7_MOVES = {0,2};
	static final int [] _8_MOVES = {1,3};
	static final int [] _9_MOVES = {3,5};
	
	
	public static void main(String [] args) {
		getTotalPossiblePhones(5);
	}
	
	public static int [] getBasicMoves(int p) {
		switch(p){
			case 0:
				return _0_MOVES; //{5,7}
			case 1:
				return _1_MOVES; //{6,8}
			case 2:
				return _2_MOVES; //{3,7}
			case 3:
				return _3_MOVES; //{2,8,9}
			case 4:
				return _4_MOVES; //{}
			case 5:
				return _5_MOVES; //{0,6,9}
			case 6:
				return _6_MOVES; //{1,5}
			case 7:
				return _7_MOVES; //{0,2}
			case 8:
				return _8_MOVES; //{1,3}
			case 9:
				return _9_MOVES; //{3,5}
				
			default:
				return new int [] {};
 		}
	}
	
	public static void getTotalPossiblePhones(int k) {
		//column 1 is the basic move for each number. column 0 is discarded.
		int [][] mtx = new int [10][k+1];
		
		for (int i = 0; i <= 9; i++) {
			mtx[i][1] = 1;			
		}
		
		for (int j = 2; j <= k; j++) {
			for (int i = 0; i <= 9; i++) {
				for (int m : getBasicMoves(i)) {
					mtx [i][j] += mtx[m][j-1];
				}
			}
		}
		
		for (int i = 0; i <= 9; i++) {
			System.out.println("Key '" + i + "' in k has " + mtx[i][k] + " moves.");
		}
	}
}