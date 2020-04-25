package com.mumulx.xulie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//序列化
		objectOut();
		//反序列化
		objectIn();
		
	}
	public static void objectOut() throws FileNotFoundException, IOException {
		Person per = new Person("zs",24);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\per.obj"));
		oos.writeObject(per);
		oos.close();
	}
	public static void objectIn() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\per.obj"));
		Object readObject = ois.readObject();
		Person per = (Person)readObject;
		System.out.println(per.getName()+"===="+per.getAge());

	}
}
