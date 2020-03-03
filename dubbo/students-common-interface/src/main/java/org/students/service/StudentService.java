package org.students.service;

import org.students.pojo.Student;

public interface StudentService {
	void addStudent(Student student);
	Student queryStudentByStuNo(int stuno);
}
