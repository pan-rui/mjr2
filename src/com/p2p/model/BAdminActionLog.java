package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 管理员操作记录表**/
@Table(name="b_admin_action_log")
public class BAdminActionLog{
	/****/
	@Id
	private Long id;

	/**管理员ID**/
	@Column
	private Long adminId;

	/**访问路径**/
	@Column
	private String action;

	/**引用路径**/
	@Column
	private String refAction;

	/**IP地址**/
	@Column
	private String ip;

	/**参数**/
	@Column
	private String actionPara;

	/**创建时间**/
	@Column
	private Date createTime;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setAdminId(Long adminId) {
		this.adminId=adminId;
	}

	public Long getAdminId() {
		return this.adminId;
	}

	public void setAction(String action) {
		this.action=action;
	}

	public String getAction() {
		return this.action;
	}

	public void setRefAction(String refAction) {
		this.refAction=refAction;
	}

	public String getRefAction() {
		return this.refAction;
	}

	public void setIp(String ip) {
		this.ip=ip;
	}

	public String getIp() {
		return this.ip;
	}

	public void setActionPara(String actionPara) {
		this.actionPara=actionPara;
	}

	public String getActionPara() {
		return this.actionPara;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}