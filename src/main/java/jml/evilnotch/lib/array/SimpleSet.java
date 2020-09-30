package jml.evilnotch.lib.array;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.Validate;
import jml.evilnotch.lib.reflect.ReflectionHandler;

/**
 * a set with no need for initial capacity as it doesn't use arrays
 */
public class SimpleSet<T> implements Set<T>{
	
	protected Node<T> first;
	protected Node<T> last;
	protected int size;

	public SimpleSet()
	{
		this.first = new Node<T>();
		this.last = new Node<T>();
		this.first.link(this.last);
	}

	/**
	 * appends an object to the end of the list if it doesn't contain it
	 */
	@Override
	public boolean add(T obj) 
	{
		if(this.contains(obj))
		{
			return false;
		}
		Node newNode = new Node(obj);
		this.last.link(newNode);
		this.last = newNode;
		this.size++;
		return true;
	}
	
	@Override
	public boolean remove(Object obj) 
	{
		this.size--;
		return false;//TODO:
	}
	
	@Override
	public boolean contains(Object compare) 
	{
		for(T obj : this)
		{
			if(this.compare(compare, obj))
				return true;
		}
		return false;
	}
	
	public boolean compare(Object a, Object b)
	{
		return a == null && b == null || a.equals(b);
	}

	@Override
	public boolean addAll(Collection<? extends T> col)
	{
		boolean modified = false;
		for(T obj : col)
		{
			modified |= this.add(obj);
		}
		return modified;
	}

	@Override
	public void clear() 
	{
		this.last = new Node<T>();
		this.first.link(this.last);
		this.size = 0;
	}

	@Override
	public boolean containsAll(Collection<?> col) 
	{
		for(Object obj : col)
		{
			if(!this.contains(obj))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() 
	{
		return this.size == 0;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new IteratorSet<T>(this);
	}
	
	/**
	 * iterates from the begining of the SimpleSet<T> to the end of it
	 */
	public static class IteratorSet<T> implements Iterator<T>
	{
		public Node<T> current;
		
		public IteratorSet(SimpleSet<T> set)
		{
			this.current = set.first.right;
		}
		
		@Override
		public boolean hasNext() 
		{
			return this.current != null && this.current.right != null;
		}

		@Override
		public T next() 
		{
			this.current = this.current.right;
			return (T) this.current.obj;
		}
	}

	@Override
	public boolean removeAll(Collection<?> col) 
	{
		boolean modified = false;
		for(Object obj : col)
		{
			modified |= this.remove(obj);
		}
		return modified;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		throw new RuntimeException("What is this?");//TODO:
	}

	@Override
	public int size() 
	{
		return this.size;
	}

	@Override
	public Object[] toArray() 
	{
		return JavaUtil.toArrayStatic(this);
	}

	@Override
	public <E> E[] toArray(E[] arg0) 
	{
		return JavaUtil.toArrayStatic((Collection<E>)this, ReflectionHandler.getArrayClass(arg0));
	}
	
	@Override
	public String toString()
	{
		StringBuilder b = new StringBuilder(this.size() * 10);
		for(T obj : this)
		{
			b.append(obj + ", ");
		}
		String str = b.toString();
		if(!this.isEmpty())
			str = str.substring(0, str.length() - ", ".length());
		return '[' + str + ']';
	}
	
	public static class Node<T>
	{
		public Node right;
		public Node left;
		public T obj;
		
		public Node()
		{
			this(null);
		}
		
		/**
		 * links the right node with the left one
		 */
		public void link(Node<T> node)
		{
			this.right = node;
			node.left = this;
		}
		
		/**
		 * links the left node with the right node
		 */
		public void linkLeft(Node<T> node)
		{
			this.left = node;
			node.right = this;
		}

		public Node(T obj)
		{
			this.obj = obj;
		}
	}

}
