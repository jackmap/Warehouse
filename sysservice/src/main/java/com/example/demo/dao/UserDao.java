package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<SysUser,Integer> {
}
