package com.test;

public class Main {
	
    
	public static void main(String[] args) {
		
		FIFOCacheQueue<String, String> fifo = new FIFOCacheQueue<String, String>(3);
		fifo.put("str1", "val1");
		fifo.put("str2", "val2");
		fifo.put("str3", "val3");
		fifo.put("str4", "val4");
		fifo.put("str5", "val5");
		fifo.print();
		System.out.println();
		FixedCacheQueue<String, String> fcq = new FixedCacheQueue<String, String>(3);
		fcq.put("str1", "val1");
		fcq.put("str2", "val2");
		fcq.put("str3", "val3");
		fcq.put("str4", "val4");
		fcq.put("str5", "val5");
		fcq.print();
		System.out.println();
		LRUCacheQueue<String, String> lru = new LRUCacheQueue<String, String>(3);
		lru.put("str1", "val1");
		lru.put("str2", "val2");
		lru.put("str3", "val3");
		lru.put("str4", "val4");
		lru.put("str5", "val5");
		lru.print();	

	}
}