package com.test;

import java.util.*;
import java.util.Map.Entry;

public class LRUCacheQueue<K, V> implements Cache<K, V>
{
    private final int maxSize;    
    private final Map<K, CachedItem<K, V>> map;
    private final CachedItem[] arr;

    public LRUCacheQueue(int maxSize)
    {
        this.maxSize = maxSize;
        map = Collections.synchronizedMap(new LinkedHashMap<K, CachedItem<K, V>>());
        arr = new CachedItem[maxSize];
    }  

    /**
     * When the queue is full, use LRU to replace the item
     */
    public synchronized CachedItem<K, V> put(K key, V val)
    {        
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		CachedItem<K, V> ci = obj;
    		ci.setKey(key);
    		ci.setVal(val);   		
    		moveItemToTop(obj.getArrIndex());
    		return obj;
    	}
    	
    	CachedItem<K, V> ci = new CachedItem<K, V>(key, val);   	
    	int size = map.size();
    	if (size == maxSize) {
    		map.remove(arr[size-1].getKey());
    		arr[size-1] = ci;
    		moveItemToTop(size-1);
    	} else {
    		arr[size] = ci;
    		moveItemToTop(size);
    	}    	
    	map.put(key, ci); 	
    	
    	return ci;
    }
    
    private void moveItemToTop(int i) {
    	if (i == 0) {
    		return; //no need to move
    	}
    	if (i < 0 || i >= maxSize)
    		return; //illegal
    	CachedItem tmp = arr[i];
    	for (int j = i-1; j >= 0; j--) {
    		arr[j+1] = arr[j];
    	}
    	arr[0] = tmp;
    }

    public V get(K key)
    {
    	CachedItem<K, V> obj = map.get(key);
    	if (obj != null) {
    		return obj.getVal();    		
    	}
    	return null;
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



