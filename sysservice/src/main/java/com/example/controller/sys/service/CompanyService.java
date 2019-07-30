package com.example.controller.sys.service;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.entity.Company;
import com.example.controller.sys.entity.SysDept;
import com.example.controller.sys.entity.SysPost;

import java.text.ParseException;

public interface CompanyService {

    /**分页查询所有公司
     * @param page
     * @param limit
     * @return
     */
    JsonResult  findCompanyPage(String start,String end,String companyType,String companyName,int page, int limit) throws ParseException;

    /**分页查询所有部门
     * @param page
     * @param limit
     * @return
     */
    JsonResult findDeptPage(String start,String end,String deptName,int page, int limit,String companyName) throws ParseException;


    /**分页查询所有岗位
     * @param page
     * @param limit
     * @return
     */
    JsonResult findPostPage(String start,String end,String postName,int page, int limit,String companyName) throws ParseException;

    /**查询所有的公司
     * @return
     */
    JsonResult findAllCompany();

    /**查询公司所有的部门
     * @return
     */
    JsonResult findAllDeptByCompanyName(String companyName);

    /**查询公司所有的岗位
     * @return
     */
    JsonResult findAllPostByCompanyName(String companyName);


    /**
     * 添加公司
     * @param company
     * @return
     */
    JsonResult addCompany(Company company);
    /**
     * 添加部门
     * @param dept
     * @return
     */
    JsonResult addDept(SysDept dept,String companyName);
    /**
     * 添加岗位
     * @param post
     * @return
     */
    JsonResult addPost(SysPost post,String companyName);


    /** 修改已有的公司
     * @param user
     * @return
     */
    JsonResult editCompany(Company user);
    /** 修改已有的部门
     * @param user
     * @return
     */
    JsonResult editSysDept(SysDept user);
    /** 修改已有的岗位
     * @param user
     * @return
     */
    JsonResult editSysPost(SysPost user);


    /** 删除已有的公司
     * @param companyid
     * @return
     */
    JsonResult deleteCompany(Integer companyid);
    /** 删除已有的部门
     * @param deptid
     * @return
     */
    JsonResult deleteSysDept(Integer deptid);
    /** 删除已有的岗位
     * @param postid
     * @return
     */
    JsonResult deleteSysPost(Integer  postid);

    JsonResult ChangCompanyState(Integer companyid);

}
