<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>tomcata</title>
  </head>
  <body>
		服务器地址
		<%
				out.print(request.getRemoteAddr()+":"+request.getLocalPort()  ) ;	
		%>
		<br/>
		sessionID:
		<%
				out.print(session.getId());		
		%>
  </body>
</html>
