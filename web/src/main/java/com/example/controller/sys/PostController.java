package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.entity.SysPost;
import com.example.controller.sys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/sys/post")
public class PostController {

    @Autowired
    CompanyService companyService;

    static final  String  companyName="河南唐洛有限公司";

    @RequestMapping("/findAll")
    public JsonResult findAllDept(){
        return companyService.findAllDeptByCompanyName(companyName);
    }

    @GetMapping("/findPage")
    public JsonResult findPostPage(String start,String end,String postName,int page,int limit){
        JsonResult  result = null;
        try {
            result = companyService.findPostPage(start,end,postName,page,limit,companyName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/addPost")
    public JsonResult addPost(@RequestBody SysPost post){
        return companyService.addPost(post,companyName);
    }

    @PostMapping("/editRole")
    public JsonResult editRole(@RequestBody SysPost post){
        return companyService.editSysPost(post);
    }

    @PostMapping("/delete/{postid}")
    public JsonResult deletePostByPostId(@PathVariable Integer postid){
        return companyService.deleteSysPost(postid);
    }
}
