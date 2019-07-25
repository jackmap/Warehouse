package com.example.sys.service.serviceImpl;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.dao.UserDao;
import com.example.sys.entity.SysUser;
import com.example.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public JsonResult findAll() {
       List<SysUser> list= userDao.findAll();
       if (list!=null){
           return new JsonResult(0,list,"查询成功");
       }else {
            return new JsonResult(1,"查询失败");
       }

    }

    @Override
    public JsonResult addUser(SysUser user) {
        SysUser sysUser=userDao.findByUsernameAndCompany(user.getUsername(),user.getCompany());
        if(sysUser==null){
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
            sysUser.setUpdateTime(new Date());
            return  new JsonResult(0,"修改用戶信息成功！");
        }else{
            return  new JsonResult(1,"用戶不存在，请查询你的用戶信息是否存在！");
        }
    }

    @Override
    public JsonResult findUserPage(RequestFilter requestFilter) {
        Pageable pageable = (Pageable) new PageRequest(requestFilter.getPage()-1,requestFilter.getLimit(), Sort.Direction.ASC,"uid");
        Page<SysUser>  pages=userDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public SysUser findUserByName(String username) {
        return userDao.findAllByUsername(username);
    }

    @Override
    public JsonResult findUserPage(int page, int limit) {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"uid");
        Page<SysUser>  pages=userDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }
}
