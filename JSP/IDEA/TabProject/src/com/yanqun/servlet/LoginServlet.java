package com.yanqun.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =  request.getParameter("uname") ;
        String pwd =  request.getParameter("upwd") ;
        //zs  ,abc
        if(name.equals("zs") && pwd.equals("abc")){
            //登录成功，将当前用户名  保存到session
            request.getSession().setAttribute("name",name); //User user = new User();

            request.getRequestDispatcher("show.jsp").forward(request,response);
        }else{
            //跳转到失败页面
            response.sendRedirect("error.jsp");
        }
    }
}
