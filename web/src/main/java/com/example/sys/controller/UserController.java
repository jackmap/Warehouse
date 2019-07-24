package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return userService.findAll();
    }

    @PostMapping("/findPageFilter")
    public JsonResult findPage(@RequestBody RequestFilter requestFilter){
        return userService.findUserPage(requestFilter);
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return userService.findUserPage(page,limit);
    }

    @PostMapping("/addUser")
    public JsonResult addUser(SysUser user){
        return userService.addUser(user);
    }

    @PostMapping("/editUser")
    public JsonResult editUser(SysUser user){
        return userService.editUser(user);
    }
}
