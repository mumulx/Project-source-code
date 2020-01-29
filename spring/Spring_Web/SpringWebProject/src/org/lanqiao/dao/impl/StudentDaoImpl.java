package org.lanqiao.dao.impl;

import org.lanqiao.dao.IStudentDao;

public class StudentDaoImpl  implements IStudentDao{

	@Override
	public String queryStudentById() {
		//模拟通过JDBC查询数据
		System.out.println("1   zs   23   ");
		return "zs" ;
	}

}
