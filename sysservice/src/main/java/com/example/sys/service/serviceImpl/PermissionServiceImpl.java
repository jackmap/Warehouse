package com.example.sys.service.serviceImpl;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.dao.PermissionDao;
import com.example.sys.entity.SysPermission;
import com.example.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao  permissionDao;

    @Override
    public JsonResult findAll() {
       List<SysPermission> list= permissionDao.findAll();
       if (list!=null){
           return new JsonResult(0,list,"查询成功");
       }else {
            return JsonResult.Error();
       }
    }

    @Override
    public JsonResult addPermission(SysPermission permission) {
        return null;
    }

    @Override
    public JsonResult editPermission(SysPermission permission) {
        return null;
    }

    @Override
    public JsonResult findRulePage(int page, int limit) {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"pid");
        Page<SysPermission> pages=permissionDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult findRulePageFilter(RequestFilter requestFilter) {
        Pageable pageable = (Pageable) new PageRequest(requestFilter.getPage()-1,requestFilter.getLimit(), Sort.Direction.ASC,"pid");
        Page<SysPermission> pages=permissionDao.findAll(pageable);
        if (pages.getContent()!=null){
            int count= (int) pages.getTotalElements();
            return new JsonResult(0,count,pages.getContent(),"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

}
