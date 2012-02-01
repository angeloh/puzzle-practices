package com.test;

import java.util.*;
import java.util.Map.Entry;

public class FIFOCacheQueue<K, V> implements Cache<K, V>
{
    private final int maxSize;    
    private int current = 0;//in order to make circular
    private final Map<K, CachedItem<K, V>> map;
    private final CachedItem[] arr;

    public FIFOCacheQueue(int maxSize)
    {
        this.maxSize = maxSize;
        map = Collections.synchronizedMap(new LinkedHashMap<K, CachedItem<K, V>>());
        arr = new CachedItem[maxSize];
    }  

    /**
     * When the queue is full, use FIFO to replace the item
     */
    public synchronized CachedItem<K, V> put(K key, V val)
    {        
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		CachedItem<K, V> ci = obj;
    		ci.setKey(key);
    		ci.setVal(val);
    		return obj;
    	}
    	
    	CachedItem<K, V> ci = new CachedItem<K, V>(key, val);
    	
    	if (map.size() >= maxSize) {
    		map.remove(arr[current].getKey());
    	}    	
    	arr[current++] = ci;
    	map.put(key, ci);    	
    	if(current >= maxSize)
			current = 0; //circular index
    	
    	return ci;
    }

    public V get(K key)
    {
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		return obj.getVal();    		
    	}
    	return null;
    }
    
    public void print(){
    	Iterator<Entry<K, CachedItem<K, V>>> itor = map.entrySet().iterator();
    	while(itor.hasNext()) {
    		Entry<K, CachedItem<K, V>> entry = itor.next();
    		System.out.println(entry.getKey().toString() + "," + entry.getValue().toString());
    	}
    }
    
 	public int getMaxSize() {
		return maxSize;
	}
}



