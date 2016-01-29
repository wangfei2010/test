package javacommon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckParamFactory {
	Map<String, List<ICheckProcess>> map = new HashMap<String, List<ICheckProcess>>();

	public void add(String key, ICheckProcess checkpro) {
		if (map.containsKey(key)) {
			List<ICheckProcess> icheckp = map.get(key);
			icheckp.add(checkpro);
		}
		else {
			List<ICheckProcess> lst = new ArrayList<ICheckProcess>();
			lst.add(checkpro);
			map.put(key, lst);
		}

	}

	public boolean pro(String prakey, String pravalue) {
		if (map.isEmpty()) {
			return true;
		}
		List<ICheckProcess> lst = map.get(prakey);
		if (lst == null) {
			return true;
		}
		for (ICheckProcess icheckp : lst) {
			boolean bl = icheckp.proCheck(pravalue);
			if (!bl) {
				return false;
			}
		}
		return true;
	}

	public boolean pro(Map<String, Object> mapvalue) {
		if (map.isEmpty()) {
			return true;
		}
		Set<String> keys = mapvalue.keySet();
		for (String key : keys) {
			List<ICheckProcess> lst = map.get(key);
			if (lst == null) {
				return true;
			}
			for (ICheckProcess icheckp : lst) {
				boolean bl = icheckp.proCheck(mapvalue.get(key).toString());
				if (!bl) {
					return false;
				}
			}
		}
		return true;
	}

}