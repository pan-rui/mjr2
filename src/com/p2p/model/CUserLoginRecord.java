package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 用户登录记录表**/
@Table(name="c_user_login_record")
public class CUserLoginRecord{
	/****/
	@Id
	private Long id;

	/**用户ID**/
	@Column
	private Long userId;

	/**登录时间**/
	@Column
	private Date createTime;

	/**登录IP**/
	@Column
	private String loginIp;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp=loginIp;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

}