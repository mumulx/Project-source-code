package com.lx.myclass;

import java.lang.reflect.Field;

public class MyProperty {
	//per.setXxx(value)
	public static void setProperty(Object obj,String propertyName,Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> class1 = obj.getClass();
		Field field = class1.getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(obj, value);
	}
}
