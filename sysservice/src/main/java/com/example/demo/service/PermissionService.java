package com.example.demo.service;

import com.example.demo.common.JsonResult;
import com.example.demo.common.RequestFilter;
import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysUser;

public interface PermissionService {
    /**查询所有的权限信息
     * @return
     */
    JsonResult findAll();

    /**添加权限信息
     * @param permission
     * @return
     */
    JsonResult addPermission(SysPermission permission);
    /**修改权限信息
     * @param permission
     * @return
     */
    JsonResult editPermission(SysPermission permission);

    /** 查询分页信息
     * @param page
     * @param limit
     * @return
     */
    JsonResult findRulePage(int page,int limit);

    /** 根据过滤条件查询分页信息
     * @param requestFilter
     * @return
     */
    JsonResult findRulePageFilter(RequestFilter requestFilter);
}
