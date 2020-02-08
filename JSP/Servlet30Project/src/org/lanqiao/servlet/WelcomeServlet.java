package org.lanqiao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet( value="/WelcomeServlet" ,loadOnStartup=1  )
@WebServlet( value="/WelcomeServlet" ,loadOnStartup=1,initParams= {@WebInitParam(name="serveltparaname30",value="servletparavalue30")   }   )
public class WelcomeServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init3.0...");
		String value = super.getInitParameter("serveltparaname30");
		System.out.println("当前Servlet的初始化 参数serveltparaname30的值："+value);
		
		
	}
	@Override
	public void destroy() {
		System.out.println("destroy...3.0..");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet3.0....");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
