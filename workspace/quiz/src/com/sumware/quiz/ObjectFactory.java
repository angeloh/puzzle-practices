package com.sumware.quiz;

import java.io.InputStream;

/**
 * ObjectFactory is base class for factory method to create object from text file or any other data source.
 * @author Angelo Huang
 * 
 */
public abstract class ObjectFactory{
	public abstract Object getObject(String objectType, InputStream in);
}