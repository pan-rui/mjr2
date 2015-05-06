package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/** 管理员表**/
@Table(name="b_admin")
public class BAdmin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3184281575447551264L;

	/****/
	@Id
	private Long id;

	/**用户名**/
	@Column
	private String adminName;

	/**密码**/
	@Column
	private String pwd;

	/**创建时间**/
	@Column
	private Date createTime;

	/**是否禁用（0为否，1为是）**/
	@Column
	private Integer isEnable;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setAdminName(String adminName) {
		this.adminName=adminName;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable=isEnable;
	}

	public Integer getIsEnable() {
		return this.isEnable;
	}

}