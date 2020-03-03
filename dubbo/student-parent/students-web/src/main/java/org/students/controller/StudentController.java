package org.students.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.students.pojo.Student;
import org.students.service.StudentService;

import com.alibaba.dubbo.config.annotation.Reference;
//@RestController
@Controller
@RequestMapping("controller")
public class StudentController {
	@Reference //AutoWire
	private  StudentService studentService ;
	
	@RequestMapping("queryStudentByNo")
	public ModelAndView queryStudentByNo() {
		ModelAndView mv = new ModelAndView("success");
		
		Student student = studentService.queryStudentByStuNo(1) ;
		mv.addObject("student",student) ;//request域
		
		System.out.println(student.getStuNo()+","+student.getStuName()+","+student.getStuAge());
		
		return mv;
	}
	
	@RequestMapping("addStudent")
	public String addStudent() {
		Student student = new Student(66,"zs6",68);
		studentService.addStudent(student);
		return "success" ;
	}
	
	
}
