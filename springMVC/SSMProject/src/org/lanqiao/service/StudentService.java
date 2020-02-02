package org.lanqiao.service;

import org.lanqiao.entity.Student;

public interface StudentService {
		Student queryStudentByNo(int stuNo);
		void addStudent(Student student);
}
