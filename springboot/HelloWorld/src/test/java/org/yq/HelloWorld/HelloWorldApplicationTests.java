package org.yq.HelloWorld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.yq.HelloWorld.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {
	
	 Logger logger =  LoggerFactory.getLogger(HelloWorldApplicationTests.class  );
	
	@Autowired
	Student student ;
	@Autowired
	ApplicationContext context ;//spring ioc容器
	
	@Test
	public void contextLoads() {
		System.out.println(student);
	}
	
	@Test
	public void testLog(){//日志级别
		logger.trace("trace********");
		logger.debug("debug********");
		logger.info("info*******");
		logger.warn("warn******");
		logger.error("error****");
	}
	
	
//	@Test
//	public void test(){
//		StudentService stuService = (StudentService)context.getBean("stuService") ;
//		System.out.println(stuService+"=========");
//	}

}
