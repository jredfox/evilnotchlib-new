package jml.evilnotch.lib.array;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/**
 * a SortedMap which uses a compartor for insertion order. If the compartor is null default HashMap behavior
 */
public class SortedMap<K, V> implements Map<K, V>{
	
	public SortedMap() 
	{
		this((Comparator)null);
	}
	
	public SortedMap(Comparator c) 
	{
		
	}
	
	public SortedMap(Collection col, Comparator<? extends Map.Entry<K, V>> c) 
	{
		//TODO:
	}

	public SortedMap(Map<K, V> map, Comparator<? extends Map.Entry<K, V>> c) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
