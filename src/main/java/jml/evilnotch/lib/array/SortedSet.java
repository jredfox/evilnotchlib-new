package jml.evilnotch.lib.array;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import jml.evilnotch.lib.JavaUtil;

public class SortedSet<T> implements Set<T>{
	
	protected Comparator comparator;
	protected Set<T> child;
	
	public SortedSet()
	{
		this(null);
	}
	
	public SortedSet(Collection<T> set, Comparator<? extends T> c)
	{
		this(c);
		this.addAll(set);
	}

	public SortedSet(Comparator c) 
	{
		this.comparator = c;
		this.newChild();
	}

	/**
	 * creates a new child with an empty Set<T>
	 */
	public void newChild() 
	{
		this.child = this.comparator == null ? new LinkedHashSet(this.size() + JavaUtil.initCapacity) : new TreeSet(this.comparator);
	}

	public Comparator getComparator()
	{
		return this.comparator;
	}
	
	public void setComparator(Comparator c)
	{
		this.comparator = c;
		Set<T> set = new SortedSet(this, this.comparator);
		this.clear();
		this.newChild();
		this.addAll(set);
	}

	@Override
	public boolean add(T e) {
		return this.child.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return this.child.addAll(c);
	}

	@Override
	public void clear() {
		this.child.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.child.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.child.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.child.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return this.child.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return this.child.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.child.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.child.retainAll(c);
	}

	@Override
	public int size() {
		return this.child.size();
	}

	@Override
	public Object[] toArray() {
		return this.child.toArray();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return this.child.toArray(a);
	}
	
	@Override
	public int hashCode()
	{
		return this.child.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.child.equals(o);
	}
	
	@Override
	public String toString()
	{
		return this.child.toString();
	}
}
