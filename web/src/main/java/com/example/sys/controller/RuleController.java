package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.entity.SysPermission;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/rule")
public class RuleController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public JsonResult findAll(){
        return permissionService.findAll();
    }

    @GetMapping("/findPage")
    public JsonResult findPage(int page,int limit){
        return permissionService.findRulePage(page,limit);
    }

    @PostMapping("/findPageFilter")
    public JsonResult findPageFilter(@RequestBody RequestFilter requestFilter){
        return permissionService.findRulePageFilter(requestFilter);
    }



    @PostMapping("/addPermission")
    public JsonResult addPermission(SysPermission  permission){
        return permissionService.addPermission(permission);
    }

    @PostMapping("/editPermission")
    public JsonResult editPermission(SysPermission permission){
        return permissionService.editPermission(permission);
    }

}
