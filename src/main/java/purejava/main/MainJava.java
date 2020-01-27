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
import jml.evilnotch.lib.simple.SimpleConfig;

public class MainJava {
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		int large = Short.MAX_VALUE*10000;
		char[] chars = new char[large];
		Arrays.fill(chars, 'Z');
		String text = new String(chars);
		BufferedWriter writer = JavaUtil.getWriter(new File("test.txt"));
		writer.write(text);
		writer.close();
	}

}
