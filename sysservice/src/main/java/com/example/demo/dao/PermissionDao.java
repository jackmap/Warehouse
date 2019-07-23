package com.example.demo.dao;

import com.example.demo.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<SysPermission,Integer> {
}
