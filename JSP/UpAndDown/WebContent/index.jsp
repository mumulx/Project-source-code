<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>			
		
		
		
		----------EL---点操作符-------------------<br/>
		学生对象：${requestScope.student}<br/>
		学号：${requestScope.student.sno}<br/>
		
		===姓名：${requestScope.student.sname}<br/>
		
		学校地址：${requestScope.student.address.schoolAddress}<br/>
		家庭地址：${requestScope.student.address.homeAddress}<br/>
		he-llo的值：${requestScope.he-llo }
		
		<br/>	----------EL------中括号操作符----------------<br/>
		学生对象：${requestScope.student}<br/>
		学号344：${requestScope.student['sno']}<br/>
		===姓名：${requestScope.student[x]}<br/>
		
		学校地址：${requestScope.student["address"]["schoolAddress"]  }<br/>
		家庭地址xx：${requestScope.student["address"]["homeAddress"]  }<br/>
		he-llo的值：${requestScope["he-llo"] }
		
		处理数组：
		<br/>
		x1xx:${requestScope.arr[0] }、
		xx2x:${requestScope.arr[1] }、
		xx3x:${requestScope.arr[2] }、
		
		===<br/>
		${requestScope.countries.cn }、
		${requestScope.countries["us"] }



	

<br/>								
<br/>								
<br/>								
		<form action="UploadServet" method="post"  enctype="multipart/form-data">
			学号：<input name="sno" /><br/>
			姓名：<input name="sname" /><br/>
			上传照片: <input type="file"  name="spicture"/>
			<br/>
			<input type="submit" value="注册"/>
		</form>
		<a href="DownloadServlet?filename=图片.png">图片</a>
		
</body>
</html>