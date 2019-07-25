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

import java.util.Date;
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
        SysPermission sysPermission=permissionDao.findByMenuname(permission.getMenuname());
        if(sysPermission==null){
            permission.setCreateTime(new Date());
            permissionDao.save(permission);
            return  new JsonResult(0,"保存权限成功！");
        }else{
            return  new JsonResult(1,"权限名称已存在，请更换权限名称！");
        }
    }

    @Override
    public JsonResult editPermission(SysPermission permission) {
        SysPermission sysPermission=permissionDao.findById(permission.getPid()).get();
        if(sysPermission!=null){
            sysPermission.setMenuname(permission.getMenuname());
            sysPermission.setParentName(permission.getParentName());
            sysPermission.setIcon(permission.getIcon());
            sysPermission.setUrl(permission.getUrl());
            sysPermission.setIcon(permission.getIcon());
            sysPermission.setNote(permission.getNote());
            permissionDao.save(sysPermission);
            return  new JsonResult(0,"修改权限成功！");
        }else{
            return  new JsonResult(1,"请查询你的权限信息是否存在！");
        }
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
