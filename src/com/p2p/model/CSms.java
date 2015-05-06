package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 短信信息表**/
@Table(name="c_sms")
public class CSms{
	/****/
	@Id
	private Long id;

	/**用户ID**/
	@Column
	private Long userId;

	/**短信内容**/
	@Column
	private String smsContent;

	/**创建时间**/
	@Column
	private Date createTime;

	/**手机号码**/
	@Column
	private String cellPhone;

	/**发送类型**/
	@Column
	private String smsType;

	/**发送状态（1，未发送，2，已发送成功）**/
	@Column
	private Integer sendStatus;
	
	/**用户名**/
	private String userName;
	
	/**真实姓名**/
	private String realName;
	
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

	public void setSmsContent(String smsContent) {
		this.smsContent=smsContent;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone=cellPhone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setSmsType(String smsType) {
		this.smsType=smsType;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus=sendStatus;
	}

	public Integer getSendStatus() {
		return this.sendStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
	
}