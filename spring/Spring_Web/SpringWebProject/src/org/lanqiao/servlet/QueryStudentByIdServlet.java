package org.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.service.IStudentService;
import org.lanqiao.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentByIdServlet
 */
public class QueryStudentByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  IStudentService studentService ;//通过springioc容器将 studentService 注入给Servlet
	
       
    public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
		System.out.println(studentService);
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		IStudentService studentService = new StudentServiceImpl();
		
		
		
		String name = studentService.queryStudentById(); 
		request.setAttribute("name", name);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		IStudentService studentService = new StudentServiceImpl();
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
