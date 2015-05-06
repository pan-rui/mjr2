package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** 角色表**/
@Table(name="b_role")
public class BRole{
	/****/
	@Id
	private Long id;

	/**角色名**/
	@Column
	private String roleName;

	/**描述**/
	@Column
	private String description;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setRoleName(String roleName) {
		this.roleName=roleName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setDescription(String description) {
		this.description=description;
	}

	public String getDescription() {
		return this.description;
	}

}