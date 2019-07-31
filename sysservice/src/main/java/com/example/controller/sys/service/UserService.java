package com.example.controller.sys.service;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.common.RequestFilter;
import com.example.controller.sys.entity.SysUser;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    /**查询所有的用户
     * @return
     */
    List<SysUser> findAll();

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
    List<SysUser> findUserPage(String start ,String end,String username,int page, int limit) throws ParseException;

    SysUser findUserByName(String username);

    JsonResult ChangUserState(Integer uid);

    JsonResult deleteUser(Integer uid);
}
