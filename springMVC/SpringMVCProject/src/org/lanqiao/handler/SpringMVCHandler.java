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

//�ӿ�/��    ע��   ����
//@SessionAttributes(value="student4")  //���Ҫ��request�д��student4������ͬʱ���ö��� ����session����
//@SessionAttributes(types= {Student.class,Address.class})  //���Ҫ��request�д��Student���͵Ķ�����ͬʱ�������Ͷ��� ����session����
@Controller
@RequestMapping(value="handler") //ӳ��
public class SpringMVCHandler {
		@RequestMapping(value="welcome",method=RequestMethod.POST,params= {"name=zs","age!=23","!height"})//ӳ��
		public String  welcome() {
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}		
		
		@RequestMapping(value="testException")
		public String  testException() {
			System.out.println(1/0);
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}		
		
		
		@RequestMapping(value="welcome2",headers= {"Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8","Accept-Encoding=gzip, deflate"})
		public String  welcome2() {
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="welcome3/**/test")
		public String  welcome3() {
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="welcome4/a?c/test")
		public String  welcome4() {
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		@RequestMapping(value="welcome5/{name}")
		public String  welcome5(@PathVariable("name") String name ) {
			System.out.println(name);
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.POST)
		public String  testPost(@PathVariable("id") Integer id) {
			System.out.println("post���� " +id);
			//Service��ʵ�� ��������
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.GET)
		public String  testGet(@PathVariable("id") Integer id) {
			System.out.println("get���� " +id);
			//Service��ʵ�� ��������
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.DELETE)
		public String  testDelete(@PathVariable("id") String id) {
			System.out.println("delete��ɾ " +id);
			//Service��ʵ�� ��������
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testRest/{id}",method=RequestMethod.PUT)
		public String  testPut(@PathVariable("id") Integer id) {
			System.out.println("put���� " +id);
			//Service��ʵ�� ��������
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testParam")
		public String  testParam(@RequestParam("uname") String name,@RequestParam(value="uage",required=false,defaultValue="23") Integer age) {
//			String name = request.getParameter("uname");
			
			System.out.println(name);
			System.out.println(age);
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testRequestHeader")
		public String  testRequestHeader(@RequestHeader("Accept-Language")  String al  ) {
			System.out.println( al);
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
	
		@RequestMapping(value="testCookieValue")
		public String  testCookieValue(@CookieValue("JSESSIONID") String jsessionId) {
			System.out.println( jsessionId);
			return "success" ;//  views/success.jsp��Ĭ��ʹ���� ����ת���� ��ת��ʽ
		}
		
		@RequestMapping(value="testObjectProperties")
		public String  testObjectProperties(Student student) {//student���� ���� �� form���е�����Nameֵһ�£�֧�ּ������ԣ�
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
		public ModelAndView testModelAndView() {//ModelAndView:�������ݣ�������ͼ
			//ModelAndView:Model -M     View-V
			ModelAndView mv = new ModelAndView("success");//view:  views/success.jsp 
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			
			mv.addObject("student", student);//�൱��request.setAttribute("student", student);
			return mv;
		}
		
		@RequestMapping(value="testModelMap")
		public String testModelMap(ModelMap mm) {//success
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			
			mm.put("student2", student);//request��
			//forward:           redirect:  
			//
			return "redirect:/views/success.jsp";  
		}
		
		@RequestMapping(value="testMap")
		public String testMap(Map<String,Object> m) {
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			m.put("student3", student);//request��
			
			return "success";
		}
		
		@RequestMapping(value="testModel")
		public String testModel(Model model) {
			
			Student student = new Student() ;
			student.setId(2);
			student.setName("zs");
			model.addAttribute("student4",student);//request��
			return "success";
		}
		
		
		@ModelAttribute//���κ�һ������ǰ��������ִ��@ModelAttribute���εķ���
		//@ModelAttribute  ������ ����ĸ�������ǰ ����ִ�е�����ǻ���һ��˼�룺һ�������� ֻ��һ������
		public void queryStudentById(Map<String,Object> map) {
			//StuentService stuService = new StudentServiceImpl();
			//Student student = stuService.queryStudentById(31);
			//ģ����������ѯ���ݿ�Ĳ���
			Student student = new Student();
			student.setId(31);
			student.setName("zs");
			student.setAge(23);
//			map.put("student", student) ;//Լ����map��key ���Ƿ������� ���͵�����ĸСд
			map.put("stu", student) ;//Լ����map��key ���Ƿ������� ���͵�����ĸСд
		}
		
		//�޸�:Zs-ls
		@RequestMapping(value="testModelAttribute")
		public String testModelAttribute(@ModelAttribute("stu")Student student) {
			student.setName(student.getName());//�������޸�Ϊls
			System.out.println(student.getId()+","+student.getName()+","+student.getAge());
			return "success";
		}
		
		
		
		@RequestMapping(value="testI18n")
		public String testI18n() {
			return "success";
		}
		
		
		
		@RequestMapping(value="testConverter")
		public String testConverter(@RequestParam("studentInfo")  Student student) {// ǰ�ˣ�2-zs-23  
			
			System.out.println(student.getId()+","+student.getName()+","+student.getAge());
			return "success";
		}
		
		@ResponseBody//����SpringMVC����ʱ�ķ��� ����һ�� Viewҳ�棬����һ�� ajax���õķ���ֵ��Json���飩
		@RequestMapping(value="testJson")
		public List<Student> testJson() {
			//Controller-Service-dao
			//StudentService studentService = new StudentServiceImp();
//			List<Student> students =  studentService.qeuryAllStudent();
			//ģ�����service�Ĳ�ѯ����
			
			Student stu1 = new Student(1,"zs",23);
			Student stu2 = new Student(2,"ls",24);
			Student stu3 = new Student(3,"ww",25);
			List<Student> students = new ArrayList<>();
			students.add(stu1) ;
			students.add(stu2) ;
			students.add(stu3) ;
			
			return students;
		}
		
		
		@RequestMapping(value="testDateTimeFormat")//���Student��ʽ�������Ὣ������Ϣ ����result��
		public String testDateTimeFormat(@Valid Student student, BindingResult result ,Map<String,Object> map) {
			
//			System.out.println(student.getId()+","+student.getName()+","+student.getBirthday());
			
			if(result.getErrorCount() >0) {
				for(FieldError error:  result.getFieldErrors() ) {
					System.out.println(error.getDefaultMessage());
					map.put("errors", result.getFieldErrors()  ) ;//��������Ϣ����requset���е�errors��
//					result.getFieldErrors().get(0).getDefaultMessage()
				}
			}
			return "success";
		}
		
		
		//�ļ��ϴ�������
		@RequestMapping(value="testUpload") //abc.png
		public String testUpload(@RequestParam("desc") String desc  , @RequestParam("file") MultipartFile file  ) throws IOException {
			
			System.out.println("�ļ�������Ϣ��"+desc);
			//jsp���ϴ����ļ���file
			
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
			//��file�ϴ����������е� ĳһ��Ӳ���ļ���
		System.out.println("�ϴ��ɹ���");
			
			return "success";
		}
		
		
		
		@RequestMapping(value="testInterceptor") 
		public String testInterceptor( )  {
		System.out.println("��������ķ���....��");
			
			return "success";
		}
				
		
		
}
