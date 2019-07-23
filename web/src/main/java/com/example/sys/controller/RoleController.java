package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return roleService.findAll();
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return roleService.findPage(page-1, limit);
    }

}
