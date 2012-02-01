package com.guidewire.wordy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WordScorerTest
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
    public void scoreWord5()
    {
    	IWordScorer ws = new WordScorerImpl();    	
    	int score = ws.scoreWord("ABCDE");
    	Assert.assertEquals(2, score);    	
    }
    
    @Test
    public void scoreWord7()
    {
    	IWordScorer ws = new WordScorerImpl();    	
    	int score = ws.scoreWord("ABCDEFG");
    	Assert.assertEquals(5, score);    	
    }
    
    @Test
    public void scoreWord8()
    {
    	IWordScorer ws = new WordScorerImpl();    	
    	int score = ws.scoreWord("ABCDEFGH");
    	Assert.assertEquals(11, score);    	
    }
}
