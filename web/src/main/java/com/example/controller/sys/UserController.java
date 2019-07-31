package com.example.controller.sys;

import com.example.config.model.ConstantsData;
import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.common.RequestFilter;
import com.example.controller.sys.entity.SysUser;
import com.example.controller.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/findAll")
    public JsonResult findAll(){
        List<SysUser> list= userService.findAll();
        if (list!=null){
           if(!ConstantsData.GetAdminData()){
               list.stream().filter(u->u.getCompanyName().equals(ConstantsData.CompanyName)).collect(Collectors.toList());
               return new JsonResult(0,list,"查询成功");
           }
           return new JsonResult(0,list,"查询成功");
        }
        return new JsonResult(1,"查询失败");
    }

    /** 根据条件查询分页信息
     * @param start
     * @param end
     * @param username
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findPage")
    public JsonResult findPage(String start ,String end,String username,int page,int limit){
        List<SysUser> pages = null;
        try {
           pages=userService.findUserPage(start,end,username,page,limit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(pages)){
            SysUser user=ConstantsData.GetSessionData();
            if(user.getRolename().equals("超级管理员")){
                return  new JsonResult(0,pages.size(),pages,"查询成功");
            }
            List<SysUser>  list_user= pages.stream().filter(u->u.getCompanyName().equals(user.getCompanyName())).collect(Collectors.toList());
            return  new JsonResult(0,list_user.size(),list_user,"查询成功");
        }
        return new JsonResult(1,"查询失败");
    }

    @PostMapping("/addUser")
    public JsonResult addUser( @RequestBody  SysUser user){
        if(StringUtils.isEmpty(user.getUsername())){
            return new JsonResult(1,"用户的账户名称不能为空");
        }else if(StringUtils.isEmpty(user.getPhone())){
            return new JsonResult(1,"用户的账户手机号码不能为空");
        }else {
            SysUser sysuser = ConstantsData.GetSessionData();
            String salt= UUID.randomUUID().toString();
            user.setSalt(salt);
            user.setPassword(ConstantsData.GetNewPassword(salt));
            user.setCompanyName(sysuser.getCompanyName());
            return userService.addUser(user);
        }
    }

    @PostMapping("/editUser")
    public JsonResult editUser(SysUser user){
        return userService.editUser(user);
    }


    @PostMapping("/delete/{uid}")
    public JsonResult deleteUser(@PathVariable Integer uid){
        return userService.deleteUser(uid);
    }

    @PostMapping("/ChangState/{uid}")
    public JsonResult ChangState(@PathVariable Integer uid){
        return userService.ChangUserState(uid);
    }
    

}
