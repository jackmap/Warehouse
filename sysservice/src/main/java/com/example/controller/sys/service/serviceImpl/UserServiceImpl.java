package com.example.controller.sys.service.serviceImpl;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.dao.UserDao;
import com.example.controller.sys.entity.Company;
import com.example.controller.sys.entity.SysUser;
import com.example.controller.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    SimpleDateFormat sdf=new SimpleDateFormat("YYYY-HH-DD  HH:mm:ss");

    @Override
    public List<SysUser> findAll() {
       return userDao.findAll();
    }





    @Override
    public SysUser findUserByName(String username) {
        return userDao.findAllByUsername(username);
    }

    @Override
    public List<SysUser> findUserPage(String start ,String end,String username,int page, int limit) throws ParseException {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"uid");
        Page<SysUser> pages = userDao.findAll(pageable);
        if (pages.getContent()!=null) {
            List<SysUser> list;
            if (!StringUtils.isEmpty(username)) {
                list = pages.getContent().stream().filter(r -> r.getUsername().equals(username)).collect(Collectors.toList());
            } else if (!StringUtils.isEmpty(start)) {
                Date starttime = sdf.parse(start);
                list = pages.getContent().stream().filter(r -> r.getCreateTime().after(starttime)).collect(Collectors.toList());
            } else if (!StringUtils.isEmpty(end)) {
                Date endtime = sdf.parse(end);
                list = pages.getContent().stream().filter(r -> r.getCreateTime().before(endtime)).collect(Collectors.toList());
            }
            return pages.getContent();
        }
        return null;
    }

    @Override
    public JsonResult ChangUserState(Integer uid) {
        SysUser user = userDao.findById(uid).get();
        if(user.getState()==true){
            user.setState(false);
            userDao.save(user);
            return new JsonResult(0,"停用 用户"+user.getUsername()+"成功！");
        }else {
            user.setState(true);
            userDao.save(user);
            return new JsonResult(0,"启动 用户"+user.getUsername()+"成功！");
        }
    }

    @Override
    public JsonResult deleteUser(Integer uid) {
        SysUser user = userDao.findById(uid).get();
        if(user.getUsername().equals("admin")&&user.getCompanyName().equals("河南唐洛有限公司")){
            return new JsonResult(0,"该用户为超级管理员不能删除！");
        }else {
            userDao.delete(user);
            return new JsonResult(0,"删除 用户："+user.getUsername()+" 成功！");
        }
    }

    @Override
    public JsonResult addUser(SysUser user) {
        SysUser sysUser=userDao.findByUsernameAndCompanyName(user.getUsername(),user.getCompanyName());
        if(StringUtils.isEmpty(sysUser)){
            user.setRolename("游客");
            userDao.save(user);
            return  new JsonResult(0,"用戶信息保存成功！");
        }else{
            return  new JsonResult(1,"用戶名称已存在，请更换用戶名称！");
        }
    }

    @Override
    public JsonResult editUser(SysUser user) {
        SysUser sysUser=userDao.findById(user.getUid()).get();
        if(sysUser!=null){
            sysUser.setAddress(user.getAddress());
            sysUser.setNickname(user.getNickname());
            return  new JsonResult(0,"修改用戶信息成功！");
        }else{
            return  new JsonResult(1,"用戶不存在，请查询你的用戶信息是否存在！");
        }
    }
}
