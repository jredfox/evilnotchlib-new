package jml.evilnotch.lib;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import jml.evilnotch.lib.json.JSONObject;
import jml.evilnotch.lib.json.serialize.JSONSerializer;
import jml.evilnotch.lib.primitive.ByteObj;
import jml.evilnotch.lib.primitive.DoubleObj;
import jml.evilnotch.lib.primitive.FloatObj;
import jml.evilnotch.lib.primitive.IntObj;
import jml.evilnotch.lib.primitive.LongObj;
import jml.evilnotch.lib.primitive.ShortObj;
import jml.evilnotch.lib.reflect.ReflectionHandler;
import net.minecraft.util.ResourceLocation;

public class JavaUtil {
	public static String SPECIALCHARS = "~!@#$%^&*()_+`'-=/,.<>?\"{}[]:;|" + "\\";
	public static String numberIds = "bsilfd";
	public static int arrayInitCapacity = 10;
	
	/**
	 * cast without loosing data and have a random negative number
	 */
	public static int castInt(long l)
	{
		if(l > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if(l < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int)l;
	}
	
	public static short castShort(long l)
	{
		if(l > Short.MAX_VALUE)
			return Short.MAX_VALUE;
		else if(l < Short.MIN_VALUE)
			return Short.MIN_VALUE;
		return (short)l;
	}
	public static byte castByte(long l)
	{
		if(l > Byte.MAX_VALUE)
			return Byte.MAX_VALUE;
		if(l < Byte.MIN_VALUE)
			return Byte.MIN_VALUE;
		return (byte)l;
	}
	
	public static short castShort(int i)
	{
		if(i > Short.MAX_VALUE)
			return Short.MAX_VALUE;
		else if(i < Short.MIN_VALUE)
			return Short.MIN_VALUE;
		return (short)i;
	}
	public static byte castByte(int i)
	{
		if(i > Byte.MAX_VALUE)
			return Byte.MAX_VALUE;
		if(i < Byte.MIN_VALUE)
			return Byte.MIN_VALUE;
		return (byte)i;
	}
	public static byte castByte(short s)
	{
		if(s > Byte.MAX_VALUE)
			return Byte.MAX_VALUE;
		if(s < Byte.MIN_VALUE)
			return Byte.MIN_VALUE;
		return (byte)s;
	}
	
	public static byte castByte(float f) 
	{
		long l = toLong(f);
		return JavaUtil.castByte(l);
	}
	
	public static byte castByte(double d) 
	{
		long l = toLong(d);
		return JavaUtil.castByte(l);
	}
	
	public static short castShort(float f) 
	{
		long l = toLong(f);
		return JavaUtil.castShort(l);
	}
	
	public static short castShort(double d) 
	{
		long l = toLong(d);
		return JavaUtil.castShort(l);
	}
	
	public static int castInt(float f) 
	{
		long l = toLong(f);
		return JavaUtil.castInt(l);
	}
	
	public static int castInt(double d) 
	{
		long l = toLong(d);
		return JavaUtil.castInt(l);
	}
	
	public static long castLong(float f)
	{
		return toLong(f);
	}
	
	public static long castLong(double d)
	{
		return toLong(d);
	}
	
	public static float castFloat(double d)
	{
		if(d > Float.MAX_VALUE)
			return Float.MAX_VALUE;
		else if (d < Float.MIN_VALUE)
			return Float.MIN_VALUE;
		return (float)d;
	}
	
	/**
	 * doesn't work every time as java algorithms truncate to 0 sometimes when negative only????
	 */
	public static long toLong(double d)
	{
		if(d > Long.MAX_VALUE)
			return Long.MAX_VALUE;
		if(d < Long.MIN_VALUE)
			return Long.MIN_VALUE;
		return (long) d;
	}
	
	/**
	 * doesn't work every time as java algorithms truncate to 0 sometimes when negative only????
	 */
	public static long toLong(float f)
	{
		if(f > Long.MAX_VALUE)
			return Long.MAX_VALUE;
		if(f < Long.MIN_VALUE)
			return Long.MIN_VALUE;
		return (long) f;
	}
	
	public static int castInt(Number obj)
	{
		obj = getInt(obj);
		if(obj instanceof Long)
			return JavaUtil.castInt(obj.longValue());
		return obj.intValue();
	}
	
	public static short castShort(Number obj)
	{
		obj = getInt(obj);
		if(obj instanceof Long)
			return JavaUtil.castShort(obj.longValue());
		else if(obj instanceof Integer)
			return JavaUtil.castShort(obj.intValue());
		return obj.shortValue();
	}
	
	public static byte castByte(Number obj)
	{
		obj = getInt(obj);
		if(obj instanceof Long)
			return JavaUtil.castByte(obj.longValue());
		else if(obj instanceof Integer)
			return JavaUtil.castByte(obj.intValue());
		else if(obj instanceof Short)
			return JavaUtil.castByte(obj.shortValue());
		return obj.byteValue();
	}
	
	public static long castLong(Number obj)
	{
		obj = getInt(obj);
		return obj.longValue();
	}
	
	public static double castDouble(Number obj)
	{
		return obj.doubleValue();
	}
	
	public static float castFloat(Number obj)
	{
		return obj.floatValue();
	}
	
	/**
	 * if double/float convert to integer of long else do nothing
	 */
	public static Number getInt(Number obj) 
	{
		if(isDouble(obj))
			obj = new Long(JavaUtil.toLong(obj.doubleValue() ));
		else if(isFloat(obj))
			obj = new Long(JavaUtil.toLong(obj.floatValue() ));
		return obj;
	}
	
	public static boolean isFloat(Number num)
	{
		return num instanceof Float || num instanceof FloatObj;
	}
	
	public static boolean isDouble(Number num)
	{
		return num instanceof Double || num instanceof DoubleObj;
	}
	
	/**
	 * dynamically get your current public ip address I recommend caching it somewhere so it doesn't go throw a huge process each time
	 */
	public static String getIpPublic() throws IOException 
	{
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		String ip = in.readLine(); //you get the IP as a String
		return ip.trim();
	}
	
	/**
	 * your current computer adress's ip
	 */
	public static String getIpv4() throws UnknownHostException 
	{
        InetAddress inetAddress = InetAddress.getLocalHost();
		return inetAddress.getHostAddress();
	}
	
	public static boolean isOnline()
	{
		return isOnline("www.google.com");
	}
	
	public static boolean isOnline(String url)
	{
		try
		{
			if(url.startsWith("http")) 
				throw new IllegalArgumentException("isOnline Requires not HTTP/HTTPS protical");
			Socket soc = new Socket();
			InetSocketAddress adress = new InetSocketAddress(url, 80);
			soc.setSoTimeout(3500);
			soc.connect(adress);
			soc.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static void getDirFiles(File dir, Set<File> files, String ext, boolean blackList) 
	{
	    for (File file : dir.listFiles()) 
	    {
	    	boolean isType = blackList ? (!file.getName().endsWith(ext)) : (file.getName().endsWith(ext) || ext.equals("*") );
	        if (file.isFile() && isType)
	        {
	            files.add(file);
	        }
	        else if (file.isDirectory()) 
	        {
	        	getDirFiles(file, files, ext, blackList);
	        }
	    }
	}
	
	public static void getDirFiles(File dir, Set<File> files) 
	{
		getDirFiles(dir, files, "*", false);
	}
	
	public static Set<File> getDirFiles(File dir)
	{
		Set<File> files = new HashSet();
		getDirFiles(dir, files);
		return files;
	}
	
	public static Set<File> getDirFiles(File dir, String ext)
	{
		return getDirFiles(dir, ext, false);
	}
	
	public static Set<File> getDirFiles(File dir, String ext, boolean blackList)
	{
		Set<File> files = new HashSet();
		getDirFiles(dir, files, ext, blackList);
		return files;
	}
	
	public static void deleteDir(File dir)
	{
		if(!dir.exists() || !dir.isDirectory())
		{
			throw new IllegalArgumentException("directory doesn't exist or is not a directory!" + dir.getAbsoluteFile().toString());//sanity check
		}
		try 
		{
			FileUtils.deleteDirectory(dir);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Color getColor(int color)
	{	
		int red = (int)(color >> 16 & 255);
        int green = (int)(color >> 8 & 255);
        int blue = (int)(color & 255);
        int alpha = (int)(color >> 24 & 255);
        return new Color(red, green, blue, alpha);
	}
	
	public static int getColor(int r, int g, int b, int a)
	{
        return a << 24 | r << 16 | g << 8 | b;
    }
	
	public static int getColor(Color c)
	{
		return getColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
	public static int multiplyColor(int color, double[] mul) 
	{
		Color c = getColor(color);
		double rmul = mul[0];
		double gmul = mul[1];
		double bmul = mul[2];
		int r = ((int)(c.getRed() * rmul) & 255);
		int g = ((int)(c.getGreen() * gmul) & 255);
		int b = ((int)(c.getBlue() * bmul) & 255);
		return getColor(r, g, b, c.getAlpha());
	}

	public static boolean isSpecialChar(char c)
	{
		return SPECIALCHARS.contains("" + c);
	}
	
	/**
	 * allows for white spacing
	 */
	public static boolean isAlphanumeric(String str)
	{
		str = whiteSpaced(str);
		for(char c : str.toCharArray())
			if(!isAlphanumeric(c))
				return false;
		return true;
	}
	
	/**
	 * allows for white spacing
	 */
	public static boolean containsAlphanumeric(String str)
	{
		str = whiteSpaced(str);
		for(char c : str.toCharArray())
			if(isAlphanumeric(c))
				return true;
		return false;
	}
	
	/**
	 * Ejects a string that is whitespaced
	 */
	public static String whiteSpaced(String s)
	{
		return s.replaceAll("\\s+", "");
	}
	
	/**
	 * Supports all languages. every letter is alphabetical but, not every alphabetical char is a letter such as greek vowels
	 */
	public static boolean isAlphanumeric(char c)
	{
		return Character.isAlphabetic(c) || Character.isDigit(c);
	}
	
	/**
	 * Supports all languages. every letter is alphabetical but, not every alphabetical char is a letter such as greek vowels
	 */
	public static boolean isLetterNumeric(char c)
	{
		return Character.isLetterOrDigit((int) c);
	}
	
	public static void moveFileFromJar(Class clazz, String input, File output, boolean replace) 
	{
		if(output.exists() && !replace)
		{
			return;
		}
		try
		{
			InputStream stream =  clazz.getResourceAsStream(input);
			FileOutputStream outputStream = new FileOutputStream(output);
			IOUtils.copy(stream, outputStream);
			stream.close();
			outputStream.close();
		}
		catch (Exception io)
		{
			io.printStackTrace();
		}
	}
	
	public static void printTime(long time, String msg) 
	{
		System.out.println(msg + (System.currentTimeMillis() - time) + "ms");
	}
	
	public static void copyClipboard(String s) 
	{
		copyClipboard(s, null);
	}
	
	public static void copyClipboard(String s, ClipboardOwner owner) 
	{
		Validate.nonNull(s);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    Transferable transferable = new StringSelection(s);
	    clipboard.setContents(transferable, owner);
	}
	
	/**
	 * the array type cannot be casted out of Object[] use toArray(Collection col, Class clazz) instead
	 */
	public static Object[] toArray(Collection col)
	{
		return toArray(col, Object.class);
	}
	
	public static <T> T[] toArray(Collection<T> col, Class<T> clazz)
	{
	    T[] li = (T[]) Array.newInstance(clazz, col.size());
	    int index = 0;
	    for(T obj : col)
	    {
	        li[index++] = obj;
	    }
	    return li;
	}
	
	public static <T> List<T> toArray(T[] arr)
	{
		List<T> list = new ArrayList(arr.length + arrayInitCapacity);
		for(T obj : arr)
			list.add(obj);
		return list;
	}
	
	public static <K, V> SortedMap<K, V> sortByKeys(Map<K, V> map)
	{
		return new TreeMap<K, V>(map);
	}
	
	public static <K, V> SortedMap<K, V> sort(Map<K, V> map, Comparator c)
	{
		TreeMap<K, V> ordered = new TreeMap<K, V>(c);
		ordered.putAll(map);
		return ordered;
	}
	
	public static <K, V> SortedMap<K, V> sortByValues(Map<K, V> map) 
	{
		SortedMap m = sort(map, new Comparator<Map.Entry>()
		{
			@Override
			public int compare(Map.Entry e1, Map.Entry e2) 
			{
				return ((Comparable)e1.getValue()).compareTo((Comparable)e2.getValue());
			}
		});
		return m;
	}
	
	/**
	 * Returns Cross platform file name removing any illegal chars/names.
	 */
	public static String toFileChars(String s) throws IllegalArgumentException
	{ 
		//TODO:
		String invalid = "*/<>?\":|" + "\\";
		String name = "";
		Validate.isFalse(s.isEmpty());
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < s.length(); i++)
		{
			String sub = s.substring(i, i + 1);
			if(!invalid.contains(sub))
			{
				builder.append(sub);
			}
		}
		name = toFileCharsWindows(builder.toString());
		if(name.endsWith(" ") || name.endsWith("\\."))
		{
			
		}
		if(name.length() > 255)
		{
			name = name.substring(0, 255 + 1);
		}
		else if(name.isEmpty())
		{
			name = "failed";
		}
		return name;
	}
	
	/**
	 * remove reserved os operating file names
	 */
	public static String toFileCharsWindows(String str) 
	{
		String[] parts = JavaUtil.splitFirst(str, '.');
		String check = parts[0];
		if(check.equalsIgnoreCase("CON") || check.equalsIgnoreCase("PRN") || check.equalsIgnoreCase("AUX") || check.equalsIgnoreCase("NUL"))
		{
			str = check + "_" + (parts.length > 0 ? parts[1] : "");
		}
		else
		{
			for (int j=0; j < 10; j++)
			{
				String com = "COM" + j;
				String lpt = "LPT" + j;
				if(check.equalsIgnoreCase(com) || check.equalsIgnoreCase(lpt))
				{
					str = check + "_" + (parts.length > 0 ? parts[1] : "");
					break;
				}
			}
		}
		return str;
	}

	@SuppressWarnings("rawtypes")
	public static void printMap(Map map)
	{
		Iterator it = map.entrySet().iterator();
		int index = 0;
		System.out.print("[");
		while(it.hasNext())
		{
			Map.Entry pair = (Entry) it.next();
			System.out.print(" Key" + index + ":" + pair.getKey() + " Value"  + index + ":" + pair.getValue());
			index++;
		}
		System.out.print("]\n");
	}
	
	public static byte[] arrayToStaticBytes(List<Byte> list)
	{
		byte[] strstatic =  new byte[list.size()];
		for(int i=0;i<list.size();i++)
			strstatic[i] = list.get(i);
		return strstatic;
	}
	public static int[] arrayToStaticInts(List<Integer> list)
	{
		int[] strstatic =  new int[list.size()];
		for(int i=0;i<list.size();i++)
			strstatic[i] = list.get(i);
		return strstatic;
	}
	@SuppressWarnings("rawtypes")
	public static Object[] arrayToStatic(List list)
	{
		Object[] strstatic = new Object[list.size()];
		for(int i=0;i<list.size();i++)
			strstatic[i] = list.get(i);
		return strstatic;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setList(List filelist,List list,int index)
	{
		for(int i=0;i<list.size();i++)
		{
			Object entry = list.get(i);
			boolean flag = false;
			if(index + i < filelist.size() && !flag)
				filelist.set(index+i,entry);
			else{
				filelist.add(entry);
				flag = true;
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void deleteListRange(List filelist,int index1,int index2)
	{
		List<String> sub = filelist.subList(index1, index2);
		sub.clear();
	}
	public static boolean ArrayhasEqualString(String[] list, String strhead) 
	{
		for(int i=0;i<list.length;i++)
		{
			String s = list[i];
			if(s == null)
				continue;//Static arrays are known for nulls
			if(strhead.equals(s))
				return true;
		}
		return false;
	}
	public static int findLastChar(String str, char character) 
	{
		for(int i=str.length()-1;i>0;i--)
		{
			if(str.substring(i, i+1).equals("" + character))
				return i;
		}
		return -1;
	}
	public static int findLastChar(String str, String charSequence) 
	{
		for(int i=str.length()-1;i>0;i--)
		{
			if(charSequence.contains(str.substring(i, i+1)))
				return i;
		}
		return -1;
	}
	public static String[] splitStringAtIndex(int index,String tosplit) 
	{
		String[] list = new String[2];
		String first = "";
		String second = "";
		for(int i=0;i<tosplit.length();i++)
		{
			if(i < index)
				first += tosplit.substring(i, i+1);
			if(i > index)
				second += tosplit.substring(i, i+1);
		}
		list[0] = first;
		list[1] = second;
		return list;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList copyArrays(List li) 
	{
		ArrayList list = new ArrayList();
		for(Object object : li)
			list.add(object);
		return list;
	}
	public static String reverseString(String s) 
	{
		String str = "";
		for(int i=s.length()-1;i>=0;i--)
			str += s.substring(i, i+1);
		return str;
	}
	@SuppressWarnings("rawtypes")
	public static boolean hasKeys(Map list, Map list2)
	{
		for(int i=0;i<list.size();i++)
		{
			Object obj = list.get(i);
			if(!list2.containsKey(obj))
				return false;
		}
		return true;
	}
	@SuppressWarnings("rawtypes")
	public static boolean hasKeys(List list, List list2)
	{
		for(int i=0;i<list.size();i++)
		{
			Object obj = list.get(i);
			if(!list2.contains(obj))
				return false;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void reverseArray(ArrayList origin) {
		ArrayList list = new ArrayList();
		for(int i=origin.size()-1;i>=0;i--)
			list.add(origin.get(i));
		origin.clear();
		for(Object obj : list)
			origin.add(obj);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void reverseHashMap(HashMap<Integer, ArrayList> list) {
		HashMap<Integer,ArrayList> map = new HashMap();
		//sort ints
		ArrayList<Integer> ints = new ArrayList();
		for(Integer i : list.keySet() )
			ints.add(i);
		Collections.sort(ints,Collections.reverseOrder());
		for(Integer i : ints)
			map.put(i,list.get(i));
		list.clear();
		list.putAll(map);
	}
	/**
	 * Use for hashmaps for keys that override the .equals method this compares memory location
	 */
	@SuppressWarnings("rawtypes")
	public static boolean containsMemoryLocKey(HashMap map,Object obj) {
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object obj2 = it.next();
			if(obj == obj2)
				return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static void removeKeyMemoryLoc(Map map, Object key) {
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object obj2 = it.next();
			if(key == obj2)
				it.remove();
		}
	}
	@SuppressWarnings("rawtypes")
	public static Object getMemoryLocKey(Map map, Object value) {
		Iterator it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			if(entry.getValue() == value)
				return entry.getKey();
		}
		return null;
	}

	public static char toUpperCaseChar(char c) {
		return ("" + c).toUpperCase().charAt(0);
	}
	public static String getIntsAsString(int[] ints) {
		String str = "";
		for(int i : ints)
			str += "" + i + ",";
		return str.substring(0, str.length()-1);
	}
	
	public static List asList(Object[] objs)
	{
		List li = new ArrayList();
		for(Object obj : objs)
			li.add(obj);
		return li;
	}
	public static List asList(Set set) {
		List list = new ArrayList();
		for(Object obj : set)
			list.add(obj);
		return list;
	}
	/**
	 * returns name from first index till it disovers a dot
	 * @param file
	 * @return
	 */
	public static String getFileTrueDisplayName(File file) {
		return file.getName().split("\\.")[0];
	}
	public static <T extends Object> ArrayList<T> asArray(Object... staticArr) {
		ArrayList<T> list = new ArrayList();
		for(int i=0;i<staticArr.length;i++)
			list.add((T) staticArr[i]);
		return list;
	}
	
	public static boolean isStringNullOrEmpty(String string) 
	{
		if(string == null || string.isEmpty())
			return true;
		return false;
	}
	
	/**
	 * Equivalent to Files.readAllLines() but, works way faster
	 */
	public static List<String> getFileLines(File f)
	{
		return getFileLines(getReader(f));
	}
	
	public static List<String> getFileLines(String input) 
	{
		return getFileLines(getReader(input));
	}
	
	public static List<String> getFileLines(BufferedReader reader) 
	{
		List<String> list = null;
		try
		{
			list = new ArrayList();
			String s = reader.readLine();
			
			if(s != null)
			{
				list.add(s);
			}
			
			while(s != null)
			{
				s = reader.readLine();
				if(s != null)
				{
					list.add(s);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(reader != null)
			{
				try 
				{
					reader.close();
				} catch (IOException e) 
				{
					System.out.println("Unable to Close InputStream this is bad");
				}
			}
		}
		return list;
	}
	
	/**
	 * even though it's utf8 writing it's the fastes one I tried 5 different other options from different objects
	 */
	public static BufferedWriter getWriter(File f) throws FileNotFoundException, IOException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8));
	}
	
	public static BufferedReader getReader(File f)
	{
		 try
		 {
			 return new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
		 }
		 catch(Throwable t)
		 {
			 return null;
		 }
	}
	
	public static BufferedReader getReader(String input)
	{
		return new BufferedReader(new InputStreamReader(JavaUtil.class.getClassLoader().getResourceAsStream(input)));
	}
	
	/**
	 * Overwrites entire file default behavior no per line modification removal/addition
	 */
	public static void saveFileLines(List<String> list, File f)
	{
		BufferedWriter writer = null;
		try
		{
			writer = JavaUtil.getWriter(f);
			for(String s : list)
				writer.write(s + "\r\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(writer != null)
			{
				try
				{
					writer.close();
				}
				catch(Exception e)
				{
					System.out.println("Unable to Close OutputStream this is bad");
				}
			}
		}
	}
	
	public static boolean isBoolean(String s) 
	{
		return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
	}
	
	public static boolean getBoolean(String str) {
		if(!isBoolean(str))
			return false;
		return Boolean.parseBoolean(str);
	}
	
	public static boolean isURL(String url) 
	{
		try 
		{
			URL tst = new URL(url);
			return true;
		} 
		catch (Exception e)
		{
			
		}
		return false;
	}
	
	public static JSONObject toJsonFrom64(String base64) 
	{
		byte[] out = org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes());
		String str = new String(out, StandardCharsets.UTF_8);
		return new JSONObject(str);
	}

	public static long getDays(long ms) 
	{
		return ms / (1000L*60L*60L*24L);
	}

	public static long getTime(long time) {
		return System.currentTimeMillis()-time;
	}
	
	public static JSONObject getJSON(File file)
	{
		if(!file.exists())
			return null;
		try
		{
			JSONSerializer parser = new JSONSerializer();
			return parser.readJSONObject(JavaUtil.getReader(file));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void saveJSONSafley(JSONObject json, File file) throws IOException 
	{
		createFileSafley(file);
		saveJSON(json, file);
	}
	
	/**
	 * @return if the file saved or not
	 */
	public static boolean saveIfJSON(JSONObject json, File file) throws IOException 
	{
		if(!file.exists())
		{
			saveJSONSafley(json, file);
			return true;
		}
		return false;
	}

	public static void createFileSafley(File file) throws IOException 
	{
		File parent = file.getParentFile();
		if(!parent.exists())
			parent.mkdirs();
		if(!file.exists())
			file.createNewFile();
	}

	public static void saveJSON(JSONObject json, File file) 
	{
		try 
		{
			BufferedWriter writer = JavaUtil.getWriter(file);
			JSONSerializer parser = new JSONSerializer();
			parser.setPrettyPrint(true);
			parser.write(json, writer);
			writer.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static Object getFirst(Collection li) {
		for(Object obj : li)
			return obj;
		return null;
	}

	public static Set asSet(Object[] obj) {
		Set set = new HashSet();
		for(Object o : obj)
			set.add(o);
		return set;
	}

	public static void populateStatic(Object[] locs, List names) {
		for(int i=0;i<names.size();i++)
			locs[i] = names.get(i);
	}
	
	public static boolean isClassExtending(Class<? extends Object> base, Class<? extends Object> toCompare) 
	{
		return ReflectionHandler.instanceOf(base, toCompare);
	}
	
	/**
	 * @return the char id based on the generic number object
	 */
	public static char getNumId(Number obj) 
	{
		if(obj instanceof Integer || obj instanceof IntObj)
		{
			return 'i';
		}
		else if(obj instanceof Long || obj instanceof LongObj)
		{
			return 'l';
		}
		else if(obj instanceof Short || obj instanceof ShortObj)
		{
			return 's';
		}
		else if(obj instanceof Byte || obj instanceof ByteObj)
		{
			return 'b';
		}
		else if(obj instanceof Float || obj instanceof FloatObj)
		{
			return 'f';
		}
		else if(obj instanceof Double || obj instanceof DoubleObj)
		{
			return 'd';
		}
		return ' ';
	}
	
	public static int findFirstChar(int index,String str, char c) 
	{
		for(int j=index;j<str.length();j++)
			if(str.charAt(j) == c)
				return j;
		return -1;
	}
	/**
	 * get the id from the string to parse
	 * @return ' ' if none is found
	 */
	public static char getNumId(String str) {
		str = str.trim().toLowerCase();
		String last = "" + str.charAt(str.length()-1);
		if(numberIds.contains(last))
			return last.toLowerCase().charAt(0);
		return ' ';
	}
	/**
	 * an optimized way to split a string from it's first instanceof a char
	 */
	public static String[] splitFirst(String s, char reg)
	{
		String[] parts = new String[2];
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c == reg)
			{
				parts[0] = s.substring(0, i);
				parts[1] = s.substring(i + 1, s.length());
				break;
			}
		}
		if(parts[0] == null)
			return new String[]{s};
		return parts;
	}
	
	public static String parseQuotes(String s, int index, String q) 
	{
		if(index == -1)
			return "";
		char lquote = q.charAt(0);
		char rquote = q.length() > 1 ? q.charAt(1) : lquote;
		
		String strid = "";
		int quote = 0;
		for(int i=index;i<s.length();i++)
		{
			if(quote == 2)
				break; //if end of parsing object stop loop and return getParts(strid,":");
			char tocompare = s.charAt(i);
			boolean contains = tocompare == lquote && quote == 0 || tocompare == rquote;
			
			if(contains)
				quote++;
			if(!contains && quote > 0)
				strid += tocompare;
		}
		return strid;
	}
	public static boolean isStringInt(String s)
	{
		String valid = "1234567890-";
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(!valid.contains("" + c))
				return false;
			else if(c == '-' && i > 0)
				return false;
		}
		return true;
	}
	public static boolean isNumber(String s)
	{
		String valid = "1234567890.-";
		String valid_endings = numberIds;//byte,short,long,float,double,int
		String check = ".";
		int indexdot = 0;
		if(s.indexOf('.') == 0 || s.indexOf('.') == s.length() - 1 || s.indexOf('-') > 0)
			return false;
		s = s.toLowerCase();
		for(int i=0;i<s.length();i++)
		{
			String character = s.substring(i, i+1);
			boolean lastindex = i == s.length() -1;
			if(check.contains(character))
			{
				if(character.equals("."))
					indexdot++;
				
				if(indexdot > 1)
					return false;
			}
			if(!valid.contains(character))
			{
				return lastindex && valid_endings.contains(character);
			}
		}
		return true;
	}
	/**
	 * create folders for file
	 */
	public static void createFolders(File file) 
	{
		File parent = file.getParentFile();
		if(!parent.exists())
			file.getParentFile().mkdirs();
	}
	public static List<String> asStringList(String[] str) {
		List list = new ArrayList(str.length);
		for(String s : str)
			list.add(s);
		return list;
	}
	public static List<String> asStringList(Class[] str) {
		List list = new ArrayList(str.length);
		for(Class s : str)
			list.add(s.getName());
		return list;
	}
	public static List<ResourceLocation> stringToLocArray(String[] list) {
		List<ResourceLocation> locs = new ArrayList();
		for(String str : list)
			locs.add(new ResourceLocation(str));
		return locs;
	}
	public static long[] arrayToStaticLong(List<Long> li) 
	{
		long[] list = new long[li.size()];
		for(int i=0;i<li.size();i++)
			list[i] = li.get(i);
		return list;
	}
	
	public static int[] getStaticArrayInts(String str) 
	{
		str = str.substring(1, str.length()-1);
		String[] parts = str.split(",");
		int[] arr = new int[parts.length];
		int index = 0;
		for(String s : parts)
		{
			s = s.trim();
			arr[index] = Integer.parseInt(s);
			index++;
		}
		return arr;
	}
	
	public static boolean isStaticArray(Object value) 
	{
		return value != null && value.getClass().isArray();
	}
	
	public static boolean contains(String[] list, String name) 
	{
		for(String s : list)
			if(name.equals(s))
				return true;
		return false;
	}
	
    public static boolean equals(Class[] compare, Class[] params)
    {
    	if(compare.length != params.length)
			return false;
		for(int i=0;i<params.length;i++)
		{
			Class c1 = params[i];
			Class c2 = compare[i];
			if(!c1.equals(c2))
				return false;
		}
		return true;
	}

	public static Class[] getWrappedClasses(Class[] params) 
    {
		for(int i=0; i< params.length; i++)
		{
			params[i] = getWrappedClass(params[i]);
		}
		return params;
	}

	public static Class getWrappedClass(Class clazz) 
    {
    	if(clazz.isPrimitive())
    	{
    		if(boolean.class.equals(clazz))
    			return Boolean.class;
    		else if(char.class.equals(clazz))
    			return Character.class;
    		else if(byte.class.equals(clazz))
    			return Byte.class;
    		else if(short.class.equals(clazz))
    			return Short.class;
    		else if(int.class.equals(clazz))
    			return Integer.class;
    		else if(long.class.equals(clazz))
    			return Long.class;
    		else if(float.class.equals(clazz))
    			return Float.class;
    		else if(double.class.equals(clazz))
    			return Double.class;
    		else
    			return null;//unkown data type
    	}
		return clazz;
	}
	
	/**
	 * combine two static arrays into one
	 */
	public static <T> T[] concat(T[] a, T[] b)
	{
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
	    System.arraycopy(a, 0, c, 0, a.length);
	    System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	
	/**
	 * increase a static array by x amount of indices
	 */
	public static <T> T[] increase(T[] src, int increment)
	{
		T[] arr = (T[]) Array.newInstance(src.getClass().getComponentType(), src.length + increment);
		System.arraycopy(src, 0, arr, 0, src.length);
		return arr;
	}
	
	/**
	 * split with quote ignoring support
	 */
	public static String[] split(String str, char sep, char lquote, char rquote) 
	{
		List<String> list = new ArrayList();
		boolean inside = false;
		for(int i = 0; i < str.length(); i += 1)
		{
			String a = str.substring(i, i + 1);
			String prev = str.substring(i-1, i);
			boolean escape = prev.charAt(0) ==  '\\';
			if(a.equals("" + lquote) && !escape || a.equals("" + rquote) && !escape)
			{
				inside = !inside;
			}
			if(a.equals("" + sep) && !inside)
			{
				String section = str.substring(0, i);
				list.add(section);
				str = str.substring(i + ("" + sep).length(), str.length());
				i = -1;
			}
		}
		list.add(str);//add the rest of the string
		return JavaUtil.toArray(list, String.class);
	}
	
}