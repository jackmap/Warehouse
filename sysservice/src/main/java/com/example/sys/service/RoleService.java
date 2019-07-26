package com.example.sys.service;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysRole;

import java.text.ParseException;

public interface RoleService {
    /**查询所有的用户
     * @return
     */
    JsonResult findAllRole();
    /**
     * 添加用户
     * @param role
     * @return
     */
    JsonResult addRole(SysRole role);
    /**
     * 修改角色信息
     * @param role
     * @return
     */
    JsonResult editRole(SysRole role);
    /**分页查询所有角色
     * @param page
     * @param limit
     * @return
     */
    JsonResult findPage(String start,String end,String username,int page,int limit) throws ParseException;
    /**  改变角色状态
     * @param rid
     * @return
     */
    JsonResult ChangRoleState(Integer rid);

    JsonResult deleteRole(Integer rid);
}
