package purejava.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.array.MapEntry;
import jml.evilnotch.lib.array.SortedSet;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
	}

	private static void cleanDir(File file) 
	{
		for(File f : file.listFiles())
		{
			if(f.getName().length() > 100)
				f.delete();
		}
	}

}
