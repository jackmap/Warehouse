package com.example.sys.dao;

import com.example.sys.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<SysPermission,Integer> {


    SysPermission findByMenuname(String menuname);
}
