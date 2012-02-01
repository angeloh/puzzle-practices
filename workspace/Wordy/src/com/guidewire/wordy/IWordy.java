package com.guidewire.wordy;

import java.util.List;

/**
 * Interface that defines the main "Wordy" application class that external clients, such as WordyGame, will use.
 */
public interface IWordy {

  /**
   * Request that a new "board" (or grid) of letters be generated.
   * <p>
   * The array must be arranged where each four characters represent a row, i.e.:
   * <pre>
   * [ 0] [ 1] [ 2] [ 3]
   * [ 4] [ 5] [ 6] [ 7]
   * [ 8] [ 9] [10] [11]
   * [12] [13] [14] [15]
   * </pre>
   *
   * @return a single-dimensional character array of letters.
   */
  char[] generateNewBoard();

  /**
   * Request that the list of words be scored according to the game's scoring algorithm.
   *
   * @param words list of words
   * @return the score based on the given word list
   */
  int scoreWords(List<String> words);
}
