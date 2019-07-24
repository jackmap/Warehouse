package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.entity.SysRole;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return roleService.findAllRole();
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return roleService.findPage(page,limit);
    }

    @PostMapping("/findPageFilter")
    public JsonResult findPageFilter(@RequestBody RequestFilter requestFilter){
        return roleService.findPageFilter(requestFilter);
    }
    @PostMapping("/addRole")
    public JsonResult addRole(SysRole role){
        return roleService.addRole(role);
    }

    @PostMapping("/editRole")
    public JsonResult editRole(SysRole role){
        return roleService.editRole(role);
    }

}
