package purejava.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.array.Comparators;
import jml.evilnotch.lib.array.MapEntry;
import jml.evilnotch.lib.array.SortedList;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		List<MapEntry> list = new SortedList(Comparators.assending);
		list.add(new MapEntry("y", 0));
		list.add(new MapEntry("b", 1));
		list.add(new MapEntry("c", 2));
		list.add(new MapEntry("a", 3));
		list.add(new MapEntry("a", 4));
		list.add(new MapEntry("y", 5));
		MapEntry entry = list.set(list.size()-1, new MapEntry("z", 6));
		System.out.println(list);
	}
}
