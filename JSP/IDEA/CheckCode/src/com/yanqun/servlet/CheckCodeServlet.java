package com.yanqun.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String resultTip = "imgs/wrong.jpg";
        //获取用户输入验证码
       String checkcodeClient =  request.getParameter("checkcode");

       //真实的验证码值
       String checkcodeServer = (String) request.getSession().getAttribute("CKECKCODE");
       if(checkcodeServer.equals(checkcodeClient)){
           resultTip = "imgs/right.jpg";
       }
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();//输出流
        writer.write(resultTip);
        writer.flush();
        writer.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
