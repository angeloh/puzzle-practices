package com.test;

public interface Cache<K, V>
{
	public CachedItem<K, V> put(K key, V val);
	public V get(K key);
	public void print();
}

class  CachedItem <K, V>{
	private K key;
	private V val;
	private int arrIndex = 0;
	public int getArrIndex() {
		return arrIndex;
	}
	public void setArrIndex(int arrIndex) {
		this.arrIndex = arrIndex;
	}
	public CachedItem (K key, V val) {
		this.key = key;
		this.val = val;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getVal() {
		return val;
	}
	public void setVal(V val) {
		this.val = val;
	}
	@Override
	 public String toString() {
        return new StringBuilder()
        .append("key:" + this.key.toString())
        .append("val:" + this.val.toString()).toString();        
    }
}
