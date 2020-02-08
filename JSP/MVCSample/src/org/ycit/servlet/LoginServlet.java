package org.ycit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ycit.dao.Login;
import org.ycit.dao.LoginDao;
// 控制器层：接受biew请求，并分发给Model处理
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//处理登录请求
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("uanme");
		String pwd =request.getParameter("upwd");
		Login login =new Login(name,pwd);//用户名密码
		//调用模型层的登录功能
		int result=LoginDao.login(login);
		if(result>0) {//成功
			response.sendRedirect("Welcome.jsp");
			
		}else {//登陆失败
			
			response.sendRedirect("login.jsp");
	
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
