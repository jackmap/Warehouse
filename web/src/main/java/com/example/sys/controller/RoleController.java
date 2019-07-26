package com.example.sys.controller;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysRole;
import com.example.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public JsonResult findPage(String start,String end,String username,int page,int limit){
        JsonResult  result = null;
        try {
            result = roleService.findPage(start,end,username,page,limit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

//    @PostMapping("/findPage")
//    public JsonResult findPageFilter(@RequestBody RequestFilter requestFilter){
//        return roleService.findPageFilter(requestFilter);
//    }
    @PostMapping("/add")
    public JsonResult addRole(SysRole role){
        return roleService.addRole(role);
    }

    @PostMapping("/edit")
    public JsonResult editRole(SysRole role){
        return roleService.editRole(role);
    }

    @PostMapping("/delete/{rid}")
    public JsonResult deleteRole(@PathVariable Integer rid){
        return roleService.deleteRole(rid);
    }

    @PostMapping("/ChangState/{rid}")
    public JsonResult ChangState(@PathVariable Integer rid){
        return roleService.ChangRoleState(rid);
    }
}
