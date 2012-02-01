package com.guidewire.wordy;

import java.util.*;

public class BoardGeneratorImpl implements IBoardGenerator {	
	
	/**
	 * @see com.guidewire.wordy.IBoardGenerator#generateBoard()
	 */
	public List<List<Character>> generateBoard(){
		IWordy wordy = new WordyImpl();
		//generating 16 chars
		char [] chars = wordy.generateNewBoard();
		if (chars == null || chars.length != WordyGame.XLEN*WordyGame.YLEN)
			throw new RuntimeException("the number of board characters is wrong.");
		List<List<Character>> board = new ArrayList<List<Character>>();				
		for (short i = 0; i < WordyGame.XLEN; i++) {
			List<Character> listChar = new LinkedList<Character>();
			for (short j = 0; j < WordyGame.YLEN; j++) {
				listChar.add(chars[i*4+j]);
			}
			board.add(listChar);
		}
		return board;
	}	
}


