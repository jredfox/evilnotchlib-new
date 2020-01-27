package purejava.main;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.asm.ASMHelper;
import jml.evilnotch.lib.json.JSONObject;
import jml.evilnotch.lib.json.serialize.JSONSerializer;
import jml.evilnotch.lib.simple.SimpleConfig;

public class MainJava {
	
	public static void main(String[] args)
	{
		JSONObject json = new JSONObject();
		JSONObject car = new JSONObject();
		json.put("car", car);
		car.put("a", true);
		car.put("null", null);
		car.put("year", 22);
		json.put("cfg", 100);
		System.out.println(new JSONSerializer().readJSONObject(json.toString()));
		try
		{
			long time = System.currentTimeMillis();
			JavaUtil.printTime(time, "Done Saving json:");
			JavaUtil.saveJSONSafley(json, new File(".","eclipse/test.json"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
