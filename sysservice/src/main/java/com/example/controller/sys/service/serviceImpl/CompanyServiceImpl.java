package com.example.controller.sys.service.serviceImpl;

import com.example.controller.sys.common.JsonResult;
import com.example.controller.sys.dao.CompanyDao;
import com.example.controller.sys.dao.SysDeptDao;
import com.example.controller.sys.dao.SysPostDao;
import com.example.controller.sys.entity.Company;
import com.example.controller.sys.entity.SysDept;
import com.example.controller.sys.entity.SysPost;
import com.example.controller.sys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;
    @Autowired
    SysDeptDao sysDeptDao;
    @Autowired
    SysPostDao sysPostDao;

    SimpleDateFormat sdf=new SimpleDateFormat("YYYY-HH-DD  HH:mm:ss");

    @Override
    public JsonResult findAllCompany() {
        List<Company> list= companyDao.findAll();
        if (list!=null){
            return new JsonResult(0,list,"查询成功");
        }else {
            return JsonResult.Error();
        }
    }

    @Override
    public JsonResult findAllDeptByCompanyName(String companyName) {
        List<SysDept> list= sysDeptDao.findAllByCompanyName(companyName);
        if (StringUtils.isEmpty(list)){
            return new JsonResult(0,list,"查询成功");
        }else {
            return JsonResult.Error();
        }
    }

    @Override
    public JsonResult findAllPostByCompanyName(String companyName) {
        List<SysDept> list= sysPostDao.findAllByCompanyName(companyName);
        if (StringUtils.isEmpty(list)){
            return new JsonResult(0,list,"查询成功");
        }else {
            return JsonResult.Error();
        }
    }

    @Override
    public JsonResult addCompany(Company company) {
        Company company1=companyDao.findByCompanyName(company.getCompanyName());
        if(StringUtils.isEmpty(company1)){
            company.setCreateTime(new Date());
            companyDao.save(company);
            return  new JsonResult(0,"公司信息保存成功！");
        }else{
            return  new JsonResult(1,"公司名称已存在，请更换公司名称！");
        }
    }

    @Override
    public JsonResult addDept(SysDept dept,String companyName) {
        Company company1=companyDao.findByCompanyName(companyName);
        SysDept dept1=sysDeptDao.findByDeptName(dept.getDeptName());
        if(StringUtils.isEmpty(company1)||!StringUtils.isEmpty(company1)){
            dept.setCompanyName(companyName);
            dept.setCreateTime(new Date());
            sysDeptDao.save(dept);
            return  new JsonResult(0,"部门信息保存成功！");
        }else{
            return  new JsonResult(1,"公司名称不存在或者部门名称已存在，请检查！");
        }
    }

    @Override
    public JsonResult addPost(SysPost post,String companyName) {
        SysDept dept=sysDeptDao.findByDeptNameAndCompanyName(post.getDeptName(),companyName);
        SysPost syspost=sysPostDao.findByPostName(post.getPostName());
        if(!StringUtils.isEmpty(dept)|| StringUtils.isEmpty(syspost)){
            post.setCompanyName(companyName);
            post.setCreateTime(new Date());
            sysPostDao.save(post);
            return  new JsonResult(0,"岗位信息保存成功！");
        }else{
            return  new JsonResult(1,"部门名称不存在或者岗位名称已存在，请检查！");
        }
    }


    @Override
    public JsonResult editCompany(Company company) {
        Company sys_company=companyDao.findById(company.getCompanyid()).get();
        if(StringUtils.isEmpty(sys_company)){
            company.setCompanyName(company.getCompanyName());
            company.setCompanyType(company.getCompanyType());
            company.setAvailable(company.getAvailable());
            company.setCreateTime(new Date());
            companyDao.save(company);
            return  new JsonResult(0,"修改公司信息成功！");
        }else{
            return  new JsonResult(0,"公司信息不存在，请查询你的公司信息是否存在！");
        }
    }

    @Override
    public JsonResult editSysDept(SysDept user) {
        return null;
    }

    @Override
    public JsonResult editSysPost(SysPost user) {
        return null;
    }



    @Override
    public JsonResult deleteCompany(Integer companyid) {
        return null;
    }

    @Override
    public JsonResult deleteSysDept(Integer deptid) {
        return null;
    }

    @Override
    public JsonResult deleteSysPost(Integer postid) {
        return null;
    }


    @Override
    public JsonResult findCompanyPage(String start,String  end,String companyType,String companyName,int page, int limit) throws ParseException {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"companyid");
        Page<Company> pages = companyDao.findAll(pageable);
        if (pages.getContent()!=null){
            List<Company>  list;
            if(!StringUtils.isEmpty(companyName)){
                list = pages.getContent().stream().filter(r->r.getCompanyName().equals(companyName)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(start)){
                Date starttime=sdf.parse(start);
                list = pages.getContent().stream().filter(r->r.getCreateTime().after(starttime)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(end)){
                Date endtime=sdf.parse(end);
                list = pages.getContent().stream().filter(r->r.getCreateTime().before(endtime)).collect(Collectors.toList());
            }else{
                int count= (int) pages.getTotalElements();
                return new JsonResult(0,count,pages.getContent(),"查询成功");
            }
            int count= (int) list.size();
            return new JsonResult(0,count,list,"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult findDeptPage(String start,String end,String deptName,int page, int limit, String companyid) throws ParseException {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"deptid");
        Page<SysDept> pages = sysDeptDao.findAll(pageable);
        if (pages.getContent()!=null){
            List<SysDept> sys_list=pages.getContent().stream().filter(d->d.getCompanyName().equals(companyid)).collect(Collectors.toList());
            if(!StringUtils.isEmpty(deptName)){
                sys_list = pages.getContent().stream().filter(r->r.getDeptName().equals(deptName)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(start)){
                Date starttime=sdf.parse(start);
                sys_list = pages.getContent().stream().filter(r->r.getCreateTime().after(starttime)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(end)){
                Date endtime=sdf.parse(end);
                sys_list = pages.getContent().stream().filter(r->r.getCreateTime().before(endtime)).collect(Collectors.toList());
            }else{
                int count= (int) pages.getTotalElements();
                return new JsonResult(0,count,pages.getContent(),"查询成功");
            }
            int count= (int) sys_list.size();
            return new JsonResult(0,count,sys_list,"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult findPostPage(String start,String end,String postName,int page, int limit, String companyid) throws ParseException {
        Pageable pageable = (Pageable) new PageRequest(page-1,limit, Sort.Direction.ASC,"postid");
        Page<SysPost> pages = sysPostDao.findAll(pageable);
        if (pages.getContent()!=null){
            List<SysPost>  list=pages.getContent().stream().filter(p->p.getCompanyName().equals(companyid)).collect(Collectors.toList());
            if(!StringUtils.isEmpty(postName)){
                list = pages.getContent().stream().filter(r->r.getPostName().equals(postName)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(start)){
                Date starttime=sdf.parse(start);
                list = pages.getContent().stream().filter(r->r.getCreateTime().after(starttime)).collect(Collectors.toList());
            }else  if(!StringUtils.isEmpty(end)){
                Date endtime=sdf.parse(end);
                list = pages.getContent().stream().filter(r->r.getCreateTime().before(endtime)).collect(Collectors.toList());
            }else{
                int count= (int) pages.getTotalElements();
                return new JsonResult(0,count,pages.getContent(),"查询成功");
            }
            int count= (int) list.size();
            return new JsonResult(0,count,list,"查询成功");
        }else {
            return new JsonResult(1,"查询失败");
        }
    }

    @Override
    public JsonResult ChangCompanyState(Integer uid) {
        return null;
    }
}
