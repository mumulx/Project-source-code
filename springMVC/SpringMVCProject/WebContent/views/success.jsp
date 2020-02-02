<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

		<c:forEach  items="${requestScope.errors}" var="error">
				${error.getDefaultMessage()}<br/>
		</c:forEach>
		
		
		<br/>
	aaaaaaaaaaaa<br/>
		welcome  to spring mvc success<br/>
		==== request:<br/>
		${requestScope.student.id } -${requestScope.student.name } <br/>
		${requestScope.student2.id } -${requestScope.student2.name }  <br/>
		${requestScope.student3.id } -${requestScope.student3.name }  <br/>
		${requestScope.student4.id } -${requestScope.student4.name }  <br/>
		
		==== session:<br/>
		${sessionScope.student.id } -${sessionScope.student.name } <br/>
		${sessionScope.student2.id } -${sessionScope.student2.name }  <br/>
		${sessionScope.student3.id } -${sessionScope.student3.name }  <br/>
		${sessionScope.student4.id } -${sessionScope.student4.name }  <br/>
</body>
</html>