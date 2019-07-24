package com.example.demo.service.serviceImpl;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public JsonResult editUser(SysUser user) {
        return null;
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
