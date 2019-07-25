package com.example.sys.controller;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysPermission;
import com.example.sys.service.PermissionService;
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

    @PostMapping("/add")
    public JsonResult addPermission(SysPermission  permission){
        return permissionService.addPermission(permission);
    }

    @PostMapping("/edit")
    public JsonResult editPermission(SysPermission permission){
        return permissionService.editPermission(permission);
    }

}
