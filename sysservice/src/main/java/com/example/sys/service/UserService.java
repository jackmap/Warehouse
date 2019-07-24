package com.example.sys.service;

import com.example.sys.common.JsonResult;
import com.example.sys.common.RequestFilter;
import com.example.sys.entity.SysUser;

public interface UserService {
    /**查询所有的用户
     * @return
     */
    JsonResult findAll();

    /**
     * 添加加用户
     * @param user
     * @return
     */
    JsonResult addUser(SysUser user);

    /** 修改已有的用户信息
     * @param user
     * @return
     */
    JsonResult editUser(SysUser user);

    /**分页查询所有用户
     * @param page
     * @param limit
     * @return
     */
    JsonResult findUserPage(int page, int limit);

    /**  根据过滤条件查询所有的用户信息
     * @param requestFilter
     * @return
     */
    JsonResult findUserPage(RequestFilter requestFilter);


    SysUser findUserByName(String username);
}
