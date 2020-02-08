package org.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

public class AddStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no =Integer.parseInt( request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(  request.getParameter("sage")) ;
		String address = request.getParameter("saddress");
		Student student  = new Student(no,name,age,address) ;
		
		//接口 x = new 实现类();
		IStudentService studentService = new StudentServiceImpl();
		boolean result = studentService.addStudent(student) ;
		
		/*
		 *   out  request  response session  application 
		 *   out:	PrintWriter out = response.getWriter() ;
		 *   session:	request.getSession()
		 *   application:	request.getServletContext()
		 */
		//设置响应编码
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter() ;//响应对象
		
		/*
		if(result) {
			//out.println("增加成功！");
			//response.sendRedirect("QueryAllStudentsServlet");
		}else {
			//out.println("增加失败！");
			//response.sendRedirect("QueryAllStudentsServlet");
		}*/
		if(!result) {//如果增加失败，给request放入一条数据error
			request.setAttribute("error", "addError");  
		}else {//增加成功
			request.setAttribute("error", "noaddError");  
		}
//		response.sendRedirect("QueryAllStudentsServlet");
		request.getRequestDispatcher("QueryAllStudentsServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
