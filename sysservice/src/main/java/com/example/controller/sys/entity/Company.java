package com.example.controller.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "company")
@NamedQuery(name = "Company.findAll", query = "SELECT s FROM Company s")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue
	private Integer companyid;// 主键.
	@Column(nullable = false,unique = true)
	private String companyName;// 公司名称.
	@Column(columnDefinition = "enum('创业型公司','上市公司','集团公司')",nullable = false)
	private String companyType;// 公司类型，[menu|button]
	private String companyAdmin;//公司联系人
	private String companyPhone;//公司联系电话
	private String companyAddress;//公司联系地址
	private String icon;
	private Boolean available = Boolean.FALSE;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	private Date updateTime;
	private String updateName;
	private String note;

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(String companyAdmin) {
		this.companyAdmin = companyAdmin;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}