package com.aswishes.novel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import com.aswishes.novel.mvc.model.MUser;

public class Test {
	
	public static void main(String[] args) {
		cast();
	}
	
	public static void cast() {
		System.out.println(new Date().getClass().isInstance(new Date()));
		
	}
	
	public static void beanToMapTest() {
		System.currentTimeMillis();
		int count = 100000;
		long t1 = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("uName", "ebert");
//			map.put("uPwd", "1231231");
//			novelUser bean = new novelUser();
//			mapToBean2(map, bean);
////			System.out.print(bean.getUName() + " ");
//		}
		long t2 = System.currentTimeMillis();
		System.out.println("--------------------" + (t2 - t1));
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uName", "ebert");
			map.put("uPwd", "1231231");
			MUser bean = new MUser();
			mapToBean3(map, bean);
//			System.out.print(bean.getUName() + " ");
		}
		long t3 = System.currentTimeMillis();
		System.out.println("--------------------" + (t3 - t2));
//		for (int i = 0; i < count; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("uName", "ebert");
//			map.put("uPwd", "1231231");
//			novelUser bean = new novelUser();
//			mapToBean4(map, bean);
////			System.out.print(bean.getUName() + " ");
//		}
		long t4 = System.currentTimeMillis();
		System.out.println("--------------------" + (t4 - t3));
		for (int i = 0; i < count; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uName", "ebert");
			map.put("uPwd", "1231231");
			MUser bean = new MUser();
			mapToBean4(map, bean);
//			System.out.print(bean.getUName() + " ");
		}
		long t5 = System.currentTimeMillis();
		System.out.println("--------------------" + (t5 - t4));
	}

	public static void beanToMap() {
		MUser bean = new MUser();
		try {
			Map<String, String> map = BeanUtils.describe(bean);
			System.out.println(map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void mapToBean() {
		MUser bean = new MUser();
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("setUName", "ebert");
		try {
			BeanUtils.populate(bean, properties);
			System.out.println(bean.getName());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void mapToBean2(Map<String, Object> map, Object bean) {
		Class<?> bclass = bean.getClass();
		for (Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			
			try {
				Field f = bclass.getDeclaredField(key);
				f.setAccessible(true);
				f.set(bean, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void mapToBean3(Map<String, Object> map, Object bean) {
		Class<?> bclass = bean.getClass();
		Field[] fs = bclass.getDeclaredFields();
		for (Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			
			try {
				for (int i = 0; i < fs.length; i++) {
					Field f = fs[i];
					if (f.getName().equals(key)) {
						f.setAccessible(true);
						f.set(bean, value);
						break;
					} else {
						continue;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void mapToBean4(Map<String, Object> map, Object bean) {
		Class<?> bclass = bean.getClass();
		Map<String, Field> m = fieldMap(bclass.getDeclaredFields());
		for (Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			
			try {
				Field f = m.get(key);
				if (f != null) {
					f.setAccessible(true);
					f.set(bean, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void mapToBean5(Map<String, Object> map, Object bean) {
		Class<?> bclass = bean.getClass();
		Map<String, Field> m = fieldMap(bclass.getDeclaredFields());
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			
			try {
				Field f = m.get(key);
				if (f != null) {
					f.setAccessible(true);
					f.set(bean, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Map<String, Field> fieldMap(Field[] fields) {
		Map<String, Field> m = new HashMap<String, Field>();
		for (Field f : fields) {
			m.put(f.getName(), f);
		}
		return m;
	}
}
