package com.itheima.controller;


import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("paramController")
public class ParamController {

    @RequestMapping("/param")
    public String testParam(User user ){
        System.out.println("user = " + user);
        return  "success";
    }


}
