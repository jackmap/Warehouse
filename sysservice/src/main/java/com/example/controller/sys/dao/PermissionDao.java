package com.example.controller.sys.dao;

import com.example.controller.sys.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionDao extends JpaRepository<SysPermission,Integer> {

    SysPermission findByMenuname(String menuname);

    List<SysPermission> findByResourceType(String menu);

    SysPermission findByTitle(String parentName);
}
