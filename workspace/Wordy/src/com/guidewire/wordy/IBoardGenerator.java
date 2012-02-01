package com.guidewire.wordy;

import java.util.List;

/**
 * The interface for the generator of Wordy "boards".
 */
public interface IBoardGenerator {

  /**
   * Generates to 4x4 grid of letters (aka the Board) as a list-of-lists.
   *
   * @return a 4x4 list-of-lists containing a grid of randomly generated letters (as Characters)
   */
  List<List<Character>> generateBoard();  
 
}
