package org.lanqiao.dao;

import org.lanqiao.entity.Student;
import org.springframework.stereotype.Repository;
/*
 * 
 * <bean id="studentDao" class="org.lanqiao.dao.StudentDaoImpl">
 */
//@Component("studentDao")
@Repository
public class StudentDaoImpl {
	public void addStudent(Student student) {
		System.out.println("增加学生...");
	}
}
