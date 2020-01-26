package jml.evilnotch.lib.json.serialization;

import jml.evilnotch.lib.json.JSONObject;

/**
 * Is needed to manage none-JSON types when serializing or deserializing objects.
 * @author Ralph Niemitz/RalleYTN(ralph.niemitz@gmx.de)
 * @version 2.0.0
 * @since 1.0.0
 */
public interface JSONTypeSerializationHandler {

	/**
	 * Is called when an object is being serialized and a none-JSON type is reached.
	 * @param type the none-JSON type
	 * @param value the value
	 * @return a JSON type value that will be written into the object
	 * @since 1.0.0
	 */
	public Object serialize(Class<?> type, Object value);
	
	/**
	 * Is called when the needed type is a none-JSON type.
	 * @param type the needed type
	 * @param value the value from the {@linkplain JSONObject}
	 * @return the value to which the Java object attribute will be set
	 * @since 1.0.0
	 */
	public Object deserialize(Class<?> type, Object value);
}
