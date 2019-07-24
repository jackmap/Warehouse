package com.example.sys.service;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysRole;

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
    JsonResult findPage(int page, int limit);
    /**  根据过滤条件查询所有的角色信息
     * @param requestFilter
     * @return
     */
    JsonResult findPageFilter(RequestFilter requestFilter);

}
