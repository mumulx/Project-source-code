<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %><%--
  Created by IntelliJ IDEA.
  User: YANQUN
  Date: 2019/2/25
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>mytitle</title>
  </head>
  <body>
    hello idea111
  <a href="MyServlet">MyServlet</a>

  <br/>
  <br/>
    <%
      Context ctx =  new InitialContext() ;
    String testJndi =  (String)ctx.lookup("java:comp/env/jndiName");

    out.print(testJndi);
    %>

  </body>
</html>
