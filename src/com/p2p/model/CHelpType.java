package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 帮助类型**/
@Table(name="c_help_type")
public class CHelpType{
	/****/
	@Id
	private Long id;

	/**名称类型**/
	@Column
	private String helpTypeName;

	/**创建时间**/
	@Column
	private Date createTime;

	/**排序**/
	@Column
	private Integer orderNo;

	/**所属类型**/
	@Column
	private Integer helpTypeId;
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setHelpTypeName(String helpTypeName) {
		this.helpTypeName=helpTypeName;
	}

	public String getHelpTypeName() {
		return this.helpTypeName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo=orderNo;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public Integer getHelpTypeId() {
		return helpTypeId;
	}

	public void setHelpTypeId(Integer helpTypeId) {
		this.helpTypeId = helpTypeId;
	}
	
}