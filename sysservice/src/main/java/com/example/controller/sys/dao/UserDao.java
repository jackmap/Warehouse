package com.example.controller.sys.dao;

import com.example.controller.sys.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<SysUser,Integer> {

    SysUser findAllByUsername(String username);

    SysUser findByUsername(String username);

    SysUser findByUsernameAndCompany(String username,String  companyName);
}
