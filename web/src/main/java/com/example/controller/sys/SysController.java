package com.example.controller.sys;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.dao.PermissionDao;
import com.example.controller.sys.dao.UserDao;
import com.example.controller.sys.entity.SysPermission;
import com.example.controller.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    UserDao userDao;
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

    @RequestMapping("/sys/user/edit/{uid}")
    public  String EditUser(Model model, @PathVariable Integer uid){
        SysUser user=userDao.findById(uid).get();
        model.addAttribute("user",user);
        return "/sys/user/edit";
    }

    @Autowired
    PermissionDao permissionDao;
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

    @RequestMapping("/sys/rule/edit/{pid}")
    public  String EditRule(Model model, @PathVariable Integer pid){
        SysPermission permission=permissionDao.findById(pid).get();
        model.addAttribute("rule",permission);
        return "/sys/rule/edit";
    }

    /**公司管理页面
     * @return
     */
    @GetMapping("/sys/company")
    public  String Company(){
        return "/sys/company/company";
    }

    @GetMapping("/sys/company/add")
    public  String AddCompany(){
        return "/sys/company/add";
    }

    @GetMapping("/sys/company/edit/{rid}")
    public  String EditCompany(Model model, @PathVariable Integer rid){
        return "/sys/company/edit";
    }


    @GetMapping("/sys/dept")
    public  String Dept(){
        return "/sys/dept/dept";
    }

    @GetMapping("/sys/dept/add")
    public  String AddDept(){
        return "/sys/dept/add";
    }

    @GetMapping("/sys/dept/edit/{rid}")
    public  String EditDept(Model model, @PathVariable Integer rid){
        return "/sys/dept/edit";
    }


    @GetMapping("/sys/post")
    public  String Post(){
        return "/sys/post/post";
    }

    @GetMapping("/sys/post/add")
    public  String AddPost(){
        return "/sys/post/add";
    }

    @GetMapping("/sys/post/edit/{rid}")
    public  String EditPost(Model model, @PathVariable Integer rid){
        return "/sys/post/edit";
    }


    /**岗位权限页面
     * @return
     */
    @RequestMapping("/sys/cate")
    public  String Cate(){
        return "/sys/rule/cate";
    }

}
