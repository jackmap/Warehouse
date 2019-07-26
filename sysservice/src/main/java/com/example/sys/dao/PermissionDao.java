package com.example.sys.dao;

import com.example.sys.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionDao extends JpaRepository<SysPermission,Integer> {

    SysPermission findByMenuname(String menuname);

    List<SysPermission> findByResourceType(String menu);

    SysPermission findByParentName(String parentName);
}
