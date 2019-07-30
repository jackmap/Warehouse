package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.entity.Company;
import com.example.controller.sys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/sys/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/findPage")
    public JsonResult findPage(String start,String end,String companyName,String companyType,int page,int limit){
        JsonResult  result = null;
        try {
            result = companyService.findCompanyPage(start,end,companyName,companyType,page,limit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/addCompany")
    public JsonResult addCompanyOne(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PostMapping("/editCompany")
    public JsonResult editCompany(@RequestBody Company company){
        return companyService.editCompany(company);
    }

    @PostMapping("/delete/{rid}")
    public JsonResult deleteCompany(@PathVariable Integer companyid){
        return companyService.deleteCompany(companyid);
    }

}
