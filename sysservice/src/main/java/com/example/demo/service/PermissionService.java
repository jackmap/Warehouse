package com.example.demo.service;

import com.example.demo.common.JsonResult;

public interface PermissionService {
    JsonResult findAll();

    JsonResult findRulePage(int page,int size );
}
