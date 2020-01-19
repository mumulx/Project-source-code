package com.lx.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.lx.myclass.Person;

public class Test {
	public void test1() {
		// 获取反射对象（反射入口）
		/*
		 * 1. Class.forName(全类名) 2. xx.class 3. 对象.getClass
		 * 
		 */
		// 通过反射获取类
		// 1. Class.forname()
		try {
			Class<?> forName = Class.forName("com.lx.myclass.Person");
			System.out.println(forName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. 类名.class
		Class<?> forName2 = Person.class;
		System.out.println(forName2);
		// 3. 对象.getClass
		Person per = new Person();
		Class<?> forName3 = per.getClass();
		System.out.println(forName3);

	}
	// 获取方法

	public static void test2() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取所有的公共的方法
		/*
		 * 本类以及父类、接口中的所有的方法
		 * 
		 * 符合访问修饰符的规律
		 * 
		 * 
		 */
		Method[] methods = forName.getMethods();

		for (Method method : methods) {
			System.out.println(method);
		}
	}

	// 获取所有接口
	public static void test3() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取当前类的所有接口
		Class<?>[] interfaces = forName.getInterfaces();
		for (Class<?> inter : interfaces) {
			System.out.println(inter);
		}
	}

	// 获取所有父类
	public static void test4() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取所有父类 单继承
		Class<?> superclass = forName.getSuperclass();
		System.out.println(superclass);

	}

	// 获取所有构造方法
	public static void test5() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取所有父类 单继承
		Constructor<?>[] constructors = forName.getConstructors();
		for (Constructor<?> constructor : constructors)
			System.out.println(constructor);
	}

	// 获取所有公共属性
	public static void test6() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取所有父类 单继承
		Field[] fields = forName.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
	}

	// 获取当前类所有方法
	// 1.只能是当前类
	// 2.忽略访问修饰符限制
	public static void test7() {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method[] declaredMethods = forName.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			System.out.println(declaredMethod);
		}
	}

	// 获取当前类所有属性
	// 1.只能是当前类
	// 2.忽略访问修饰符限制
	public static void test8() {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Field[] declaredFields = forName.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			System.out.println(declaredField);
		}
	}

	// 获取当前反射所代表类（接口）的对象

	public static void test9() throws InstantiationException, IllegalAccessException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object instance = forName.newInstance();
		Person person = (Person) instance;
		System.out.println(person);
	}

	public static void main(String[] args) {
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
//		test7();
//		test8();
		/*
		 * try { test9(); } catch (InstantiationException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}
}
