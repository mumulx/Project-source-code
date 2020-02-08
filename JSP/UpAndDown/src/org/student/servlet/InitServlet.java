package org.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Address;
import org.student.entity.Student;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student();
		student.setSno(1);
		student.setSname("zs");
		Address add = new Address();
		add.setHomeAddress("xa");
		add.setSchoolAddress("bj");
		
		student.setAddress(add);
		
		request.setAttribute("student", student);
		request.setAttribute("he-llo", "world");
		
		String[] arr = new String[] {"aa","cc","dd"};
		request.setAttribute("he-llo", "world");
		request.setAttribute("arr", arr);
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
		response.setHeader("Pragma","no-cache"); //HTTP 1.0    
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
		
		Map<String,String> map = new HashMap<>();
		map.put("cn", "China");
		map.put("us", "USA");
		
		request.setAttribute("countries", map);
		
		
		String[] names = new String[] {"aaa","bb","ccc"};
		
		request.setAttribute("names", names);
		
		Student stu1 = new Student();
		Student stu2 = new Student() ;
		stu1.setSname("zs");
		stu1.setSno(1);
		stu2.setSname("ls");
		stu2.setSno(2);
		
		List<Student> students = new ArrayList<>();
		students.add(stu1);
		students.add(stu2);
		request.setAttribute("students", students);
		
		request.getRequestDispatcher("jstl2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
