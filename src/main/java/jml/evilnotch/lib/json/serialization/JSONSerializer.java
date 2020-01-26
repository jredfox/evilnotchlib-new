package jml.evilnotch.lib.json.serialization;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import jml.evilnotch.lib.json.JSONArray;
import jml.evilnotch.lib.json.JSONObject;

/**
 * Provides static methods which allow you to convert Java objects into {@linkplain JSONObject}s
 * and the other way around.
 * @author Ralph Niemitz/RalleYTN(ralph.niemitz@gmx.de)
 * @version 2.0.0
 * @since 1.0.0
 */
public final class JSONSerializer {

	private JSONSerializer() {}
	
	/**
	 * Transfers the data of {@linkplain JSONObject} into a Java object.
	 * @param json the JSON object containing the data
	 * @param object the body of the Java object that should be filled
	 * @throws Exception if an error occurs
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static final void deserialize(JSONObject json, Object object) throws Exception {
		
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getFields();
		Method[] methods = clazz.getMethods();
		
		for(Field field : fields) {
			
			JSONAttribute annotation = field.getAnnotation(JSONAttribute.class);
			
			if(!Modifier.isTransient(field.getModifiers()) && annotation != null && JSONSerializer.contains(JSONAttribute.Type.SETTER, annotation)) {

				Class<?> targetType = field.getType();
				Object value = json.get(annotation.name());
				
				if(value != null) {
					
					if(float.class.isAssignableFrom(targetType) || Float.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).floatValue());
						
					} else if(double.class.isAssignableFrom(targetType) || Double.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).doubleValue());
						
					} else if(byte.class.isAssignableFrom(targetType) || Byte.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).byteValue());
						
					} else if(short.class.isAssignableFrom(targetType) || Short.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).shortValue());
						
					} else if(int.class.isAssignableFrom(targetType) || Integer.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).intValue());
						
					} else if(long.class.isAssignableFrom(targetType) || Long.class.isAssignableFrom(targetType)) {
						
						field.set(object, ((Number)value).longValue());
						
					} else if(String.class.isAssignableFrom(targetType) || Number.class.isAssignableFrom(targetType) || Boolean.class.isAssignableFrom(targetType) || boolean.class.isAssignableFrom(targetType)) {
						
						field.set(object, value);
						
					} else if(Enum.class.isAssignableFrom(targetType)) {
						
						for(Object constant : targetType.getEnumConstants()) {
							
							if(((Enum<?>)constant).name().equals(value.toString())) {
								
								field.set(object, constant);
								break;
							}
						}
						
					} else if(targetType.getAnnotation(JSONRoot.class) != null) {
						
						Object newObject = targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize((JSONObject)value, newObject);
						field.set(object, newObject);
						
					} else if(Map.class.isAssignableFrom(targetType)) {
						
						Map<Object, Object> map = (Map<Object, Object>)targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize(clazz, object, map, (JSONObject)value);
						field.set(object, map);
						
					} else if(Collection.class.isAssignableFrom(targetType)) {
						
						Collection<Object> collection = (Collection<Object>)targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize(clazz, object, collection, (JSONArray)value);
						field.set(object, collection);
						
					} else if(targetType.isArray()) {
						
						Object newArray = Array.newInstance(targetType.getComponentType(), ((JSONArray)value).size());
						JSONSerializer.deserialize(clazz, object, newArray, ((JSONArray)value));
						field.set(object, newArray);
						
					} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {
						
						field.set(object, ((JSONTypeSerializationHandler)object).deserialize(targetType, value));
					}
				}
			}
		}
		
		for(Method method : methods) {

			JSONAttribute annotation = method.getAnnotation(JSONAttribute.class);
			
			if(!Modifier.isTransient(method.getModifiers()) && annotation != null && JSONSerializer.contains(JSONAttribute.Type.SETTER, annotation)) {
				
				Class<?> targetType = method.getParameterTypes()[0];
				
				if(targetType.isInterface()) {
					
					throw new Exception("Cannot deserialize an interface! Method: " + method.getName() + ", Interface: " + targetType.getName());
				}
				
				Object value = json.get(annotation.name());
				
				if(value != null) {
					
					if(float.class.isAssignableFrom(targetType) || Float.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).floatValue());
						
					} else if(double.class.isAssignableFrom(targetType) || Double.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).doubleValue());
						
					} else if(byte.class.isAssignableFrom(targetType) || Byte.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).byteValue());
						
					} else if(short.class.isAssignableFrom(targetType) || Short.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).shortValue());
						
					} else if(int.class.isAssignableFrom(targetType) || Integer.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).intValue());
						
					} else if(long.class.isAssignableFrom(targetType) || Long.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, ((Number)value).longValue());
						
					} else if(String.class.isAssignableFrom(targetType) || Number.class.isAssignableFrom(targetType) || Boolean.class.isAssignableFrom(targetType) || boolean.class.isAssignableFrom(targetType)) {
						
						method.invoke(object, value);
						
					} else if(Enum.class.isAssignableFrom(targetType)) {
						
						for(Object constant : targetType.getEnumConstants()) {
							
							if(((Enum<?>)constant).name().equals(value.toString())) {
								
								method.invoke(object, constant);
								break;
							}
						}
						
					} else if(targetType.getAnnotation(JSONRoot.class) != null) {
						
						Object newObject = targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize((JSONObject)value, newObject);
						method.invoke(object, newObject);
						
					} else if(Map.class.isAssignableFrom(targetType)) {
						
						Map<Object, Object> map = (Map<Object, Object>)targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize(clazz, object, map, (JSONObject)value);
						method.invoke(object, map);
						
					} else if(Collection.class.isAssignableFrom(targetType)) {
						
						Collection<Object> collection = (Collection<Object>)targetType.getDeclaredConstructor().newInstance();
						JSONSerializer.deserialize(clazz, object, collection, (JSONArray)value);
						method.invoke(object, collection);
						
					} else if(targetType.isArray()) {
						
						Object newArray = Array.newInstance(targetType.getComponentType(), ((JSONArray)value).size());
						JSONSerializer.deserialize(clazz, object, newArray, ((JSONArray)value));
						method.invoke(object, newArray);
						
					} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {
						
						method.invoke(object, ((JSONTypeSerializationHandler)object).deserialize(targetType, value));
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final void deserialize(Class<?> clazz, Object classObject, Collection<Object> collection, JSONArray json) throws Exception {
		
		for(Object value : json) {
			
			if(value != null) {
				
				Class<?> type = value.getClass();
				
				if(type.isInterface()) {
					
					throw new Exception("Cannot deserialize an interface! Interface: " + type.getName());
				}
				
				if(float.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).floatValue());
					
				} else if(double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).doubleValue());
					
				} else if(byte.class.isAssignableFrom(type) || Byte.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).byteValue());
					
				} else if(short.class.isAssignableFrom(type) || Short.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).shortValue());
					
				} else if(int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).intValue());
					
				} else if(long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
					
					collection.add(((Number)value).longValue());
					
				} else if(String.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
		
					collection.add(value);
					
				} else if(Enum.class.isAssignableFrom(type)) {
					
					for(Object constant : type.getEnumConstants()) {
						
						if(((Enum<?>)constant).name().equals(value.toString())) {
							
							collection.add(constant);
							break;
						}
					}
					
				} else if(type.getAnnotation(JSONRoot.class) != null) {
					
					Object newObject = type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize((JSONObject)value, newObject);
					collection.add(newObject);
					
				} else if(Map.class.isAssignableFrom(type)) {
					
					Map<Object, Object> newMap = (Map<Object, Object>)type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize(clazz, classObject, newMap, (JSONObject)value);
					collection.add(newMap);
					
				} else if(Collection.class.isAssignableFrom(type)) {

					Collection<Object> newCollection = (Collection<Object>)type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize(clazz, classObject, newCollection, (JSONArray)value);
					collection.add(newCollection);
					
				} else if(type.isArray()) {
					
					Object newArray = Array.newInstance(type.getComponentType(), ((JSONArray)value).size());
					JSONSerializer.deserialize(clazz, classObject, newArray, ((JSONArray)value));
					collection.add(newArray);
					
				} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {
					
					collection.add(((JSONTypeSerializationHandler)classObject).deserialize(type, value));
				}
				
			} else {
				
				collection.add(value);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final void deserialize(Class<?> clazz, Object classObject, Map<Object, Object> map, JSONObject json) throws Exception {

		for(Object jsonEntry : json.entrySet()) {
			
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>)jsonEntry;
			Object value = entry.getValue();
			
			if(value != null) {
				
				Class<?> type = value.getClass();
				
				if(type.isInterface()) {
					
					throw new Exception("Cannot deserialize an interface! Interface: " + type.getName());
				}
				
				if(float.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).floatValue());
					
				} else if(double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).doubleValue());
					
				} else if(byte.class.isAssignableFrom(type) || Byte.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).byteValue());
					
				} else if(short.class.isAssignableFrom(type) || Short.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).shortValue());
					
				} else if(int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).intValue());
					
				} else if(long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
					
					map.put(entry.getKey(), ((Number)value).longValue());
					
				} else if(String.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
		
					map.put(entry.getKey(), value);
					
				} else if(Enum.class.isAssignableFrom(type)) {
					
					for(Object constant : type.getEnumConstants()) {
						
						if(((Enum<?>)constant).name().equals(value.toString())) {
							
							map.put(entry.getKey(), constant);
							break;
						}
					}
					
				} else if(type.getAnnotation(JSONRoot.class) != null) {
					
					Object newObject = type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize((JSONObject)value, newObject);
					map.put(entry.getKey(), newObject);
					
				} else if(Map.class.isAssignableFrom(type)) {
					
					Map<Object, Object> newMap = (Map<Object, Object>)type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize(clazz, classObject, newMap, (JSONObject)value);
					map.put(entry.getKey(), newMap);
					
				} else if(Collection.class.isAssignableFrom(type)) {
					
					Collection<Object> collection = (Collection<Object>)type.getDeclaredConstructor().newInstance();
					JSONSerializer.deserialize(clazz, classObject, collection, (JSONArray)value);
					map.put(entry.getKey(), collection);
					
				} else if(type.isArray()) {
					
					Object newArray = Array.newInstance(type.getComponentType(), ((JSONArray)value).size());
					JSONSerializer.deserialize(clazz, classObject, newArray, ((JSONArray)value));
					map.put(entry.getKey(), newArray);
					
				} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {
					
					map.put(entry.getKey(), ((JSONTypeSerializationHandler)classObject).deserialize(type, value));
				}
				
			} else {
				
				map.put(entry.getKey(), value);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final void deserialize(Class<?> clazz, Object classObject, Object targetObject, JSONArray json) throws Exception {

		Class<?> type = targetObject.getClass().getComponentType();
		
		if(type.isInterface()) {
			
			throw new Exception("Cannot deserialize an interface! Interface: " + type.getName());
		}
		
		for(int index = 0; index < json.size(); index++) {
			
			Object value = json.get(index);
			
			if(float.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).floatValue());
				
			} else if(double.class.isAssignableFrom(type) || Double.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).doubleValue());
				
			} else if(byte.class.isAssignableFrom(type) || Byte.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).byteValue());
				
			} else if(short.class.isAssignableFrom(type) || Short.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).shortValue());
				
			} else if(int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).intValue());
				
			} else if(long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
				
				Array.set(targetObject, index, ((Number)value).longValue());
				
			} else if(String.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
	
				Array.set(targetObject, index, value);
				
			} else if(Enum.class.isAssignableFrom(type)) {
				
				for(Object constant : type.getEnumConstants()) {
					
					if(((Enum<?>)constant).name().equals(value.toString())) {
						
						Array.set(targetObject, index, constant);
						break;
					}
				}
				
			} else if(type.getAnnotation(JSONRoot.class) != null) {
				
				Object newObject = type.getDeclaredConstructor().newInstance();
				JSONSerializer.deserialize((JSONObject)value, newObject);
				Array.set(targetObject, index, newObject);
				
			} else if(Map.class.isAssignableFrom(type)) {
				
				Map<Object, Object> map = (Map<Object, Object>)type.getDeclaredConstructor().newInstance();
				JSONSerializer.deserialize(clazz, classObject, map, (JSONObject)value);
				Array.set(targetObject, index, map);
				
			} else if(Collection.class.isAssignableFrom(type)) {
				
				Collection<Object> collection = (Collection<Object>)type.getDeclaredConstructor().newInstance();
				JSONSerializer.deserialize(clazz, classObject, collection, (JSONArray)value);
				Array.set(targetObject, index, collection);
				
			} else if(type.isArray()) {
				
				Object newArray = Array.newInstance(type.getComponentType(), ((JSONArray)value).size());
				JSONSerializer.deserialize(clazz, classObject, newArray, ((JSONArray)value));
				Array.set(targetObject, index, newArray);
				
			} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {
				
				Array.set(targetObject, index, ((JSONTypeSerializationHandler)classObject).deserialize(type, value));
			}
		}
	}
	
	/**
	 * Converts a Java object into a {@linkplain JSONObject}.
	 * @param object the Java object you want to convert
	 * @return the resulting {@linkplain JSONObject}
	 * @throws Exception if an error occurs
	 * @since 1.0.0
	 */
	public static final JSONObject serialize(Object object) throws Exception {
		
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getFields();
		Method[] methods = clazz.getMethods();
		JSONObject json = new JSONObject();
		
		for(Field field : fields) {

			JSONAttribute annotation = field.getAnnotation(JSONAttribute.class);
			
			if(!Modifier.isTransient(field.getModifiers()) && annotation != null && JSONSerializer.contains(JSONAttribute.Type.GETTER, annotation)) {

				JSONSerializer.serialize(json, clazz, object, field.getType(), field.get(object), annotation);
			}
		}
		
		for(Method method : methods) {

			JSONAttribute annotation = method.getAnnotation(JSONAttribute.class);
			
			if(!Modifier.isTransient(method.getModifiers()) && annotation != null && JSONSerializer.contains(JSONAttribute.Type.GETTER, annotation)) {
				
				JSONSerializer.serialize(json, clazz, object, method.getReturnType(), method.invoke(object), annotation);
			}
		}
		
		return json;
	}
	
