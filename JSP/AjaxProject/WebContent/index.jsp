<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript">
	function register()
	{
		
		var mobile = document.getElementById("mobile").value;
		//通过ajax异步方式 请求服务端
		xmlHttpRequest = new XMLHttpRequest();
		
		//设置xmlHttpRequest对象的回调函数
		xmlHttpRequest.onreadystatechange = callBack  ;
		
		xmlHttpRequest.open("post","MobileServlet",true);
		//设置post方式的 头信息
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttpRequest.send("mobile="+mobile);//k=v    
	}
	
	function registerGet()
	{
		
		var mobile = document.getElementById("mobile").value;
		//通过ajax异步方式 请求服务端
		xmlHttpRequest = new XMLHttpRequest();
		
		//设置xmlHttpRequest对象的回调函数
		xmlHttpRequest.onreadystatechange = callBack  ;
		
		xmlHttpRequest.open("get","MobileServlet?mobile="+mobile,true);
		//设置post方式的 头信息  ,get不需要
		//xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttpRequest.send(null);//k=v      
	}
	
	//定义回调函数 (接收服务端的返回值)
	function callBack(){
		if(xmlHttpRequest.readyState ==4 && xmlHttpRequest.status   ==200){
			//接收服务端返回的数据
			var data = xmlHttpRequest.responseText ;//服务端返回值为string格式
			alert(data.length +"==="+data)
			if(data == "true"){
				alert("请号码已存在,请更换！");
			}else{
				alert("注册成功！");
			}
		}
	}

</script>


<title>Insert title here</title>
</head>
<body>
		手机：<input  id="mobile"/>
		<br/>
		<input type="button" value="注册" onclick="registerGet()" />


</body>
</html>