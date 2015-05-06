package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/** 用户**/
@Table(name="c_user")
public class CUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2766056119102044614L;

	/****/
	@Id
	private Long id;

	/**用户名**/
	@Column
	private String userName;

	/**邮箱地址**/
	@Column
	private String email;

	/**用户密码**/
	@Column
	private String pwd;

	/**手机号码**/
	@Column
	private String cellPhone;

	/**创建时间**/
	@Column
	private Date createTime;

	/**是否禁用（0不禁用，1禁用）**/
	@Column
	private Integer isEnable;

	/**注册IP**/
	@Column
	private String regIp;

	/**最后登录Ip**/
	@Column
	private String lastLoginIp;

	/**最后登录时间**/
	@Column
	private Date lastLoginTime;
	
	/**用户来源 0-普通客户，1-cpa**/
	@Column
	private Integer source;
	
	
	private String referee;
	
	
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUserName(String userName) {
		this.userName=userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone=cellPhone;
	}

	public String getCellPhone() {
		return this.cellPhone;
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

	public void setRegIp(String regIp) {
		this.regIp=regIp;
	}

	public String getRegIp() {
		return this.regIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp=lastLoginIp;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime=lastLoginTime;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}
	
	

}