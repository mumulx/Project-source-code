package org.lanqiao.service.impl;

import org.lanqiao.dao.IStudentDao;
import org.lanqiao.dao.impl.StudentDaoImpl;
import org.lanqiao.service.IStudentService;

public class StudentServiceImpl  implements IStudentService{
	private IStudentDao studentDao;
	
	
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}


	@Override
	public String queryStudentById() {
		return studentDao.queryStudentById(); 
	}

}
