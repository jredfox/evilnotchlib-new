package purejava.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.array.MapEntry;
import jml.evilnotch.lib.array.SimpleSet;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		SimpleSet<MapEntry> set = new SimpleSet();
		set.add(new MapEntry("a", 0));
		set.add(new MapEntry("b", 1));
		set.add(new MapEntry("a", 2));
		System.out.println(set);
	}
}
