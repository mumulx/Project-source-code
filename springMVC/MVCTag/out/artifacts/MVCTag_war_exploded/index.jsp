<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 26069
  Date: 2019/10/4
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

<%--<form:form>
  姓名<form:input path="name"/><br>
  年龄<form:input path="age"/>
  <input type="submit" value="提交">

</form:form>--%>
<form:form action="FormDemo/testMethod" method="post">

  <input type="submit" value="增加">
</form:form>
<form:form action="FormDemo/testMethod" method="delete">

  <input type="submit" value="删除">
</form:form>
<form:form action="FormDemo/testMethod" method="put">

  <input type="submit" value="修改">
</form:form>
<form:form action="FormDemo/testMethod" method="get">

  <input type="submit" value="查询">
</form:form>













  </body>
</html>
