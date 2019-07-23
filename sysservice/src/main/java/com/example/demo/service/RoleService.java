package com.example.demo.service;

import com.example.demo.common.JsonResult;

import java.awt.print.Pageable;

public interface RoleService {

    JsonResult findAll();

    JsonResult findPage(int page,int size );
}
