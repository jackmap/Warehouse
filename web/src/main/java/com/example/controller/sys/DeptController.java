package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.entity.SysDept;
import com.example.controller.sys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    CompanyService companyService;

    static final  String  companyName="河南唐洛有限公司";

    @RequestMapping("/findAll")
    public JsonResult findAllDept(){
        return companyService.findAllDeptByCompanyName(companyName);
    }

    @GetMapping("/findPage")
    public JsonResult findDeptPage(String start,String end,String deptName,Integer page,Integer limit){
        JsonResult  result = null;
        try {
            result = companyService.findDeptPage(start,end,deptName,page,limit,companyName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/addDept")
    public JsonResult addDept(@RequestBody SysDept dept){
        return companyService.addDept(dept,companyName);
    }

    @PostMapping("/editDept")
    public JsonResult editDept(@RequestBody SysDept dept){
        return companyService.editSysDept(dept);
    }

    @PostMapping("/delete/{rid}")
    public JsonResult deleteDeptBydeptid(@PathVariable Integer deptid){
        return companyService.deleteSysDept(deptid);
    }

}
