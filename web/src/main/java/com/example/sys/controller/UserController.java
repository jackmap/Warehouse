package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return userService.findAll();
    }


    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return userService.findUserPage(page-1, limit);
    }
}
