package com.example.sys.dao;

import com.example.sys.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<SysUser,Integer> {

    SysUser findAllByUsername(String username);

    SysUser findByUsername(String username);

    SysUser findByUsernameAndCompany(String username,String  companyName);
}
