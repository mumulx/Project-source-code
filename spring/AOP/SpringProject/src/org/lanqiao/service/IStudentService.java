package org.lanqiao.service;

import org.lanqiao.entity.Student;

public interface IStudentService {
	void addStudent(Student student);
	
	void deleteStudentByNo(int stuNo) ;
}
