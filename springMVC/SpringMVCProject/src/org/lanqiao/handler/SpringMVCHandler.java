package org.lanqiao.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.lanqiao.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//接口/类    注解   配置
//@SessionAttributes(value="student4")  //如果要在request中存放student4对象，则同时将该对象 放入session域中
//@SessionAttributes(types= {Student.class,Address.class})  //如果要在request中存放Student类型的对象，则同时将该类型对象 放入session域中
@Controller
@RequestMapping(value="handler") //映射
public class SpringMVCHandler {
		@RequestMapping(value="welcome",method=RequestMethod.POST,params= {"name=zs","age!=23","!height"})//映射
		public String  welcome() {
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}		
		
		@RequestMapping(value="testException")
		public String  testException() {
			System.out.println(1/0);
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}		
		
		
		@RequestMapping(value="welcome2",headers= {"Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8","Accept-Encoding=gzip, deflate"})
		public String  welcome2() {
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="welcome3/**/test")
		public String  welcome3() {
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="welcome4/a?c/test")
		public String  welcome4() {
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		@RequestMapping(value="welcome5/{name}")
		public String  welcome5(@PathVariable("name") String name ) {
			System.out.println(name);
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.POST)
		public String  testPost(@PathVariable("id") Integer id) {
			System.out.println("post：增 " +id);
			//Service层实现 真正的增
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.GET)
		public String  testGet(@PathVariable("id") Integer id) {
			System.out.println("get：查 " +id);
			//Service层实现 真正的增
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.DELETE)
		public String  testDelete(@PathVariable("id") String id) {
			System.out.println("delete：删 " +id);
			//Service层实现 真正的增
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.PUT)
		public String  testPut(@PathVariable("id") Integer id) {
			System.out.println("put：改 " +id);
			//Service层实现 真正的增
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testParam")
		public String  testParam(@RequestParam("uname") String name,@RequestParam(value="uage",required=false,defaultValue="23") Integer age) {
//			String name = request.getParameter("uname");
			
			System.out.println(name);
			System.out.println(age);
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testRequestHeader")
		public String  testRequestHeader(@RequestHeader("Accept-Language")  String al  ) {
			System.out.println( al);
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
	
		@RequestMapping(value="testCookieValue")
		public String  testCookieValue(@CookieValue("JSESSIONID") String jsessionId) {
			System.out.println( jsessionId);
			return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
		}
		
		@RequestMapping(value="testObjectProperties")
		public String  testObjectProperties(Student student) {//student属性 必须 和 form表单中的属性Name值一致（支持级联属性）
		/*
			String name = 	request.getParameter("name");
		int age= Integer.parseInt(request.getParameter("age")s)	;
		String haddrss = 	request.getParameter("homeaddress");
		String saddress = 	request.getParameter("schooladdress");
		Address address = new Address();
		address.setHomeAddress(haddrss);
		address.setSchoolAddress(saddress);
		
			Student student = new Student();
			student.setName(name);
			student.setAddress(address);
		*/	
//			System.out.println(student.getId()+","+student.getName()+","+student.getAddress().getHomeAddress()+","+student.getAddress().getSchoolAddress());
			return "success" ;
		}
		@RequestMapping(value="testServletAPI")
		public String testServletAPI(HttpServletRequest  request,HttpServletResponse response) {
//			request.getParameter("uname") ;
			System.out.println(request);
			return "success" ;//succes.jsp
		}
		
		@RequestMapping(value="testModelAndView")
		public ModelAndView testModelAndView() {//ModelAndView:既有数据，又有视图
			//ModelAndView:Model -M     View-V
			ModelAndView mv = new ModelAndView("success");//view:  views/success.jsp 
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			
			mv.addObject("student", student);//相当于request.setAttribute("student", student);
			return mv;
		}
		
		@RequestMapping(value="testModelMap")
		public String testModelMap(ModelMap mm) {//success
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			
			mm.put("student2", student);//request域
			//forward:           redirect:  
			//
			return "redirect:/views/success.jsp";  
		}
		
		@RequestMapping(value="testMap")
		public String testMap(Map<String,Object> m) {
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			m.put("student3", student);//request域
			
			return "success";
		}
		
		@RequestMapping(value="testModel")
		public String testModel(Model model) {
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			model.addAttribute("student4",student);//request域
			return "success";
		}
		
		
		@ModelAttribute//在任何一次请求前，都会先执行@ModelAttribute修饰的方法
		//@ModelAttribute  在请求 该类的各个方法前 均被执行的设计是基于一个思想：一个控制器 只做一个功能
		public void queryStudentById(Map<String,Object> map) {
			//StuentService stuService = new StudentServiceImpl();
			//Student student = stuService.queryStudentById(31);
			//模拟调用三层查询数据库的操作
			Student student = new Student();
			student.setId(31);
			student.setName("zs");
			student.setAge(23);
//			map.put("student", student) ;//约定：map的key 就是方法参数 类型的首字母小写
			map.put("stu", student) ;//约定：map的key 就是方法参数 类型的首字母小写
		}
		
		//修改:Zs-ls
		@RequestMapping(value="testModelAttribute")
		public String testModelAttribute(@ModelAttribute("stu")Student student) {
			student.setName(student.getName());//将名字修改为ls
			System.out.println(student.getId()+","+student.getName()+","+student.getAge());
			return "success";
		}
		
		
		
		@RequestMapping(value="testI18n")
		public String testI18n() {
			return "success";
		}
		
		
		
		@RequestMapping(value="testConverter")
		public String testConverter(@RequestParam("studentInfo")  Student student) {// 前端：2-zs-23  
			
			System.out.println(student.getId()+","+student.getName()+","+student.getAge());
			return "success";
		}
		
		@ResponseBody//告诉SpringMVC，此时的返回 不是一个 View页面，而是一个 ajax调用的返回值（Json数组）
		@RequestMapping(value="testJson")
		public List<Student> testJson() {
			//Controller-Service-dao
			//StudentService studentService = new StudentServiceImp();
//			List<Student> students =  studentService.qeuryAllStudent();
			//模拟调用service的查询操作
			
			Student stu1 = new Student(1,"zs",23);
			Student stu2 = new Student(2,"ls",24);
			Student stu3 = new Student(3,"ww",25);
			List<Student> students = new ArrayList<>();
			students.add(stu1) ;
			students.add(stu2) ;
			students.add(stu3) ;
			
			return students;
		}
		
		
		@RequestMapping(value="testDateTimeFormat")//如果Student格式化出错，会将错误信息 传入result中
		public String testDateTimeFormat(@Valid Student student, BindingResult result ,Map<String,Object> map) {
			
//			System.out.println(student.getId()+","+student.getName()+","+student.getBirthday());
			
			if(result.getErrorCount() >0) {
				for(FieldError error:  result.getFieldErrors() ) {
					System.out.println(error.getDefaultMessage());
					map.put("errors", result.getFieldErrors()  ) ;//将错误信息传入requset域中的errors中
//					result.getFieldErrors().get(0).getDefaultMessage()
				}
			}
			return "success";
		}
		
		
		//文件上传处理方法
		@RequestMapping(value="testUpload") //abc.png
		public String testUpload(@RequestParam("desc") String desc  , @RequestParam("file") MultipartFile file  ) throws IOException {
			
			System.out.println("文件描述信息："+desc);
			//jsp中上传的文件：file
			
			InputStream input = file.getInputStream() ;//IO
			String fileName = file.getOriginalFilename() ;
			
			OutputStream out = new FileOutputStream("d:\\"+fileName) ;
			
			
			byte[] bs = new byte[1024];
			int len = -1;
			while(( len = input.read(bs)) !=-1 ) {
				out.write(bs, 0, len);
			}
			out.close();
			input.close();
			//将file上传到服务器中的 某一个硬盘文件中
		System.out.println("上传成功！");
			
			return "success";
		}
		
		
		
		@RequestMapping(value="testInterceptor") 
		public String testInterceptor( )  {
		System.out.println("处理请求的方法....！");
			
			return "success";
		}
				
		
		
}
