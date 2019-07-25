package com.example.sys.dao;

import com.example.sys.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<SysRole,Integer> {


    SysRole findByRoleName(String name);
}
