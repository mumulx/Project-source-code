package org.student.service.impl;

import java.util.List;

import org.student.dao.IStudentDao;
import org.student.dao.impl.StudentDaoImpl;
import org.student.entity.Student;
import org.student.service.IStudentService;

//业务逻辑层：逻辑性的增删改查（ 增：查+增）  ，对dao层进行的组装
public class StudentServiceImpl implements IStudentService{
	IStudentDao studentDao = new StudentDaoImpl();
	//根据学号查询学生
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	//查询全部学生
	public List<Student> queryAllStudents(){
		return studentDao.queryAllStudents() ;
	}
	
	public boolean updateStudentBySno(int sno, Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno, student) ;
		}
		return false ;
	}	
	
	public boolean deleteStudentBySno(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno) ;
		}
			return false ;
	}
	
	public boolean addStudent(Student student) {
		if(!studentDao.isExist(student.getSno())) {//不存在
			
			return studentDao.addStudent(student) ; 
		}else {
			System.out.println("此人已存在！");
			return false ;
		}
	}
	//查询当前页的数据集合
	@Override
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		return studentDao.queryStudentsByPage(currentPage, pageSize);
	}
	//查询数据总条数
	@Override
	public int getTotalCount() {
		return studentDao.getTotalCount();
	}
}
