package com.test;

/**
 * 
 * @author Angelo Huang
 *
 */
public class FixedCacheQueueFactory<K, V> extends ObjectFactory{
	
	/**
	 * Only classes in same package or subclasses in other packages (super()) can call the constructor
	 */
	protected FixedCacheQueueFactory() {
		
	}
	
	@Override
	public Cache<K, V> getObject(int size) {		
		return new FixedCacheQueue<K, V>(size); 
	}
}
