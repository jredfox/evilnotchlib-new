package jml.evilnotch.lib.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import jml.evilnotch.lib.JavaUtil;

/**
 * A List<T> object that supports insertion order by a custom comparator
 * @author jredfox
 */
public class SortedList<T> extends ArrayList<T>{

	protected Comparator c;
	
	/**
	 * constructs a SortedList with a initial capacity of 10
	 */
	public SortedList() 
	{
		this((Comparator) null);
	}
	
	/**
	 * constructs a SortedList with a initial capacity of 10 and a Comparator
	 */
	public SortedList(Comparator c)
	{
		this(JavaUtil.initCapacity, c);
	}
	
	public SortedList(Collection<T> list, Comparator c)
	{
		this(list.size() + JavaUtil.initCapacity, c);
		this.addAll(list);
	}

	public SortedList(int capacity, Comparator c)
	{
		super(capacity);
		this.c = c;
	}
	
	/**
	 * returns the comparator or null if non existent
	 */
	public Comparator getComparator()
	{
		return this.c;
	}
	
	/**
	 * sets the Comparator and will sort if old != new && new != null
	 */
	public void setComparator(Comparator c)
	{
		Comparator old = this.c;
		this.c = c;
		if(this.c != old && this.c != null)
			Collections.sort(this, this.c);
	}
	
	public boolean sorted()
	{
		return this.c != null;
	}
	
	public boolean nonSorted()
	{
		return this.c == null;
	}

	@Override
	public boolean add(T a) 
	{
		if(this.nonSorted())
		{
			return super.add(a);
		}
		int insertion = this.getInsertion(a);
		return this.addAtIndex(insertion, a);
	}

	/**
	 * returns the index the order should be inserted into
	 * @return -1 if there if no insertion can be found
	 */
	public int getInsertion(T a)
	{
		Comparable o = (Comparable)a;
		for(int i = this.size() - 1; i >= 0; i--)
		{
			Comparable obj = (Comparable) this.get(i);
			int compare = o.compareTo(obj);
			if(compare >= 0)
			{
				return i + 1;
			}
		}
		return 0;
	}

	protected boolean addAtIndex(int insertion, T a) 
	{
		super.add(insertion, a);
		return true;
	}

	/**
	 * index is ignored unless there is no comparator
	 */
	@Override
	public void add(int index, T a)
	{
		if(this.nonSorted())
		{
			super.add(index, a);
			return;
		}
		this.add(a);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) 
	{
		if(this.nonSorted())
		{
			return super.addAll(c);
		}
		for(T obj : c)
		{
			this.add(obj);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		if(this.nonSorted())
		{
			return super.addAll(index, c);
		}
		for(T obj : c)
		{
			this.add(index++, obj);
		}
		return true;
	}
	
	/**
	 * turns the index null and adds the element to the array
	 */
	@Override
	public T set(int index, T element) 
	{
		if(this.nonSorted())
		{
			return super.set(index, element);	
		}
		T old = this.remove(index);
		this.add(element);
		return old;
	}

}
