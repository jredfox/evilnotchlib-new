package purejava.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.asm.Coremod;
import jml.evilnotch.lib.asm.ITransformer;
import jml.evilnotch.lib.reflect.ReflectionHandler;

@Mod(modid = "")
public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		System.out.println(ReflectionHandler.capacity(new ArrayList(100)));
//		cleanDir(new File("./eclipse"));
//		Map map = new HashMap();
//		map.put("a", 1);
//		map.put("b", -1);
//		System.out.println(JavaUtil.sortByValues(map));
//		try
//		{
//			String[] values = JavaUtil.split(",22,1,10,true,false,\"-1,2,3,4,5\",3", ',', '"', '"');
//			System.out.println();
////			System.out.println(values.length);
////			Line line = new Line("modid:block");
////			line.parse("modid:block");
////			System.out.println("line:" + line.path);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
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
