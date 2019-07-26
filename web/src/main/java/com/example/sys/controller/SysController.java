package com.example.sys.controller;

import com.example.sys.common.JsonResult;
import com.example.sys.dao.PermissionDao;
import com.example.sys.entity.SysPermission;
import com.example.sys.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.acl.Permission;
import java.util.Optional;

/**
 * 系统页面控制器
 * @author mp
 */
@Controller
public class SysController {
    /**登陆页面
     * @return
     */
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

    /**登出页面
     * @return
     */
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

    /**日记记录页面
     * @return
     */
    @RequestMapping("/log")
    public  String Log(){
        return "log";
    }


    /**欢迎页面
     * @return
     */
    @RequestMapping("/welcome")
    public  String Welcome(){
        return "welcome";
    }

    /**管理员页面
     * @return
     */
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

    /** 权限页面
     * @return
     */
    @RequestMapping("/sys/rule")
    public  String Rule(){
        return "/sys/rule/rule";
    }

    @RequestMapping("/sys/rule/add")
    public  String AddRule(){
        return "/sys/rule/add";
    }

    @GetMapping("/sys/rule/edit")
    public  String EditRule(){
        return "/sys/rule/edit";
    }

    /**角色页面
     * @return
     */
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


    /**角色权限页面
     * @return
     */
    @RequestMapping("/sys/cate")
    public  String Cate(){
        return "/sys/rule/cate";
    }

}
