package purejava.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.asm.ASMHelper;
import jml.evilnotch.lib.json.JSONObject;
import jml.evilnotch.lib.json.serialize.JSONSerializer;
import jml.evilnotch.lib.line.Line;
import jml.evilnotch.lib.simple.SimpleConfig;
import scala.Char;

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
