package org.lanqiao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MobileServlet
 */
public class MobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MobileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String mobile =  request.getParameter("mobile") ;
		//假设此时 数据库中 只有一个号码：18888888888
//		if(mobile.equals("18888888888")) {
		
		PrintWriter out = response.getWriter();
		if("18888888888".equals(mobile)) {
			//return true ;
//			out.write("true");//servlet以输出流的方式  将信息 返回给客户端
			//out.write("此号码已存在！");
			//如果客户端是getJSON(),则需要以json格式返回数据
//			out.write( "{\"msg\":\"true\"}"   );//   {"\"msg\":\"true\""}
			out.print( "{\"msg\":\"true\"}"   );//   {"\"msg\":\"true\""}
		}else {
			//return false ;
//			out.write("false");
			//out.write("注册成功！");
//			out.write( "{\"msg\":\"false\"}"   );// "\"msg\":\"false\""    
			out.print( "{\"msg\":\"false\"}"   );// "\"msg\":\"false\""    
		}
		out.close(); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
