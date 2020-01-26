package jml.evilnotch.lib.json;

import java.util.Collection;
import java.util.Map;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.json.internal.Util;


public final class JSONUtil {
	
	/**
	 * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F).
	 * @param string the {@linkplain String} you want to escape
	 * @return the escaped {@linkplain String}
	 * @since 1.0.0
	 */
	@Deprecated
	public static final String escape(String string) {
		
		if(string != null) {
			
			StringBuilder builder = new StringBuilder();
	        Util.escape(string, builder);
	        return builder.toString();
		}

		return null;
    }
	
	/**
	 * fixes any objects before inserting them into a json. Doesn't support static or dynamic arrays
	 */
	public static Object getValidJsonValue(Object value) 
	{
		if(JavaUtil.isStaticArray(value))
			throw new IllegalArgumentException("Use JSONArray Objects for non primitive values");
		else if(value instanceof Map && !(value instanceof JSONObject) || value instanceof Collection && !(value instanceof JSONArray))
			throw new IllegalArgumentException("Inserted Maps must be JSONObject and Inserted Collections Must be JSONArray");
		
		return canPut(value) ? value : value.toString();
	}
	
	/**
	 * can the object without modifications be inputted into the json object/json array
	 */
	public static boolean canPut(Object value) 
	{
		return value == null ||
			 value instanceof String ||
			 value instanceof Number || 
			 value instanceof Boolean ||
			 value instanceof JSONObject || 
			 value instanceof JSONArray;
	}
}
