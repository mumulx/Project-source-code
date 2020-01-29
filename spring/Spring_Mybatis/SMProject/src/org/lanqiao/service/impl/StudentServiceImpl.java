package org.lanqiao.service.impl;

import org.lanqiao.entity.Student;
import org.lanqiao.mapper.StudentMapper;
import org.lanqiao.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	private StudentMapper studentMapper ;
	
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}


	@Override
	public void addStudent(Student student) {
		//µ÷ÓÃdao²ã
		studentMapper.addStudent(student);
	}

}
