package wf.com.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @author wangfei
 */
public class ReflectUtil {
	/**
	 * 通过方法名执行方法
	 * @param methodName
	 * @param clazz
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Object executeMethod(String methodName, Object objIn) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = getMethod(methodName, objIn.getClass());
		if (method == null) {
			return null;
		}

		return method.invoke(objIn);
	}

	/**
	 * 通过方法名获取方法
	 * @param methodName
	 * @param clazz
	 * @return
	 */
	public static Method getMethod(String methodName, Class<?> clazz) {
		if (StringUtils.isEmpty(methodName) || (clazz == null)) {
			return null;
		}

		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (methodName.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 通过属性名获取属性值
	 * @param propName
	 * @param clazz
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Object getPropertyValue(String propName, Object objIn) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = getPropertyGetMethod(propName, objIn.getClass());
		if (method == null) {
			return null;
		}

		return method.invoke(objIn);
	}

	/**
	 * 通过属性名获取此属性的get方法
	 * @param propName
	 * @param clazz
	 * @return
	 */
	public static Method getPropertyGetMethod(String propName, Class<?> clazz) {
		if (StringUtils.isEmpty(propName) || (clazz == null)) {
			return null;
		}

		String methodName = "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1);

		return getMethod(methodName, clazz);
	}

	/**
	 * 通过属性名获取此属性的set方法
	 * @param propName
	 * @param clazz
	 * @return
	 */
	public static Method getPropertySetMethod(String propName, Class<?> clazz) {
		if (StringUtils.isEmpty(propName) || (clazz == null)) {
			return null;
		}

		String methodName = "set" + propName.substring(0, 1).toUpperCase() + propName.substring(1);

		return getMethod(methodName, clazz);
	}
}