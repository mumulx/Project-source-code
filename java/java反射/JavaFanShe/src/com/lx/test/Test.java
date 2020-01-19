package com.lx.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.lx.myclass.Person;

public class Test {
	public void test1() {
		// ��ȡ������󣨷�����ڣ�
		/*
		 * 1. Class.forName(ȫ����) 2. xx.class 3. ����.getClass
		 * 
		 */
		// ͨ�������ȡ��
		// 1. Class.forname()
		try {
			Class<?> forName = Class.forName("com.lx.myclass.Person");
			System.out.println(forName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. ����.class
		Class<?> forName2 = Person.class;
		System.out.println(forName2);
		// 3. ����.getClass
		Person per = new Person();
		Class<?> forName3 = per.getClass();
		System.out.println(forName3);

	}
	// ��ȡ����

	public static void test2() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ���еĹ����ķ���
		/*
		 * �����Լ����ࡢ�ӿ��е����еķ���
		 * 
		 * ���Ϸ������η��Ĺ���
		 * 
		 * 
		 */
		Method[] methods = forName.getMethods();

		for (Method method : methods) {
			System.out.println(method);
		}
	}

	// ��ȡ���нӿ�
	public static void test3() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ��ǰ������нӿ�
		Class<?>[] interfaces = forName.getInterfaces();
		for (Class<?> inter : interfaces) {
			System.out.println(inter);
		}
	}

	// ��ȡ���и���
	public static void test4() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ���и��� ���̳�
		Class<?> superclass = forName.getSuperclass();
		System.out.println(superclass);

	}

	// ��ȡ���й��췽��
	public static void test5() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ���и��� ���̳�
		Constructor<?>[] constructors = forName.getConstructors();
		for (Constructor<?> constructor : constructors)
			System.out.println(constructor);
	}

	// ��ȡ���й�������
	public static void test6() {
		Class<?> forName = null;
		// class
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ���и��� ���̳�
		Field[] fields = forName.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
	}

	// ��ȡ��ǰ�����з���
	// 1.ֻ���ǵ�ǰ��
	// 2.���Է������η�����
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

	// ��ȡ��ǰ����������
	// 1.ֻ���ǵ�ǰ��
	// 2.���Է������η�����
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

	// ��ȡ��ǰ�����������ࣨ�ӿڣ��Ķ���

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
