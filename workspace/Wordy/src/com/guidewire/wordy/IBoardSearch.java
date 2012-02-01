package com.guidewire.wordy;

/**
 * The interface for the generator of wordy board search.
 */
public interface IBoardSearch {  
  
  /**
   * @param arr
   * @return a matrix of nodes that each node has a adjacent list for neighbors.
   */
  CharNode [][] processMtx(char [] arr);
}
