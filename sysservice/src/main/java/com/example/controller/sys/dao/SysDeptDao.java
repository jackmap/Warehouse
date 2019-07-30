package com.example.controller.sys.dao;

import com.example.controller.sys.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysDeptDao extends JpaRepository<SysDept,Integer> {
    SysDept findByDeptName(String deptName);

    SysDept findByDeptNameAndCompanyName(String deptName,String companyName);
    List<SysDept> findAllByCompanyName(String companyName);
}
