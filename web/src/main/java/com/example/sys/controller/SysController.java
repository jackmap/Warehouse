package com.example.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysController {
    @RequestMapping("/login")
    public  String Login(){
        return "login";
    }

    @RequestMapping("/")
    public  String Index(){
        return "index";
    }


    @RequestMapping("/index")
    public  String Index1(){
        return "index";
    }

    @RequestMapping("/log")
    public  String Log(){
        return "log";
    }


    @RequestMapping("/welcome")
    public  String Welcome(){
        return "welcome";
    }

    @RequestMapping("/sys/user")
    public  String User(){
       return "/sys/user/user";
    }

    @RequestMapping("/sys/user/add")
    public  String AddUser(){
        return "/sys/user/add";
    }

    @RequestMapping("/sys/user/edit")
    public  String EditUser(){
        return "/sys/user/edit";
    }

    @RequestMapping("/sys/rule")
    public  String Rule(){
        return "/sys/rule/rule";
    }

    @RequestMapping("/sys/role")
    public  String Role(){
        return "/sys/role/role";
    }

    @RequestMapping("/sys/role/add")
    public  String AddRole(){
        return "/sys/role/add";
    }

    @RequestMapping("/sys/role/edit")
    public  String EditRole(){
        return "/sys/role/edit";
    }


    @RequestMapping("/sys/cate")
    public  String Cate(){
        return "/sys/rule/cate";
    }
}
