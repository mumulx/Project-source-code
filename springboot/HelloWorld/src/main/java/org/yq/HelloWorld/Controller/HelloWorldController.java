package org.yq.HelloWorld.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@ResponseBody
	@RequestMapping("helloWorld")
	public String helloWorld() {
		return "hello world;hello spring boot" ;
	}
}	//传统写法： org.yq.HelloWorld.Controller手工写到scan扫描器中（即加入spring容器）
	//spring  boot自动配置：将org.yq.HelloWorld;及org.yq.HelloWorld.xxx 纳入spring容器
