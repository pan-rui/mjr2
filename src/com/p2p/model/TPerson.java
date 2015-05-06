package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 实名认证**/
@Table(name="t_person")
public class TPerson{
	/****/
	@Id
	private Long id;

	/**用户ID**/
	@Column
	private Long userId;

	/**真实姓名**/
	@Column
	private String realName;

	/**身份证号**/
	@Column
	private String cardNo;

	/**性别（1，男，2，女）**/
	@Column
	private Integer sex;

	/**创建时间**/
	@Column
	private Date createTime;

	/**出生日期**/
	@Column
	private Date birthDate;

	/**是否可用（0，未认证、1，认证成功，2、认证失败）**/
	@Column
	private Integer isAuth ;
	
	/**认证失败原因**/
	@Column
	private String remark;
	
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

	public void setRealName(String realName) {
		this.realName=realName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setCardNo(String cardNo) {
		this.cardNo=cardNo;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setSex(Integer sex) {
		this.sex=sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate=birthDate;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}