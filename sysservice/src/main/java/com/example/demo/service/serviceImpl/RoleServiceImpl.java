package com.example.demo.service.serviceImpl;

import com.example.demo.common.JsonResult;
import com.example.demo.dao.RoleDao;
import com.example.demo.entity.SysRole;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao  roleDao;
    @Override
    public JsonResult findAll() {
       List<SysRole> list= roleDao.findAll();
       if (list!=null){
           return new JsonResult(0,list,"查询成功");
       }else {
           return new JsonResult(1,"查询失败");
       }
    }

    @Override
    public JsonResult findPage(int page, int size) {
        Pageable pageable = (Pageable) new PageRequest(page,size, Sort.Direction.ASC,"id");
        Page<SysRole> pages = roleDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

}
