<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${10>2 }" var="result" scope="request">
			真
			${requestScope.result }
	</c:if>
	
	<c:set var="role" value="学生"  scope="request"></c:set>
	
	
	
	
	<c:choose>
		<c:when test="${requestScope.role == '老师' }"> 
				老师代码...
		  </c:when>
		<c:when test="${requestScope.role eq '学生' }"> 
		     	学生代码...  
		</c:when>
		<c:otherwise>  
				管理员等其他人员....
		 </c:otherwise>
	</c:choose>
	
	
	=============
	<c:forEach  begin="0"  end="5" step="1"  varStatus="status">
		${status.index }
		  test....
	</c:forEach>
	=====<br/>
	<c:forEach  var="name" items="${requestScope.names }" >
		-${name }-
	</c:forEach>

	************<br/>
	
	<c:forEach  var="student" items="${requestScope.students }" >
		${student.sname }-${student.sno }
	
	</c:forEach>
	

</body>
</html>