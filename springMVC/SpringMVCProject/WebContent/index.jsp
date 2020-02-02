<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			$("#testJson").click(function(){
					//通过ajax请求springmvc
					$.post(
						"handler/testJson",//服务器地址
						//{"name":"zs","age":23}
						function(result){//服务端处理完毕后的回调函数 List<Student> students， 加上@ResponseBody后， students实质是一个json数组的格式
							for(var i=0;i<result.length ;i++){
								alert(result[i].id +"-"+result[i].name +"-"+result[i].age);
							}
						}
					);
				
				
			});
			
			
		});
	
	
	</script>

</head>
<body>

	<input type="button" value="testJson" id="testJson" />

<!-- 如果web.xml中的配置是
	  <servlet-mapping>
  			<servlet-name>springDispatcherServlet</servlet-name>
  			<url-pattern>.action</url-pattern>
 	 </servlet-mapping>

	<a href="user/welcome.action">first springmvc - welcome</a>	交由springmvc处理 找 @RuestMapping映射
	<a href="user/welcome.action">first springmvc - welcome</a>交由springmvc处理  找 @RuestMapping映射
	<a href="xxx/welcome">first springmvc - welcome</a>			交由servlet处理  找url-parttern /@WebServlet()
 -->
	<a href="handler/welcome3/xyz/abcz/asb/test">33333333get - welcome</a>
	<br/>
	<a href="handler/welcome4/abc/test">4444444get - welcome</a>
		<br/>
	<a href="handler/welcome5/zs">555welcome</a>
	
	<form action="handler/welcome" method="post">
		name:<input name="name"  ><br/>
		age:<input name="age"  >
		height:<input name="height"  >
		<input type="submit" value="post">
	</form>
	
	<br/>======<br/>

	<form action="handler/testRest/1234" method="post">
		<input type="submit" value="增">
	</form>
	
	<form action="handler/testRest/1234" method="post">
		<input type="hidden"  name="_method" value="DELETE"/>
		<input type="submit" value="删">
	</form>
	
		<form action="handler/testRest/1234" method="post">
			<input type="hidden"  name="_method" value="PUT"/>
		<input type="submit" value="改">
	</form>
	
	<form action="handler/testRest/1234" method="get">
		<input type="submit" value="查">
	</form>
	------------<br/>
	<form action="handler/testParam" method="get">
		name:<input name="uname" type="text" />
		<input type="submit" value="查">
	</form>
	<br/>
	<a href="handler/testRequestHeader">testRequestHeader</a>
	<br/>
	<br/>
	<a href="handler/testCookieValue">testCookieValue</a><br/>
	<a href="handler/testServletAPI">testServletAPI</a>
	<br/>
	<form action="handler/testObjectProperties" method="post">
		id:<input name="id" type="text" />
		name:<input name="name" type="text" />
		家庭地址:<input name="address.homeAddress" type="text" />
		学校地址:<input name="address.schoolAddress" type="text" />
		<input type="submit" value="查">
	</form>
	
	
	<br/>
		<a href="handler/testModelAndView">testModelAndView</a>
	<br/>
	<br/>
		<a href="handler/testModelMap">testModelMap</a>
	<br/>
	<br/>
		<a href="handler/testMap">testMap</a>
	<br/>
	<br/>
		<a href="handler/testModel">testModel</a>
	<br/>
	<br/>
		<a href="handler/testI18n">testI18n</a>
	<br/>
	
	<form action="handler/testModelAttribute" method="post">
		编号:<input name="id" type="hidden" value="31" />
		姓名:<input name="name" type="text" />
		<input type="submit" value="修改">
	</form>
	
	<br/>
		<a href="handler/testMvcViewController">testMvcViewController</a>
	<br/>
	<br/>
		<a href="handler/testInterceptor">testInterceptor</a>
	<br/>
	
	<form action="handler/testConverter" method="post">
		学生信息:<input name="studentInfo" type="text"  /><!-- 2-zs-23  -->
		<input type="submit" value="转换">
	</form>
		
	<form action="handler/testDateTimeFormat" method="post">
		编号:<input name="id" type="text" value="31" />
		姓名:<input name="name" type="text" />
		出生日期:<input name="birthday" type="text" />
		邮箱:<input name="email" type="text" />
		<input type="submit" value="修改">
	</form>
	
		<br/>
		<br/>
		<br/>
	<form action="handler/testUpload" method="post"  enctype="multipart/form-data">
		<input type="file" name="file" />
		描述:<input name="desc" type="text" />
		
		<input type="submit" value="上传">
	</form>
	
	
	
	
	
</body>
</html>