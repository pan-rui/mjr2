package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 角色用户表**/
@Table(name="b_role_admin")
public class BRoleAdmin{
	/****/
	@Id
	private Long id;

	/**角色ID**/
	@Column
	private Long roleId;

	/**管理员ID**/
	@Column
	private Long adminId;

	/**创建时间**/
	@Column
	private Date createTime;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setRoleId(Long roleId) {
		this.roleId=roleId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setAdminId(Long adminId) {
		this.adminId=adminId;
	}

	public Long getAdminId() {
		return this.adminId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}