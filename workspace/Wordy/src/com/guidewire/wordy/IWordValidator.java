package com.guidewire.wordy;

/**
 * Supplies two behaviors: check that the word exists in a dictionary (i.e., is a real word) and another that
 * verifies that the word can be found in a particular board or instance.
 */
public interface IWordValidator {

  /**
   * Checks a dictionary to determine whether a word is a real word.  This does _not_ imply that the word exists in any
   * particular game board instance -- that's the purpose of {@link #isWordInBoard(char[], String)}
   *
   * @param word the word to check
   * @return <code>true</code> if the word is a real word (i.e., found in the dictionary), otherwise <code>false</code>
   */
  boolean isRealWord(String word);

  /**
   * Checks whether the given word can be found in the board. The rules for a "valid" word are:
   * <ul>
   * <li>Words are formed from adjoining letters.</li>
   * <li>Letters must join in the proper sequence to spell a word.</li>
   * <li>They may join horizontally, vertically, or diagonally, to the left, right, or up-and-down.</li>
   * <li>No letter cell may be used more than once within a single word.</li>
   * </ul>
   */
  boolean isWordInBoard(char[] board, String word);

}
