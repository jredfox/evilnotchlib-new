package jml.evilnotch.lib.array;

import java.util.Map;
import java.util.Map.Entry;

public class MapEntry<K, V> implements Map.Entry<K, V>, Comparable<Map.Entry<K, V>>{
	
	public K k;
	public V v;
	
	public MapEntry(K k, V v)
	{
		this.k = k;
		this.v = v;
	}

	@Override
	public K getKey() 
	{
		return this.k;
	}

	@Override
	public V getValue() {
		return this.v;
	}

	@Override
	public V setValue(V v) 
	{
		V old = this.v;
		this.v = v;
		return old;
	}
	
	@Override
	public int hashCode()
	{
		return this.k.hashCode();
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof Map.Entry))
			return false;
		Map.Entry entry = (Map.Entry) other;
		return this.k.equals(entry.getKey());
	}
	
	@Override
	public String toString()
	{
		return this.k + "=" + this.v;
	}

	@Override
	public int compareTo(Map.Entry<K, V> o) 
	{
		return ((Comparable)this.k).compareTo((Comparable)o.getKey());
	}

}
