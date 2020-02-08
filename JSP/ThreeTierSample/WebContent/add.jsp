<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		function check()//onsubmit： return true：表单正常提交   return false:表单终止提交
		{
			var sno = $("#sno").val();
			var sname = $("#sname").val();
			var sage = $("#sage").val();
			var saddress = $("#saddress").val();
			if(!(sno>0 && sno <101)){
				alert("学号有误！必须是1-100")
				return false ;
			}
			if (!(sname.length >1 & sname.length<5)){
				alert("姓名长度有误！必须是2-4位")
				return false ;
			}
			
			//if(...) return false ;
			
			return true ;
		}
		
	
	
	
		$(document).ready(function(){
		});
	
	</script>
	
	
<title>Insert title here</title>
</head>
<body>
		<form action="AddStudentServlet" method="post" onsubmit="return check()">
				学号：<input type="text" name="sno" id="sno"/><br/>
				姓名：<input type="text" name="sname" id="sname"/><br/>
				年龄：<input type="text" name="sage" id="sage"/><br/>
				地址：<input type="text" name="saddress" id="saddress"/><br/>
				<input type="submit" value="新增" /><br/>
		
		</form>
</body>
</html>