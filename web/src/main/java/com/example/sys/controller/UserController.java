package com.example.sys.controller;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysUser;
import com.example.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    @PostMapping("/add")
    public JsonResult addUser(SysUser user){
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    public JsonResult editUser(SysUser user){
        return userService.editUser(user);
    }
}
