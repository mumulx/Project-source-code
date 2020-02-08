package org.student.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.service.IStudentService;
import org.student.service.impl.StudentServiceImpl;

public class DeleteStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除
		request.setCharacterEncoding("utf-8");
		//接收前端传来的 学号
		int no = Integer.parseInt( request.getParameter("sno") );
		
		IStudentService service = new StudentServiceImpl();
		boolean result = service.deleteStudentBySno(no) ;
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
//			out.print();
			//response.getWriter().println("删除成功！");
			response.sendRedirect("QueryAllStudentsServlet");//重新查询 所有学生
		}else {
			response.getWriter().println("删除失败！");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
