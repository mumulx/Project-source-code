package org.students.mapper;

import org.students.pojo.Student;

public interface StudentMapper {
	Student queryStudentByStuno(int stuNo);
	void addStudent(Student student);
}
//conf.xml  ->  Spring  