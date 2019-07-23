package com.example.demo.service;

import com.example.demo.common.JsonResult;

public interface UserService {
    JsonResult findAll();

    JsonResult findUserPage(int page,int size );
}
