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
		JSONSerializer s = new JSONSerializer();
		json = new JSONObject(json.toString());
		
		json.put("car", car);
		car.put("a", true);
		car.put("null", null);
		car.put("year", 22);
		json.put("cfg", 100);
//		JavaUtil.getJSON(new File(".","eclipse/test.json"));
		long time = System.currentTimeMillis();
		JSONObject js = JavaUtil.getJSON(new File(".","eclipse/test.json"));
		JavaUtil.printTime(time, "Done Saving json:");
		System.out.println(js.prettyPrint());
	}

}
