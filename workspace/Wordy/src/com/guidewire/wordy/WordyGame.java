package com.guidewire.wordy;

/**
 * The client application that uses the Wordy implementation.
 */
public class WordyGame {
  static String DICTFILE = "CROSSWD.txt";  
  static short XLEN = 4;
  static short YLEN = 4;
  
  public static void main(String[] args) {
	  
    IWordy wordy = new WordyImpl(); // Instantiate your Wordy implementation here!
    new WordyFrame(wordy).setVisible(true);  
	
  }

}
