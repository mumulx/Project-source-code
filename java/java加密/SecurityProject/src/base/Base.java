package base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Base {
	//base64 加密
	public static String base64Encode(byte[] input) {
		String result = null;
		//反射
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("encode", byte[].class);
			result = (String)method.invoke(null, input);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	//base64解密
	public static byte[] base64Decode(String input) {
		byte[] result = null ;
		try {
			Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method = clazz.getMethod("decode", String.class);
			result = (byte[])method.invoke(null, input);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	public static void main(String[] args) {
		String str = "hello";
		//加密
		str = base64Encode(str.getBytes());
		System.out.println("加密"+str);
		//解密
		byte[] rs = base64Decode(str);
		System.out.println("解密"+new String(rs));
	}
}
