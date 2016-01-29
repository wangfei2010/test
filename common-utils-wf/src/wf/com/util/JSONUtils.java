package wf.com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JSONUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JSONUtils() {

	}

	public static ObjectMapper getInstance() {

		return objectMapper;
	}

	/**
	 * Bean转化成Json
	 */
	public static String obj2json(Object obj) {
		try {
			if (obj != null) {
				return objectMapper.writeValueAsString(obj);
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Json转化成Bean
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Json转化成Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2map(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Json转化成Map（指定Bean类型）
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
		try {
			Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
			});
			Map<String, T> result = new HashMap<String, T>();
			for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
				result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
			}
			return result;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Json转化成List
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
		try {
			List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
			});
			List<T> result = new ArrayList<T>();
			for (Map<String, Object> map : list) {
				result.add(map2pojo(map, clazz));
			}
			return result;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Map转化成Bean
	 */
	public static <T> T map2pojo(Map<String,Object> map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
}