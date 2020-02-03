package org.boot.mumu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.boot.mumu.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BootController {
	@ResponseBody
	@RequestMapping("/request")
	public String request() {
		return "hello spring boot web!!";
	}
	@RequestMapping("/welcome")
	public String welcome(Map<String,Object> map) {
		map.put("welcome", "welcome--hahahhaha");
		return "welcome";
	}
	@RequestMapping("/welcome1")
	public String welcome1(Map<String,Object> map) {
		List<Product> prods = new ArrayList<>();
		
		prods.add(new Product("a",1000,100));
		prods.add(new Product("b",1001,101));
		prods.add(new Product("c",1002,102));
		prods.add(new Product("d",1003,103));
		
		map.put("prods", prods);
		return "welcome";
	}
}
