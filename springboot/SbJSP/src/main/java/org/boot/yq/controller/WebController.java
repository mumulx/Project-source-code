package org.boot.yq.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@RequestMapping("/welcome")
	public String welcome(Map<String,Object> map){
		map.put("name", "zs");
		
		return "index" ; //前缀+index+后缀
	}
}
