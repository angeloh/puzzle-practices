package com.test;

import java.util.*;
import java.util.Map.Entry;

public class FixedCacheQueue<K, V> implements Cache<K, V>
{
    private final int maxSize;  
    private final Map<K, CachedItem<K, V>> map;   

    public FixedCacheQueue(int maxSize)
    {
        this.maxSize = maxSize;
        map = Collections.synchronizedMap(new LinkedHashMap<K, CachedItem<K, V>>());        
    }  

    /**
     * When the queue is full, return null
     */
    public CachedItem<K, V> put(K key, V val)
    {        
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		CachedItem<K, V> ci = obj;
    		ci.setKey(key);
    		ci.setVal(val);
    		return obj;
    	}
    	
    	CachedItem<K, V> ci = new CachedItem<K, V>(key, val);   	
    	if (map.size() < maxSize) {
    		map.put(key, ci);
    		return ci;	
    	} else {
    		return null;
    	}
    }

    public V get(K key)
    {
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		return obj.getVal();    		
    	}
    	return null;
    }
    
    public CachedItem<K, V> remove(K key)
    {
    	return map.remove(key);    	
    }
    
 	public int getMaxSize() {
		return maxSize;
	}
 	
 	public void print(){
 		Iterator<Entry<K, CachedItem<K, V>>> itor = map.entrySet().iterator();
 		while(itor.hasNext()) {
 			Entry<K, CachedItem<K, V>> entry = itor.next();
 			System.out.println(entry.getKey().toString() + "," + entry.getValue().toString());
 		}
 	}
}
