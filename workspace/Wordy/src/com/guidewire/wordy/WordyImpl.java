package com.guidewire.wordy;

import java.util.*;

public class WordyImpl implements IWordy {

	static char[] currArr;
	/**
	 * @see com.guidewire.wordy.IWordy#generateNewBoard()
	 */
	public char[] generateNewBoard(){
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		Random r = new Random();		
		int area = WordyGame.XLEN * WordyGame.YLEN;
		char [] arr = new char[area];
		for(int i = 0; i < area; ++i) {			
			arr[i] = str.charAt(r.nextInt(26));
		}
		currArr = arr;
		return arr;
	} 

	
	/**
	 * @see com.guidewire.wordy.IWordy#scoreWords(java.util.List)
	 */
	public int scoreWords(List<String> words){
		if (words == null) {
			return -1;
		}
		IWordValidator wv = new WordValidatorImpl();
		IWordScorer ws = new WordScorerImpl();		
		int score = 0;
		//verify each word existed in the dictionary
		//and existed in the board (extra credit)
		for (String s: words) {
			if (wv.isRealWord(s) && wv.isWordInBoard(currArr, s))			
				score += ws.scoreWord(s);
		}
		return score;
	}
}