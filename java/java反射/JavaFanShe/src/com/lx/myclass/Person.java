package com.lx.myclass;

import com.lx.myinterface.MyInterface;
import com.lx.myinterface.MyInterface2;

public class Person implements MyInterface, MyInterface2 {

	private int id;
	private String name;
	private int age;

	public Person() {
	}
	public Person(int id) {
		this.id = id;
	}
	private Person(String name) {
		this.name = name;
	}
	public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public static void staticMethod() {
		System.out.println("static ...  ");

	}

	private void privateMethod() {
		System.out.println("private£¬£¬£¬£¬£¬");
	}

	private void privateMethod1(String name) {
		System.out.println("private2222£¬£¬£¬£¬£¬" + name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod");
	}

	public void interface2wMethod() {
		// TODO Auto-generated method stub
		System.out.println("interface2wMethod");
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	

}
