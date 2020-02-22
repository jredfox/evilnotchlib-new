package jml.evilnotch.lib.array;

import java.util.Comparator;
import java.util.Map;

public class Comparators {
	
	public static final Comparator assending = new Comparator()
	{
		@Override
		public int compare(Object o1, Object o2) 
		{
			return ((Comparable)o1).compareTo((Comparable)o2);
		}
	};
	public static final Comparator descending = new Comparator()
	{
		@Override
		public int compare(Object o1, Object o2) 
		{
			return - ((Comparable)o1).compareTo((Comparable)o2);
		}
	};
	public static final Comparator keys = new Comparator<Map.Entry>()
	{
		@Override
		public int compare(Map.Entry e1, Map.Entry e2) 
		{
			return ((Comparable)e1.getKey()).compareTo((Comparable)e2.getKey());
		}
	};
	public static final Comparator keys_reverse = new Comparator<Map.Entry>()
	{
		@Override
		public int compare(Map.Entry e1, Map.Entry e2) 
		{
			return - ((Comparable)e1.getKey()).compareTo((Comparable)e2.getKey());
		}
	};
	public static final Comparator values = new Comparator<Map.Entry>()
	{
		@Override
		public int compare(Map.Entry e1, Map.Entry e2) 
		{
			return ((Comparable)e1.getValue()).compareTo((Comparable)e2.getValue());
		}
	};
	public static final Comparator values_reverse = new Comparator<Map.Entry>()
	{
		@Override
		public int compare(Map.Entry e1, Map.Entry e2) 
		{
			return - ((Comparable)e1.getValue()).compareTo((Comparable)e2.getValue());
		}
	};

}
