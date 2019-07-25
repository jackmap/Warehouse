package com.example.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_permission")
@NamedQuery(name = "SysPermission.findAll", query = "SELECT s FROM SysPermission s")
public class SysPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue
	private Integer pid;// 主键.
	private String menuname;// 名称.
	@Column(columnDefinition = "enum('menu','button')")
	private String resourceType;// 资源类型，[menu|button]
	private String url; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
	private String parentId; // 父编号
	private String icon; //图片
	private Boolean available = Boolean.FALSE;
	private String note;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	private Date updateTime;
	private String updateName;


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getIcon() {return icon;}

	public void setIcon(String icon) {this.icon = icon; }

	public String getNote() {return note; }

	public void setNote(String note) {this.note = note; }

}