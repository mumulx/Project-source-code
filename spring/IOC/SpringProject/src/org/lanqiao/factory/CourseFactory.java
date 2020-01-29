package org.lanqiao.factory;

import org.lanqiao.newinstance.ICourse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//课程工厂
public class CourseFactory {
	//根据名字获取课程
//	public static ICourse getCourse(String name) {
//		//获取ioc容器
//		ApplicationContext conext = new ClassPathXmlApplicationContext("applicationContext.xml") ;
//		if(name.equals("java")) {
//			return  (ICourse)conext.getBean("javaCourse");//new->从ioc获取
//		}else {
//			return (ICourse)conext.getBean("htmlCourse");
//		}
//	}
	
}
