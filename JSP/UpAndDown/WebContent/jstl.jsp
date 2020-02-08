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
	<%-- 
		request.setAttribute("name", "zhangsan") ;
	--%>
		<c:set var="name"    value="zhangsan"   scope="request"/>
		${requestScope.name }
		<br/>
		=======给普通对象的属性赋值========<br/>
		${requestScope.student.sname } <br/>
		
		<c:set target="${requestScope.student}" property="sname"  value="zxs" />
		${requestScope.student.sname } <br/>

	=======给map对象的属性赋值========<br/>
		${requestScope.countries.cn }<br/>
		<c:set target="${requestScope.countries}" property="cn"  value="中国" />
			${requestScope.countries.cn }
			
			
		=======给不存在的变量赋值========<br/>
	<c:set var="x" value="y" scope="request"/>
		${requestScope.x }
		=======给不存在的对象赋值========
		
		
		<br/>
		<br/>
		<br/>
		
		=======c:out====<br/>
		
		传统EL：${requestScope.student }<br/>
		c:out方式：<c:out value="${requestScope.student }"   /><br/>
		c:out显示不存在的数据：<c:out value="${requestScope.stu }"   default="zs-23"  /><br/>
		<a href="https://www.baidu.com">百度</a><br/>
		
		
		true:<c:out value='<a href="https://www.baidu.com">百度</a>'  escapeXml="true" />
		false：<c:out value='<a href="https://www.baidu.com">百度</a>' escapeXml="false" />
		
		<c:set  var="a" value="b" scope="request"/><br/>
		显示：${a }
		<c:remove var="a" scope="request"/>
		删除后再显示：${a }
		
		
</body>
</html>