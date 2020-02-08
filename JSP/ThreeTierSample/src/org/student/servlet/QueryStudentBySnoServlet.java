package org.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentBySnoServlet
 */
public class QueryStudentBySnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentBySnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no  = Integer.parseInt( request.getParameter("sno"))  ;
		StudentServiceImpl service  = new StudentServiceImpl();
		Student student = service.queryStudentBySno(no) ;
		System.out.println(student);
		//将此人的数据 通过前台jsp显示:studentInfo.jsp
		
		request.setAttribute("student", student);//请查询到的学生 放入request域中
		
		//如果request域没有数据,使用重定向跳转response.sendRedirect();
		//如果request域有数据 (request.setAttribute()  ),使用请求转发跳转
		request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
