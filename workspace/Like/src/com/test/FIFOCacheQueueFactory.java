package com.test;

/**
 * 
 * @author Angelo Huang
 *
 */
public class FIFOCacheQueueFactory<K, V> extends ObjectFactory{
	
	/**
	 * Only classes in same package or subclasses in other packages (super()) can call the constructor
	 */
	protected FIFOCacheQueueFactory() {
		
	}
	
	@Override
	public Cache<K, V> getObject(int size) {		
		return new FIFOCacheQueue<K, V>(size); 
	}
}
