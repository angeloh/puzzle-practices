package com.guidewire.wordy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordValidatorTest
{
	@Before
    public void setUp() throws Exception
    {    	

    }

    @After
    public void tearDown() throws Exception
    {

    }   

    @Test
    public void isRealWord()
    {
    	WordyGame.DICTFILE = "CROSSWD.txt";
    	IWordValidator wv = new WordValidatorImpl();    	
    	boolean b = wv.isRealWord("abandonments");
    	Assert.assertTrue(b);
    	
    	b = wv.isRealWord("zowi");
    	Assert.assertFalse(b);	
    }
    
    @Test
    public void isWordInBoard()
    {
    	WordyGame.XLEN = 4;
    	WordyGame.YLEN = 4;
    	IWordValidator wv = new WordValidatorImpl();  
    	//[D, D, F, Q, D, T, S, G, E, Q, Q, F, N, M, Y, O]
    	char [] board = {'D', 'D', 'F', 'Q', 'D', 'T', 'S', 'G', 'E', 'Q', 'Q', 'F', 'N', 'M', 'Y', 'O'};
    	String word = ""+board[0]+board[1]+board[2];
    	boolean b = wv.isWordInBoard(board, word);
    	Assert.assertTrue(b);
    	word = ""+board[5]+board[6]+board[7];
    	b = wv.isWordInBoard(board, word);
    	Assert.assertTrue(b);
    	word = ""+board[6]+board[12]+board[13];
    	b = wv.isWordInBoard(board, word);
    	Assert.assertFalse(b);
    }   
    
}
