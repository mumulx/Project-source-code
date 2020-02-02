package org.lanqiao.converter;

import org.lanqiao.entity.Student;
import org.springframework.core.convert.converter.Converter;

public class MyConverter  implements Converter<String,Student>{

	@Override
	public Student convert(String source) {//source:2-zs-23
		//source接受前端传来的String:2-zs-23
		String[] studentStrArr = source.split("-") ;
		Student student = new Student();
		student.setId(  Integer.parseInt(  studentStrArr[0]) );
		student.setName(studentStrArr[1]);
		student.setAge(Integer.parseInt(studentStrArr[2] ));
		return student;
	}

}
