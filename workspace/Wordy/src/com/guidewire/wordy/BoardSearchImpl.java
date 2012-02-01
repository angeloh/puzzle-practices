package com.guidewire.wordy;

import java.util.*;

public class BoardSearchImpl implements IBoardSearch {	
	
	/**
	 * @see com.guidewire.wordy.IBoardGenerator#processMtx(char[])
	 */
	public CharNode [][] processMtx(char [] arr){
		
		CharNode [][] boardMtx = new CharNode[WordyGame.XLEN][WordyGame.YLEN];
		for (short i = 0; i < WordyGame.XLEN; i++) {			
			for (short j = 0; j < WordyGame.YLEN; j++) {
				boardMtx[i][j] = new CharNode(boardMtx, arr[i*4+j], i, j);
			}			
		}
		for (short i = 0; i < WordyGame.XLEN; i++) {			
			for (short j = 0; j < WordyGame.YLEN; j++) {
				boardMtx[i][j].addAdjList();
			}			
		}
		return boardMtx;
	}	
}


class CharNode{
	final List<CharNode> adjList = new LinkedList<CharNode>();
	final CharNode [][] mtx;
	final char c;
	final short x;
	final short y;
	short color = 0; //0=white, 1=gray, 2=black
	
	public CharNode(CharNode [][] m , char c, short x, short y){
		this.c = c;
		this.x = x;
		this.y = y;
		mtx = m;			
	}
	
	protected void addAdjList(){
		int maxX = mtx.length-1;
		int maxY = mtx[0].length-1;
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				if (i >= 0 && i <=maxX && j >= 0 && j <=maxY){
					if (i == x && j == y)
						continue;
					adjList.add(mtx[i][j]);
				}
			}
		}
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof CharNode)) {
			return false;
		}
		CharNode node = (CharNode)o;
		if (this.x == node.x && this.y == node.y && this.c == node.c) {
			return true;
		}
		return false;
	}	
}