package com.example.sys.controller;

import com.example.sys.common.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysController {

    @GetMapping("/login")
    public  String Login(){
        return "login";
    }

    /**  登陆验证
     * @param username 账户
     * @param password  密码
     * @param rememberme  记住我
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult Login(String username, String password, String rememberme){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return new  JsonResult(0,"登陆成功！");
        }catch (NullPointerException nu) {
            token.clear();
            return new  JsonResult(1,"用户不存在，请查询后登陆！");
        }catch (LockedAccountException lae) {
            token.clear();
            return new  JsonResult(1,"用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            token.clear();
            return new  JsonResult(1,"用户或密码不正确");
        }
    }

    @GetMapping("/logout")
    public  String Logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }

    @RequestMapping("/")
    public  String Index(){
        return "index";
    }


    @RequestMapping("/index")
    public  String Index1(){
        return "index";
    }

    @RequestMapping("/log")
    public  String Log(){
        return "log";
    }


    @RequestMapping("/welcome")
    public  String Welcome(){
        return "welcome";
    }

    @RequestMapping("/sys/user")
    public  String User(){
       return "/sys/user/user";
    }

    @RequestMapping("/sys/user/add")
    public  String AddUser(){
        return "/sys/user/add";
    }

    @RequestMapping("/sys/user/edit")
    public  String EditUser(){
        return "/sys/user/edit";
    }

    @RequestMapping("/sys/rule")
    public  String Rule(){
        return "/sys/rule/rule";
    }

    @RequestMapping("/sys/role")
    public  String Role(){
        return "/sys/role/role";
    }

    @RequestMapping("/sys/role/add")
    public  String AddRole(){
        return "/sys/role/add";
    }

    @RequestMapping("/sys/role/edit")
    public  String EditRole(){
        return "/sys/role/edit";
    }


    @RequestMapping("/sys/cate")
    public  String Cate(){
        return "/sys/rule/cate";
    }
}
