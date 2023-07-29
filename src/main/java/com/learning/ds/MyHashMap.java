package com.learning.ds;

public class MyHashMap<K,V> {
	
	private Entry<K,V>[] buckets;
	
	private int size=0;
	
	private static final int DEFAULT_SIZE=2;
	
	public MyHashMap() {
		super();
		buckets= new Entry[DEFAULT_SIZE];
	}

	static class  Entry<K,V>
	{
		K key;
		V value;
		Entry<K, V> next;
		
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
	public void put(K key,V value)
	{
		int hash=getHash(key);
		Entry<K, V> entry=buckets[hash];
		if(entry==null) {
			buckets[hash]=new Entry<K, V>(key, value);
			size++;
		}else {
			while(entry.next!=null)
			{
				if(entry.key.equals(key))
					break;
				entry=entry.next;
			}
			if(entry.key.equals(key))
				entry.value=value;
			else {
				entry.next=new Entry<K, V>(key, value);
				size++;
			}
			
		}
		   
	}
	
	public V get(K key)
	{
		int hash=getHash(key);
		Entry<K, V> entry=buckets[hash];
		while(entry!=null) {
			if(entry.key.equals(key))
			{
				return entry.value;
			}
			entry=entry.next;
		}
		return null;
	}
	
	public V remove(K key)
	{
		int hash=getHash(key);
		Entry<K, V> entry=buckets[hash];
		V value=null;
		if(entry==null)
			return null;
		if(entry.key.equals(key))
		{
			value=entry.value;
			buckets[hash]=entry.next;
		    entry.next=null;
		    size--;
		    return value;
		}else{	
		  while(entry.next!=null) {
				if(entry.next.key.equals(key))
				{
					value=entry.next.value;
					entry.next=entry.next.next;
					size--;
					return value;
				}
			entry=entry.next;
		  }
		}
		return null;
	}

	
	public int size() {
		return size;
	}
	
	private int getHash(K key) {
		return key.hashCode()%DEFAULT_SIZE;
	}

	
	
	@Override
	public String toString() {
		StringBuffer strBuffer= new StringBuffer();
		for(int i=0;i<buckets.length;i++) {
			Entry<K, V> entry=buckets[i];
			if(entry!=null) {
				strBuffer.append("Bucket Index : "+i+" Values ");
				while(entry!=null) {
					strBuffer.append("[").append(entry.key).append(",").append(entry.value).append("]");
					if(entry.next!=null)
						strBuffer.append("->");
					entry=entry.next;
				}
				strBuffer.append("\n");
			}
		}
		return strBuffer.toString();
	}

	public static void main(String[] args) {
		MyHashMap<Integer, String> map=new MyHashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		
		System.out.println("Size "+map.size());
		System.out.println("Get 2 "+map.get(2));
		System.out.println("Get 1 "+map.get(1));
		System.out.println("Get 3 "+map.get(3));
		
		System.out.println(map);
		
		map.remove(5);
		
		System.out.println(map);
		
        map.remove(6);
		
		System.out.println(map);
		
		System.out.println("Size "+map.size());
	}

}
