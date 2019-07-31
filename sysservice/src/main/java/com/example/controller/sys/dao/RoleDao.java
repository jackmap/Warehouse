package com.example.controller.sys.dao;

import com.example.controller.sys.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<SysRole,Integer> {


    SysRole findByRoleName(String name);
}