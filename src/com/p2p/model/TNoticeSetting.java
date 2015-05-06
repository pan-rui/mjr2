package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 通知类型设置**/
@Table(name="t_notice_setting")
public class TNoticeSetting{
	/****/
	@Id
	private Long id;

	/**是否站内信通知（0否，1是）**/
	@Column
	private Integer isMessage;

	/**是否短信通知（0否，1是）**/
	@Column
	private Integer isSms;

	/**是否邮件通知（0否，1是）**/
	@Column
	private Integer isEmail;

	/**用户ID**/
	@Column
	private Long userId;

	/**最后修改时间**/
	@Column
	private Date lastUpdateTime;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage=isMessage;
	}

	public Integer getIsMessage() {
		return this.isMessage;
	}

	public void setIsSms(Integer isSms) {
		this.isSms=isSms;
	}

	public Integer getIsSms() {
		return this.isSms;
	}

	public void setIsEmail(Integer isEmail) {
		this.isEmail=isEmail;
	}

	public Integer getIsEmail() {
		return this.isEmail;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime=lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

}