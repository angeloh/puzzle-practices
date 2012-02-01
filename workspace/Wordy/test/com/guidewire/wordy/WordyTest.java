package com.guidewire.wordy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordyTest
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
    public void generateNewBoard()
    {
    	WordyGame.XLEN = 4;
    	WordyGame.YLEN = 4;
    	int area = 4*4;
    	IWordy wordy = new WordyImpl();
    	char[] board = wordy.generateNewBoard();
    	Assert.assertNotNull(board);
    	Assert.assertEquals(area, board.length);
    }    
}
