package com.mars;

import java.util.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RoverTest
{
	Main main;
	@Before
    public void setUp() throws Exception
    {
		main = new Main();
    }

    @After
    public void tearDown() throws Exception
    {

    }   

    @Test
    public void firstRover()
    {
    	List<String> input = new ArrayList<String>();    	
    	input.add("5 5");
    	input.add("1 2 N");
    	input.add("LMLMLMLMM");
    	ControlCenter cc = main.getCtlCenter(input);   	
    	Assert.assertEquals("1 3 N", cc.runRover(0));    	
    } 
    
    @Test
    public void secondRover()
    {    	
    	List<String> input = new ArrayList<String>();    	
    	input.add("5 5");
    	input.add("3 3 E");
    	input.add("MMRMMRMRRM");
    	ControlCenter cc = main.getCtlCenter(input);   	
    	Assert.assertEquals("5 1 E", cc.runRover(0));
    }  
   
}
