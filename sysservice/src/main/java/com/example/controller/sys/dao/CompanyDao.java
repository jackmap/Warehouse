package com.example.controller.sys.dao;

import com.example.controller.sys.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company,Integer> {

    Company findByCompanyName(String comapnyName);
}
