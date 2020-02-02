package com.ycit.controller;

import com.ycit.entity.Other;
import com.ycit.entity.Persion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/FormDemo")
public class FormDemo {
    @RequestMapping("/testForm")
    public String testForm(Map<String,Object> map){

        Persion per = new Persion();
        per.setAge(23);
        per.setName("zs");
        map.put("command", per);//将per放入了request域中
        return "/index.jsp";
        //return "forward:/views/springForm.jsp";
    }

    @RequestMapping(value = "/testMethod",method = RequestMethod.POST)
    public String testPost(){
        System.out.println("post");
        return "/index.jsp";

    }
    @RequestMapping(value = "/testMethod",method = RequestMethod.DELETE)
    public String testDelete(){
        System.out.println("delete");
        return "/index.jsp";

    }

    @RequestMapping(value = "/testMethod",method =RequestMethod.PUT)
    public String testPut(){
        System.out.println("put");
        return "/index.jsp";

    }
    @RequestMapping(value = "/testMethod",method = RequestMethod.GET)
    public String testGet(){
        System.out.println("get");
        return "/index.jsp";
    }
    @RequestMapping("/testForm2")
    public String testForm2(Map<String,Object> map){

        Persion per = new Persion();
        //per.setAge(23);
        //per.setName("zs");
        per.setSex(false);
        map.put("per", per);//将per放入了request域中
        return "springForm";
        //return "forward:/views/springForm.jsp";
    }
    @RequestMapping("/testForm3")
    public String testForm3(Map<String,Object> map){

        Persion per = new Persion();
        List<String> hobbirs=new ArrayList<>();
        hobbirs.add("football");
        hobbirs.add("basketball");
        per.setHobbies(hobbirs);
        map.put("per", per);//将per放入了request域中
        return "springForm";
    }
    @RequestMapping("/testForm4")
    public String testForm4(Map<String,Object> map){
        Persion per = new Persion();
        Other other = new Other();
        per.setOther(other);
        map.put("per", per);//将per放入了request域中
        return "springForm";
    }

    @RequestMapping("/testForm5")
    public String testForm5(Map<String,Object> map){

        Persion per = new Persion();
        List<String> hobbies=new ArrayList<>();
        hobbies.add("football");
        hobbies.add("basketball");
        per.setHobbies(hobbies);
        map.put("per", per);//将per放入了request域中

/*        List<String> allHobbies=new ArrayList<>();
        allHobbies.add("football");
        allHobbies.add("basketball");
        allHobbies.add("pingpang");
        allHobbies.add("aaa");
        allHobbies.add("bbb");
        map.put("allHobbies", allHobbies);*/
        Map<String, String> allHobbiesMap = new HashMap<>();
        allHobbiesMap.put("football","足球");
        allHobbiesMap.put("basketball","篮球");
        allHobbiesMap.put("pingpang","乒乓球");
        allHobbiesMap.put("aaa","bbb");
        map.put("allHobbies", allHobbiesMap);

        return "springForm";
    }

    @RequestMapping("/testForm6")
    public String testForm6(Map<String,Object> map){
        Persion per = new Persion();
        per.setCountry("China");
        map.put("per", per);//将per放入了request域中
        return "springForm";
    }


    @RequestMapping("/testForm7")
    public String testForm7(Map<String,Object> map){

        Persion per = new Persion();

        per.setFavouriteBall("pingpang");
        map.put("per", per);//将per放入了request域中

        Map<String, String> allHobbiesMap = new HashMap<>();
        allHobbiesMap.put("football","足球");
        allHobbiesMap.put("basketball","篮球");
        allHobbiesMap.put("pingpang","乒乓球");
        allHobbiesMap.put("aaa","bbb");
        map.put("allBallMap", allHobbiesMap);

        return "springForm";
    }










}
