package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 角色菜单表**/
@Table(name="b_role_menu")
public class BRoleMenu{
	/****/
	@Id
	private Long id;

	/**角色ID**/
	@Column
	private Long roleId;

	/**菜单ID**/
	@Column
	private Long menuId;

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

	public void setMenuId(Long menuId) {
		this.menuId=menuId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}