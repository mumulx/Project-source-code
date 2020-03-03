package org.students.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.students.mapper.StudentMapper;
import org.students.pojo.Student;
import org.students.service.StudentService;

import com.alibaba.dubbo.config.annotation.Service;

@Service//alibaba
public class StudentServiceImpl implements StudentService {
	//Service依赖Dao(MyBatis的Mapper)
	@Autowired 
	@Qualifier("studentMapper")
	private StudentMapper studentMapper ; 
	@Override
	public void addStudent(Student student) {
		studentMapper.addStudent(student);//if
	}

	@Override
	public Student queryStudentByStuNo(int stuNo) {
		return studentMapper.queryStudentByStuno(stuNo) ;
	}

}
