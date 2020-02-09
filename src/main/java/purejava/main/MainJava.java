package purejava.main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jml.evilnotch.lib.JavaUtil;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		try
		{
			String[] values = JavaUtil.split(",22,1,10,true,false,\"-1,2,3,4,5\",3", ',', '"', '"');
			System.out.println(JavaUtil.asList(values));
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

}
