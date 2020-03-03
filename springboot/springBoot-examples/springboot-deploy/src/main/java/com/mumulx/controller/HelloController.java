package com.mumulx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello11")
    public String hello(){

        // 访问放在static

        return "hello";

    }
    @GetMapping("/error")
    public String error(){

        return "error";

    }
}
