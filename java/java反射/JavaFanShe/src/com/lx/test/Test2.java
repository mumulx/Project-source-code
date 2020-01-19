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
// ��ȡ�����ʵ��������������
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

	// ��������
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
		// ���ʵ���private���ε�id������ peivate��˽��
		// ���Ҫ�޸����Եķ���Ȩ�ޣ�ʹ�÷���ʱ���������Ϊ�������η���������쳣������ͨ��setAccessible(true)�������
		// ͬ����Ҳ����setAccessible(true)�ķ���
		idField.setAccessible(true);
		idField.set(person, 1);// �൱�� person.setId(1);
		System.out.println(person.getId());
	}

	// ��������
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
		// ͬ����Ҳ����setAccessible(true)�ķ���
		myPrivateMethod.setAccessible(true);
		myPrivateMethod.invoke(person, null);
	}

	// �������� ����
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
		// ͬ����Ҳ����setAccessible(true)�ķ���
		myPrivateMethod1.setAccessible(true);
		myPrivateMethod1.invoke(person, "hello");
	}

	// �������췽��
	public static void test5() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> forName = null;
		try {
			forName = Class.forName("com.lx.myclass.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// �ڷ����У��������ͻ�ȡ����ʱ����������(int\char) �Ͱ�װ�ࣨInteger��Character��ʱ��ͬ������
		Constructor<?> constructor = forName.getConstructor(int.class);
		System.out.println(constructor);
	}

	// �������췽��
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

	// �������췽�� new����
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

	// ��̬���������ͷ���
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

	// �������Խ�� ���ͼ��
	public static void test9() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		// ��ʱֻ�����Integer����
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		// list.add("zs");
		Class<?> listclass = list.getClass();
		Method method = listclass.getMethod("add", Object.class);
		method.invoke(list, "zs");
		System.out.println(list);

	}

	// ���� set����
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
