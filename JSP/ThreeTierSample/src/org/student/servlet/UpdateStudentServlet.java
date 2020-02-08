package org.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class UpdateStudentServlet
 */
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取待修改的人的学号
		int no = Integer.parseInt( request.getParameter("sno") ) ;
		//获取 修改后的内容
		String name =  request.getParameter("sname") ;
		int age  = Integer.parseInt( request.getParameter("sage")) ;
		String address =  request.getParameter("saddress") ;
		//将修改后的内容 封装到一个实体类中
		Student student = new Student(name,age,address) ; 
		
		StudentServiceImpl service = new StudentServiceImpl();
		boolean result = service.updateStudentBySno(no, student);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
//			response.getWriter().println("修改成功！");
			response.sendRedirect("QueryAllStudentsServlet");//修改完毕后 ，再次重新查询全部的学生 并显示
		}else {
			response.getWriter().println("修改失败！");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
