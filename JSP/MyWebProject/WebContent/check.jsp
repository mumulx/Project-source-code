<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="org.lanqiao.dao.LoginDao" %>
<%@ page import="org.lanqiao.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
<body>		
		<%
		
			String name = request.getParameter("uname") ;
			String pwd = request.getParameter("upwd") ;
			Login login = new Login(name,pwd) ;
			
			
			LoginDao dao = new LoginDao();
			
			int result = dao.login(login) ;
			if(result >0){
				out.print("登录成功！");
			}else if(result==0){
				out.print("用户名或密码有误！！");
			}else{
				out.print("系统异常！！");
			}
		
		%>
</body>
</html>