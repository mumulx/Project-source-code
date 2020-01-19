package com.lx.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import com.lx.myclass.MyProperty;
import com.lx.myclass.Person;
import com.lx.myclass.Student;

public class Test2 {
// 获取对象的实例，并操作对象
	public static void test1() throws InstantiationException, IllegalAccessException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Person person = (Person) forName.newInstance();
		person.setId(22);
		person.setName("zs");
		System.out.println(person.getId() + "---" + person.getName());
	}

	// 操作属性
	public static void test2()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Person person = (Person) forName.newInstance();
		Field idField = forName.getDeclaredField("id");
		// 访问的是private修饰的id，但是 peivate是私有
		// 因此要修改属性的访问权限，使用反射时，如果是因为访问修饰符限制造成异常，可以通过setAccessible(true)方法解决
		// 同理方法也具有setAccessible(true)的方法
		idField.setAccessible(true);
		idField.set(person, 1);// 相当于 person.setId(1);
		System.out.println(person.getId());
	}

	// 操作方法
	public static void test3() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Person person = (Person) forName.newInstance();
		Method myPrivateMethod = forName.getDeclaredMethod("privateMethod", null);
		// 同理方法也具有setAccessible(true)的方法
		myPrivateMethod.setAccessible(true);
		myPrivateMethod.invoke(person, null);
	}

	// 操作方法 带参
	public static void test4() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Person person = (Person) forName.newInstance();
		Method myPrivateMethod1 = forName.getDeclaredMethod("privateMethod1", String.class);
		// 同理方法也具有setAccessible(true)的方法
		myPrivateMethod1.setAccessible(true);
		myPrivateMethod1.invoke(person, "hello");
	}

	// 操作构造方法
	public static void test5() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 在反射中，根据类型获取方法时；基本类型(int\char) 和包装类（Integer，Character）时不同的类型
		Constructor<?> constructor = forName.getConstructor(int.class);
		System.out.println(constructor);
	}

	// 操作构造方法
	public static void test6() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> constructor = forName.getDeclaredConstructor(String.class);
		System.out.println(constructor);
	}

	// 操作构造方法 new对象
	public static void test7() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor<?> constructor = forName.getConstructor(int.class);
		System.out.println(constructor);
		Person per = (Person) constructor.newInstance(10);
		System.out.println(per);
	}

	// 动态加载类名和方法
	public static void test8() throws FileNotFoundException, IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Properties properties = new Properties();
		properties.load(new FileReader("class.txt"));
		String classname = properties.getProperty("className");
		String methodName = properties.getProperty("methodName");
		Class<?> forName = null;
		try {
			forName = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method method = forName.getMethod(methodName);
		method.invoke(forName.newInstance());

	}

	// 反射可以越过 泛型检查
	public static void test9() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		// 此时只能添加Integer类型
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		// list.add("zs");
		Class<?> listclass = list.getClass();
		Method method = listclass.getMethod("add", Object.class);
		method.invoke(list, "zs");
		System.out.println(list);

	}

	// 万能 set方法
	public static void test10() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Person person = new Person();
		MyProperty.setProperty(person, "name", "zs");
		MyProperty.setProperty(person, "age", 23);
		Student student = new Student();
		MyProperty.setProperty(student, "score", 98);
		System.out.println(person);
		System.out.println(student);
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			FileNotFoundException, IOException {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();

//		test4();
//		test5();
//		test6();
//		test7();
		// test8();
//		test9();
		test10();
	}

}
