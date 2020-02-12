package purejava.main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jml.evilnotch.lib.JavaUtil;
import scala.actors.threadpool.Arrays;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		cleanDir(new File("./eclipse"));
		List<Integer> list = JavaUtil.toArray(0, 1, 2, 3 , 4, 5, 6, 7, 8, 9, -1);
		System.out.println(list);
		JavaUtil.reverse(list);
		System.out.println(list);
		if(true)
		{
			return;
		}
		try
		{
			String[] values = JavaUtil.split(",22,1,10,true,false,\"-1,2,3,4,5\",3", ',', '"', '"');
			System.out.println();
//			System.out.println(values.length);
//			Line line = new Line("modid:block");
//			line.parse("modid:block");
//			System.out.println("line:" + line.path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
