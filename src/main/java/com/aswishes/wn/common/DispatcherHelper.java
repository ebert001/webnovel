package com.aswishes.wn.common;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;

public class DispatcherHelper {

	private static final Map<Class<?>, WeakReference<Method[]>> methodCache = Collections
			.synchronizedMap(new WeakHashMap<Class<?>, WeakReference<Method[]>>());

	public static Method[] findCustomMethods(Class<?> type) throws Exception {
		Method[] methods = null;
		WeakReference<Method[]> ref = methodCache.get(type);
		if (ref != null) {
			methods = ref.get();
		}

		// Lazily examine the ActionBean and collect this information
		if (methods == null) {
			// A sorted set with a custom comparator that will order the methods
			// in
			// the set based upon the priority in their custom validation
			// annotation
			SortedSet<Method> sortMethods = new TreeSet<Method>(new Comparator<Method>() {
				public int compare(Method o1, Method o2) {
					// If one of the methods overrides the others, return equal!
					if (o1.getName().equals(o2.getName()) && Arrays.equals(o1.getParameterTypes(), o2.getParameterTypes())) {
						return 0;
					}
					return -1;
				}
			});

			Class<?> temp = type;
			while (temp != null) {
				for (Method method : temp.getDeclaredMethods()) {
					int mod = method.getModifiers();
					if (Modifier.isAbstract(mod) || Modifier.isPrivate(mod) || Modifier.isNative(mod) || Modifier.isStatic(mod)) {
						continue;
					}
					sortMethods.add(method);
				}
				temp = temp.getSuperclass();
			}

			methods = sortMethods.toArray(new Method[sortMethods.size()]);
			methodCache.put(type, new WeakReference<Method[]>(methods));
		}
		return methods;
	}
	
	/**
	 * 使用此方法需要注意：类中的方法不能有重载
	 * @param bean
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invoke(Object bean, String methodName, Object...args) throws Exception {
		Method[] methods = findCustomMethods(bean.getClass());
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method.invoke(bean, args);
			}
		}
		return null;
	}

}