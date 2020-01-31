package org.lanqiao.entity;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
	//查询单个学生
	public static void queryStudentByStuno() throws IOException {
		//Connection -  SqlSession操作MyBatis
				//conf.xml - > reader
				Reader reader = Resources.getResourceAsReader("conf.xml") ;
				//reader  ->SqlSession
				
				//可以通过build的第二参数 指定数据库环境
				SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
				SqlSession session = sessionFacotry.openSession() ;
				String statement = "org.lanqiao.entity.studentMapper.queryStudentByStuno";
				Student student = session.selectOne(statement,1) ;
				System.out.println(student);
				session.close();
	}
	
	  //查询全部学生
 		public static void queryAllStudents() throws IOException {
			//Connection -  SqlSession操作MyBatis
					//conf.xml - > reader
					Reader reader = Resources.getResourceAsReader("conf.xml") ;
					//reader  ->SqlSession
					//可以通过build的第二参数 指定数据库环境
					SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
					SqlSession session = sessionFacotry.openSession() ;
					
					
					
					String statement = "org.lanqiao.entity.studentMapper."+"queryAllStudents";
					List<Student> students = session.selectList(statement) ;
					System.out.println(students);
					session.close();
		}
		
 		
 		 //增加学生
 		public static void addStudent() throws IOException {
 			//Connection -  SqlSession操作MyBatis
					//conf.xml - > reader
					Reader reader = Resources.getResourceAsReader("conf.xml") ;
					//reader  ->SqlSession
					//可以通过build的第二参数 指定数据库环境
					SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
					SqlSession session = sessionFacotry.openSession() ;
					
					String statement = "org.lanqiao.entity.studentMapper."+"addStudent";
					Student student = new Student(3,"ww",25,"s1");
					
					
					int count = session.insert(statement, student );//statement：指定执行的SQL    student：sql中需要的参数 （ ? ? ? ）
					session.commit(); //提交事务
					
					System.out.println("增加"+count+"个学生");
					session.close();
		}
 		
 		
 		 //删除学生
 		public static void delteStudentByStuno() throws IOException {
 			//Connection -  SqlSession操作MyBatis
					//conf.xml - > reader
					Reader reader = Resources.getResourceAsReader("conf.xml") ;
					//reader  ->SqlSession
					//可以通过build的第二参数 指定数据库环境
					SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
					SqlSession session = sessionFacotry.openSession() ;
					
					String statement = "org.lanqiao.entity.studentMapper."+"deleteStudentByStuno";
					
					int count = session.delete(statement,3) ;
					
					session.commit(); //提交事务
					
					System.out.println("删除"+count+"个学生");
					session.close();
		}
	
 		 //修改学生
 		public static void updateStudentByStuno() throws IOException {
 			//Connection -  SqlSession操作MyBatis
					//conf.xml - > reader
					Reader reader = Resources.getResourceAsReader("conf.xml") ;
					//reader  ->SqlSession
					//可以通过build的第二参数 指定数据库环境
					SqlSessionFactory sessionFacotry = new SqlSessionFactoryBuilder().build(reader,"development") ;
					SqlSession session = sessionFacotry.openSession() ;
					
					String statement = "org.lanqiao.entity.studentMapper."+"updateStudentByStuno";
					//修改的参数
					Student student = new Student();
					//修改哪个人，where stuno =2 
					student.setStuNo(2);
					//修改成什么样子？
					student.setStuName("lxs");
					student.setStuAge(44);
					student.setGraName("s2");
					//执行
					int count = session.update(statement,student) ;
					
					session.commit(); //提交事务
					
					System.out.println("修改"+count+"个学生");
					session.close();
		}
	
	
	public static void main(String[] args) throws IOException {
		queryAllStudents();
//		 addStudent() ;
//		delteStudentByStuno();
		updateStudentByStuno();
		queryAllStudents();
	}
}
