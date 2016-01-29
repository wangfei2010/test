package javacommon.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.zp.commons.error.BusinessException;
import com.zp.commons.error.ErrorCode;

/**
 * 获取参数工具类
 * @author WangFei
 */
public class GetParameterUtil {
	List<Field> lst = new ArrayList<Field>();

	public CheckParamFactory getCpf() {
		return cpf;
	}

	public void setCpf(CheckParamFactory cpf) {
		this.cpf = cpf;
	}

	private CheckParamFactory cpf;

	public GetParameterUtil(CheckParamFactory cpf) {
		this.cpf = cpf;
	}

	public GetParameterUtil() {
	}

	public Object getPara(HttpServletRequest request, String para) throws BusinessException {

		if(StringUtils.isEmpty(para)){
			return "";
		}
		
		String[] array = para.split(",");
		if (array.length == 1) {
			String value = request.getParameter(array[0].replace("_", "")) == null ? null : request.getParameter(array[0].replace("_", "")).trim();
			if (this.cpf != null) {
				boolean isok = this.cpf.pro(array[0], value);
				if (!isok) {
					throw new BusinessException(ErrorCode.sys_008);
				}
			}
			return value;
		}
		else {
			Map<String, String> map = new HashMap<String, String>();
			for (String single : array) {
				String value = request.getParameter(single.replace("_", "")) == null ? null : request.getParameter(single.replace("_", "")).trim();
				if (this.cpf != null) {
					boolean isok = this.cpf.pro(single.replace("_", ""), value);
					if (!isok) {
						throw new BusinessException(ErrorCode.sys_008);
					}
				}

				map.put(single.replace("_", ""), value);

			}
			return map;
		}

	}

	public <T> T getPara(HttpServletRequest request, String para, Class<T> modelclass) throws BusinessException, InstantiationException, IllegalAccessException, IntrospectionException, NumberFormatException, IllegalArgumentException, InvocationTargetException {
		Field[] fields = getAllFields(modelclass);
		List<Field> lst = new ArrayList<Field>();
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				lst.add(field);
			}
		}
		String[] array = para.split(",");
		T o = null;

		o = modelclass.newInstance();

		for (Field f : lst) {
			PropertyDescriptor pd = new PropertyDescriptor(f.getName(), modelclass);
			Method w = pd.getWriteMethod();
			for (String single : array) {
				if (f.getName().toLowerCase().equals(single.replace("_", "").trim().toLowerCase())) {
					String value = request.getParameter(single.replace("_", "").trim()) == null ? null : request.getParameter(single.replace("_", "").trim()).trim();
					if (this.cpf != null) {
						boolean isok = this.cpf.pro(single.replace("_", "").trim(), value);
						if (!isok) {
							throw new BusinessException(ErrorCode.sys_008);
						}
					}
					if (f.getType() == Integer.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Integer.valueOf(value));
						}
					}
					else if (f.getType() == Long.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Long.valueOf(value));
						}
					}
					else if (f.getType() == Float.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Float.valueOf(value));
						}
					}
					else if (f.getType() == Boolean.class) {
						if (value != null) w.invoke(o, Boolean.valueOf(value.equals("1") ? "true" : "false"));
					}
					else if (f.getType() == String.class) {
						if (value != null) w.invoke(o, value);
					}
					else if (f.getType() == int.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Integer.valueOf(value));
						}
					}
					else if (f.getType() == long.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Long.valueOf(value));
						}
					}
					else if (f.getType() == float.class) {
						if (value != null) {
							if (value.equals("")) {
								value = "0";
							}
							w.invoke(o, Float.valueOf(value));
						}
					}
				}

			}

		}

		return o;
	}

	public <T> Field[] getAllFields(Class<T> modelclass) {

		for (Field field : modelclass.getDeclaredFields()) {
			lst.add(field);
		}
		if (modelclass.getSuperclass() != null) {
			getAllFields(modelclass.getSuperclass());
		}

		return lst.toArray(new Field[0]);

	}

	/**
	 * 返回请求参数值为单一值的参数映射集合，并对参数值做了trimToNull处理。
	 * @param request
	 * @return
	 */
	public static Map<String, String> getSingleValueParams(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> strParamMap = new HashMap<String, String>();
		for (Entry<String, String[]> entry : paramMap.entrySet()) {
			String[] values = entry.getValue();
			String value = null;
			if (values != null && values.length == 1) {
				value = StringUtils.trimToNull(values[0]);
			}
			strParamMap.put(entry.getKey(), value);
		}
		return strParamMap;
	}

}