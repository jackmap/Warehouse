package com.example.sys.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return permissionService.findRulePage(page-1,limit);
    }

}
