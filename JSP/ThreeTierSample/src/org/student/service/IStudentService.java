package org.student.service;

import java.util.List;

import org.student.entity.Student;

public interface IStudentService {
	public Student queryStudentBySno(int sno);
	//查询全部学生
	public List<Student> queryAllStudents();
	
	public List<Student> queryStudentsByPage(int currentPage ,int pageSize);
	public int getTotalCount();
	
	
	
	public boolean updateStudentBySno(int sno, Student student) ;
	
	public boolean deleteStudentBySno(int sno) ;
	
	public boolean addStudent(Student student) ;
	
	
}
