package org.student.dao;

import java.util.List;

import org.student.entity.Student;

public interface IStudentDao {

	public boolean addStudent(Student student) ;
	
	//根据学号修改学生： 根据sno知道待修改的人 ，把这个人 修改成student
	public boolean updateStudentBySno(int sno,Student student)  ;
	
	
	public int getTotalCount();//查询总数据数
	
	//currentPage:当前页（页码）   pageSize：页面大小（每页显示的数据条数）
	public  List<Student> queryStudentsByPage(int currentPage,int pageSize);
	
	//根据学号删除学生
	public boolean deleteStudentBySno(int sno)  ;
	
	//查询全部学生(很多学生)
	public List<Student> queryAllStudents() ;
	
	
	//根据姓名查询
	//根据年龄查询
	//查询此人是否存在
	public boolean isExist(int sno);
	
	//根据学号 查询学生
	public Student queryStudentBySno(int sno) ;
	
	
	
	
	
	
	
	
	
}
