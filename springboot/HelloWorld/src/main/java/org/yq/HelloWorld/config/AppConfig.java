package org.yq.HelloWorld.config;

import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yq.HelloWorld.service.StudentService;

//配置类
@Configuration
public class AppConfig {
	
	@Bean		
	public StudentService stuService(){//<bean  id="xxxxxxxxxxxxx">
		StudentService stuService = new StudentService();
		
//		StudentDao stuDao = new StudentDao()  ;
//		stuService.setStudentDao(stuDao);
		
		return stuService;//返回值 <bean  class="xxxxxxxxxxxxx">
	}
}
