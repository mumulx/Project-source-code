package org.lanqiao.test;

import org.lanqiao.entity.Student;
import org.lanqiao.service.IStudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IStudentService studentService = (IStudentService)context.getBean("studentService") ;
		Student student = new Student();
		student.setStuAge(333);
		student.setStuName("33s9");
		student.setStuNo(339);
		studentService.addStudent(student);
		
	}
}
