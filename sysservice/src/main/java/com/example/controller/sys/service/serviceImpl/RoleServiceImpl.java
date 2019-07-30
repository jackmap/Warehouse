package com.example.controller.sys.service.serviceImpl;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.dao.RoleDao;
import com.example.controller.sys.entity.SysRole;
import com.example.controller.sys.service.RoleService;
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
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao  roleDao;

    SimpleDateFormat  sdf=new SimpleDateFormat("YYYY-HH-DD  HH:mm:ss");

    @Override
    public JsonResult findAllRole() {
       List<SysRole> list= roleDao.findAll();
       if (list!=null){
           return new JsonResult(0,list,"查询成功");
       }else {
           return new JsonResult(1,"查询失败");
       }
    }


    @Override
    public JsonResult addRole(SysRole role) {
        SysRole sysrole=roleDao.findByRoleName(role.getRoleName());
        if(StringUtils.isEmpty(sysrole)){
            role.setCreateTime(new Date());
            roleDao.save(role);
            return  new JsonResult(0,"角色保存成功！");
        }else{
            return  new JsonResult(1,"角色名称已存在，请更换角色名称！");
        }
    }

    @Override
    public JsonResult editRole(SysRole role) {
        SysRole sysrole=roleDao.findById(role.getRid()).get();
        if(sysrole!=null){
            sysrole.setRoleName(role.getRoleName());
            sysrole.setAvailable(role.getAvailable());
            sysrole.setDescription(role.getDescription());
            sysrole.setUpdateTime(new Date());
            roleDao.save(sysrole);
            return  new JsonResult(0,"修改角色成功！");
        }else{
            return  new JsonResult(0,"角色不存在，请查询你的角色信息是否存在！");
        }
    }

    @Override
    public JsonResult findPage(String start, String end, String username, int page, int limit) throws ParseException {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"rid");
        Page<SysRole> pages = roleDao.findAll(pageable);
        if (pages.getContent()!=null){
            List<SysRole>  list;
            if(!StringUtils.isEmpty(username)){
                list = pages.getContent().stream().filter(r->r.getRoleName().equals(username)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(start)){
                Date starttime=sdf.parse(start);
                list = pages.getContent().stream().filter(r->r.getCreateTime().after(starttime)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(end)){
                Date endtime=sdf.parse(end);
                list = pages.getContent().stream().filter(r->r.getCreateTime().before(endtime)).collect(Collectors.toList());
            }else{
                int count= (int) pages.getTotalElements();
                return new JsonResult(0,count,pages.getContent(),"查询成功");
            }
            int count= (int) list.size();
            return new JsonResult(0,count,list,"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult ChangRoleState(Integer rid) {
       SysRole role = roleDao.findById(rid).get();
       if(role.getAvailable()==true){
           role.setAvailable(false);
       }else {
           role.setAvailable(true);
       }
       roleDao.save(role);
       return new JsonResult(0,"修改角色"+role.getRoleName()+"状态成功！");
    }

    @Override
    public JsonResult deleteRole(Integer rid) {
        SysRole role = roleDao.findById(rid).get();
        //省略
        roleDao.delete(role);
        return new JsonResult(0,"删除 角色"+role.getRoleName()+" 成功！");
    }
}
