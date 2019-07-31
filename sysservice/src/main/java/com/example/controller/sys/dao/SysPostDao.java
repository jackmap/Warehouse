package com.example.controller.sys.dao;

import com.example.controller.sys.entity.SysDept;
import com.example.controller.sys.entity.SysPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysPostDao extends JpaRepository<SysPost,Integer> {

    SysPost findByPostName(String postName);

    List<SysDept> findAllByCompanyName(String companyName);

    List<SysPost> findByDeptName(String deptName);
}
