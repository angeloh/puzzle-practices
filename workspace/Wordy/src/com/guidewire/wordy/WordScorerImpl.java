package com.guidewire.wordy;

public class WordScorerImpl implements IWordScorer{
	/**
	 * @see com.guidewire.wordy.IWordScorer#scoreWord(java.lang.String)
	 *
	 */
	public int scoreWord(String word){
		if (word == null)
			return 0;		
		
		int len = word.length();
		if (len <= 2)
			return 0;
		switch(len){
			case 3:
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			default:
				return 11;
		}
	}
}