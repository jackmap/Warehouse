package com.example.demo.dao;

import com.example.demo.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<SysRole,Integer> {
}
