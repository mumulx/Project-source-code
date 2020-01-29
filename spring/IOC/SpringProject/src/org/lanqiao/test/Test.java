package org.lanqiao.test;

import org.lanqiao.entity.AllCollectionType;
import org.lanqiao.entity.Course;
import org.lanqiao.entity.Student;
import org.lanqiao.factory.CourseFactory;
import org.lanqiao.newinstance.ICourse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	
	public static void springIoc() {
//		Student student = new Student();
//		student.setStuNo(1);
//		student.setStuName("zs");
//		student.setStuAge(23);
//		System.out.println(student);
		
		//Spring上下文对象：conext
		ApplicationContext conext = new ClassPathXmlApplicationContext("applicationContext.xml") ;
		//执行从springIOC容器中获取一个 id为student的对象
		Student student = (Student)conext.getBean("student") ;
		System.out.println(student);
		//1.new
		//2.对象属性的复制
		
	}
	
	public static void learnCourse() {
		Student student = new Student();
		student.learnHtml();
		student.learnJava(); 
	}
	
	public static void collectionDemo() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml") ;
		AllCollectionType type = (AllCollectionType)context.getBean("collectionDemo") ;
		System.out.println(type);
	}
	
	
	public static void testDI() {
		ApplicationContext conext = new ClassPathXmlApplicationContext("applicationContext.xml") ;
		Course course = (Course)conext.getBean("course") ;
		course.showInfo();
	}
	
	public static void learnCourseWithFactory() {
		Student student = new Student();
		student.learn("html");		
		
	}
	
	public static void learnCourseWithIoC() {
		ApplicationContext conext = new ClassPathXmlApplicationContext("applicationContext.xml") ;
		//从IOC获取学生对象
		Student student = (Student)conext.getBean("student") ;
		student.learn("javaCourse");
	}
	
	public static void main(String[] args) {

//		learnCourse();
//		learnCourseWithFactory();
//		learnCourseWithIoC();
		testDI();
//		collectionDemo();
		
		
		
	}
}
