package org.lanqiao.service.impl;

import java.sql.SQLException;

import org.lanqiao.dao.IStudentDao;
import org.lanqiao.entity.Student;
import org.lanqiao.service.IStudentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentServiceImpl  implements IStudentService{
	IStudentDao studentDao ;	
	
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,
			rollbackFor={SQLException.class,ArithmeticException.class})
	@Override
	public void addStudent(Student student) {
		//if(该学生是否存在)
		//增加其他....
		studentDao = null ;//测试异常通知
		studentDao.addStudent(student);
//		int[] nums = new int[2] ;
//		nums[2] = 2;
		System.out.println("=====进行增加操作========");
//		return true ;
	}

	@Override
	public void deleteStudentByNo(int stuNo) {
		System.out.println("模拟删除....");
	}

	
	
	
}
