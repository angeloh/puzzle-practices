package com.guidewire.wordy;

import java.io.*;
import java.util.*;

public class WordValidatorImpl implements IWordValidator{
	Set<String> dict = null;
	
	/**
	 * @see com.guidewire.wordy.IWordValidator#isRealWord(java.lang.String)
	 */
	public boolean isRealWord(String word) {
		if (dict == null)
			readFile(WordyGame.DICTFILE);
		return dict.contains(word);
	}
	
	/**
	 * @see com.guidewire.wordy.IWordValidator#isWordInBoard(char[], java.lang.String)
	 */
	public boolean isWordInBoard(char[] arr, String word){
		if (arr == null || word == null)
			return false;
		IBoardSearch bs = new BoardSearchImpl();
		CharNode [][] mtx = bs.processMtx(arr);
		return seachAllNodes(mtx, word);		
	}
	
	/**
	 * @param mtx
	 * @param word
	 * @return boolean
	 * search all the nodes with same character and then do dfs from it.
	 */
	private boolean seachAllNodes(CharNode [][] mtx, String word){
		char firstC = word.charAt(0);		
		for (short i = 0; i < WordyGame.XLEN; i++) {			
			for (short j = 0; j < WordyGame.YLEN; j++) {
				if (mtx[i][j].c == firstC){
					cleanMtxColor(mtx);
					mtx[i][j].color = 1; //gray					
					boolean found = dfs(mtx[i][j], word, 0);
					if (found)
						return true;
				}
			}			
		}		
		return false;
	}
	
	
	/**
	 * @param root
	 * @param word
	 * @param level
	 * @return boolean
	 * using dfs search to find the match in the board.
	 */
	public boolean dfs (CharNode root, String word, int level) {
		if (root == null || word == null) {
			return false;
		}
		if (root.c == word.charAt(level)) {
			root.color = 2;//black
			if (level == word.length()-1){//got the word from the board
				return true;
			}
			Iterator<CharNode> iter = root.adjList.iterator();
			while(iter.hasNext()){					
				boolean found = false;
				CharNode adj = iter.next();
				if (adj.color == 0) {//unvisited
					adj.color = 1;//gray
					found = dfs(adj, word, level+1);
					if (found)
						return true;
					else
						adj.color = 0;//white
				}
			}	
			return false;
		} else {
			return false;
		}		
	}
	
	private void cleanMtxColor(CharNode [][] mtx){
		for (short i = 0; i < WordyGame.XLEN; i++) {			
			for (short j = 0; j < WordyGame.YLEN; j++) {				
					mtx[i][j].color = 0; //white				
			}	
		}	
	}	
	
	private boolean readFile(String fileName) {
		if (fileName == null)
			return false;		
		dict = new HashSet<String>();
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine;			
			while((currLine = br.readLine())!=null) {
				dict.add(currLine);
			}
			br.close();
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
}