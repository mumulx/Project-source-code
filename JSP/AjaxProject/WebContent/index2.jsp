<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">
	function register()
	{
			var $mobile = $("#mobile").val();
			/*
			$.ajax({
				url:"MobileServlet",
				请求方式:"post",
				data:"mobile="+$mobile, 
				success:function(result,testStatus)
				{
					if(result == "true"){
						alert("已存在！注册失败！");
					}else{
						alert("注册成功！");
					}
				},
				error:function(xhr,errrorMessage,e){
					alert("系统异常！");
				}


				});
				
				
				$.post(
						"MobileServlet",
						"mobile="+$mobile,
					function (result){
							if(result == "true"){
								alert("已存在！注册失败！");
							}else{
								alert("注册成功！");
							}
					},
					"text"
					);
			
				
				$.get(
						"MobileServlet",
						"mobile="+$mobile,
					function (result){
							if(result == "true"){
								alert("已存在！注册失败！");
							}else{
								alert("注册成功！");
							}
					}
					);
				
				$("#tip").load(
						"MobileServlet",
						"mobile="+$mobile
				);
			
			
			var student = {"name":"zs" ,  "age":23} ;
			
			//alert(student.name +"--" +student.age) ;
			//var name = ["xx","xx","xx"] ;
			var students =[
					
					{"name":"zs" ,  "age":23} ,
					{"name":"ls" ,  "age":24} ,
					{"name":"ww" ,  "age":25} 
					
			];
			alert(students[1].name +"--" +students[1].age) ;
			*/
			$.getJSON(
					"MobileServlet",
				//	"mobile="+$mobile,
					{"mobile":$mobile},
				function (result){//msg:true|false
						if(result.msg == "true"){
							alert("已存在！注册失败！");
						}else{
							alert("注册成功！");
						}
				}
			);
	}
	/* json中只有一个对象的情况
	function  testJson()
	{
		$.getJSON(
				"JsonServlet",
				{"name":"zs",  "age":24},
			function (result){
					//js需要通过eval()函数  将返回值 转为一个js能够识别的json对象
				var jsonStudent = 	eval(result.stu1) ;
				alert(jsonStudent.name  +"---"+ jsonStudent.age) ;
				
			}
		);
		
	}
	*/
	
	/* json中有多对象的情况*/
	function  testJson()
	{
		$.getJSON(
				"JsonServlet",
				{"name":"zs",  "age":24},
			function (result){ 
					// result：  {"stu1":stu1, "stu2":stu2,"stu3":stu3 }
					//js需要通过eval()函数  将返回值 转为一个js能够识别的json对象
				var json = 	eval(result) ;
				$.each( json, function(i,element){
					alert( this.name +"---"+ this.age );
				} );
					
					
			}
		);
		
	}
	
	

</script>


<title>Insert title here</title>
</head>
<body>
		手机：<input  id="mobile"/>
		<br/>
		<input type="button" value="注册" onclick="register()" />
		<span id="tip"></span>
		
		<input type="button" value="测试json" onclick="testJson()" />

</body>
</html>