package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("helloController")
public class HelloController {

    @RequestMapping("/hello")
    public String printHello(){
        System.out.println("打印第一个MVC程序");
        return "success";
    }





}