	private static final void serialize(JSONObject json, Class<?> clazz, Object classObject, Class<?> type, Object value, JSONAttribute annotation) throws Exception {
		
		// ==== 17.03.2018 | Ralph Niemitz/RalleYTN(ralph.niemitz@gmx.de)
		// -	Numbers now always get converted to longs
		// ====
		
		       if(value instanceof String || value instanceof Boolean) {json.put(annotation.name(), value);
		} else if(value instanceof Number) {json.put(annotation.name(), value != null ? ((Number)value).longValue() : null);
		} else if(value instanceof boolean[]) {json.put(annotation.name(), new JSONArray((boolean[])value));
		} else if(value instanceof byte[]) {json.put(annotation.name(), new JSONArray((byte[])value));
		} else if(value instanceof short[]) {json.put(annotation.name(), new JSONArray((short[])value));
		} else if(value instanceof int[]) {json.put(annotation.name(), new JSONArray((int[])value));
		} else if(value instanceof long[]) {json.put(annotation.name(), new JSONArray((long[])value));
		} else if(value instanceof float[]) {json.put(annotation.name(), new JSONArray((float[])value));
		} else if(value instanceof double[]) {json.put(annotation.name(), new JSONArray((double[])value));
		} else if(value != null && value.getClass().isArray()) {json.put(annotation.name(), JSONSerializer.serializeArray(value, clazz, classObject));
		} else if(value != null && value.getClass().getAnnotation(JSONRoot.class) != null) {json.put(annotation.name(), JSONSerializer.serialize(value));
		} else if(value instanceof Collection) {json.put(annotation.name(), JSONSerializer.serializeArray((Collection<?>)value, clazz, classObject));
		} else if(value instanceof Map) {json.put(annotation.name(), JSONSerializer.serializeObject((Map<?, ?>)value, clazz, classObject));
		} else if(value instanceof Enum) {json.put(annotation.name(), ((Enum<?>)value).name());
		} else if(JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {json.put(annotation.name(), ((JSONTypeSerializationHandler)classObject).serialize(type, value));
		} else {
			
			json.put(annotation.name(), value);
		}
	}
	
	private static final JSONObject serializeObject(Map<?, ?> map, Class<?> clazz, Object classObject) throws Exception {
		
		JSONObject json = new JSONObject();
		
		for(Map.Entry<?, ?> entry : map.entrySet()) {
			
			Object value = entry.getValue();
			
			       if(value instanceof boolean[]) {json.put((String)entry.getKey(), new JSONArray((boolean[])value));
			} else if(value instanceof byte[]) {json.put((String)entry.getKey(), new JSONArray((byte[])value));
			} else if(value instanceof short[]) {json.put((String)entry.getKey(), new JSONArray((short[])value));
			} else if(value instanceof int[]) {json.put((String)entry.getKey(), new JSONArray((int[])value));
			} else if(value instanceof long[]) {json.put((String)entry.getKey(), new JSONArray((long[])value));
			} else if(value instanceof float[]) {json.put((String)entry.getKey(), new JSONArray((float[])value));
			} else if(value instanceof double[]) {json.put((String)entry.getKey(), new JSONArray((double[])value));
			} else if(value != null && value.getClass().getAnnotation(JSONRoot.class) != null) {json.put((String)entry.getKey(), JSONSerializer.serialize(value));
			} else if(value instanceof Map) {json.put((String)entry.getKey(), JSONSerializer.serializeObject((Map<?, ?>)value, clazz, classObject));
			} else if(value instanceof Collection) {json.put((String)entry.getKey(), JSONSerializer.serializeArray((Collection<?>)value, clazz, classObject));
			} else if(value != null && value.getClass().isArray()) {json.put((String)entry.getKey(), JSONSerializer.serializeArray(value, clazz, classObject));
			} else if(value instanceof Enum) {json.put((String)entry.getKey(), ((Enum<?>)value).name());
			} else if(value instanceof String || value instanceof Boolean || value instanceof Number) {json.put((String)entry.getKey(), value);
			} else if(value != null && JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {json.put((String)entry.getKey(), ((JSONTypeSerializationHandler)classObject).serialize(value.getClass(), value));
			} else {
				
				json.put((String)entry.getKey(), value);
			}
		}
		
		return json;
	}
	
	private static final JSONArray serializeArray(Collection<?> collection, Class<?> clazz, Object classObject) throws Exception {
		
		JSONArray json = new JSONArray();
		Iterator<?> iterator = collection.iterator();
		
		while(iterator.hasNext()) {
			
			Object value = iterator.next();
			
			       if(value instanceof boolean[]) {json.add(new JSONArray((boolean[])value));
			} else if(value instanceof byte[]) {json.add(new JSONArray((byte[])value));
			} else if(value instanceof short[]) {json.add(new JSONArray((short[])value));
			} else if(value instanceof int[]) {json.add(new JSONArray((int[])value));
			} else if(value instanceof long[]) {json.add(new JSONArray((long[])value));
			} else if(value instanceof float[]) {json.add(new JSONArray((float[])value));
			} else if(value instanceof double[]) {json.add(new JSONArray((double[])value));
			} else if(value != null && value.getClass().getAnnotation(JSONRoot.class) != null) {json.add(JSONSerializer.serialize(value));
			} else if(value instanceof Map) {json.add(JSONSerializer.serializeObject((Map<?, ?>)value, clazz, classObject));
			} else if(value instanceof Collection) {json.add(JSONSerializer.serializeArray((Collection<?>)value, clazz, classObject));
			} else if(value != null && value.getClass().isArray()) {json.add(JSONSerializer.serializeArray(value, clazz, classObject));
			} else if(value instanceof Enum) {json.add(((Enum<?>)value).name());
			} else if(value instanceof String || value instanceof Boolean || value instanceof Number) {json.add(value);
			} else if(value != null && JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {((JSONTypeSerializationHandler)classObject).serialize(value.getClass(), value);
			} else {
				
				json.add(value);
			}
		}
		
		return json;
	}
	
	private static final JSONArray serializeArray(Object array, Class<?> clazz, Object classObject) throws Exception {
		
		JSONArray json = new JSONArray();
		
		for(int index = 0; index < Array.getLength(array); index++) {
			
			Object value = Array.get(array, index);
			
			       if(value instanceof boolean[]) {json.add(new JSONArray((boolean[])value));
			} else if(value instanceof byte[]) {json.add(new JSONArray((byte[])value));
			} else if(value instanceof short[]) {json.add(new JSONArray((short[])value));
			} else if(value instanceof int[]) {json.add(new JSONArray((int[])value));
			} else if(value instanceof long[]) {json.add(new JSONArray((long[])value));
			} else if(value instanceof float[]) {json.add(new JSONArray((float[])value));
			} else if(value instanceof double[]) {json.add(new JSONArray((double[])value));
			} else if(value != null && value.getClass().getAnnotation(JSONRoot.class) != null) {json.add(JSONSerializer.serialize(value));
			} else if(value instanceof Map) {json.add(JSONSerializer.serializeObject((Map<?, ?>)value, clazz, classObject));
			} else if(value instanceof Collection) {json.add(JSONSerializer.serializeArray((Collection<?>)value, clazz, classObject));
			} else if(value != null && value.getClass().isArray()) {json.add(JSONSerializer.serializeArray(value, clazz, classObject));
			} else if(value instanceof Enum) {json.add(((Enum<?>)value).name());
			} else if(value instanceof String || value instanceof Boolean || value instanceof Number) {json.add(value);
			} else if(value != null && JSONTypeSerializationHandler.class.isAssignableFrom(clazz)) {json.add(((JSONTypeSerializationHandler)classObject).serialize(value.getClass(), value));
			} else {
				
				json.add(value);
			}
		}
		
		return json;
	}
	
	private static final boolean contains(JSONAttribute.Type type, JSONAttribute attribute) {
		
		for(JSONAttribute.Type value : attribute.type()) {
			
			if(value.equals(type)) {
				
				return true;
			}
		}
		
		return false;
	}
}
