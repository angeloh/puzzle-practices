package com.test;

/**
 * 
 * @author Angelo Huang
 *
 */
public class LRUCacheQueueFactory<K, V> extends ObjectFactory{
	
	/**
	 * Only classes in same package or subclasses in other packages (super()) can call the constructor
	 */
	protected LRUCacheQueueFactory() {
		
	}
	
	@Override
	public Cache<K, V> getObject(int size) {		
		return new LRUCacheQueue<K, V>(size); 
	}
}
