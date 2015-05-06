package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 借款附件**/
@Table(name="t_borrow_attr")
public class TBorrowAttr{
	/****/
	@Id
	private Long id;

	/**借款Id**/
	@Column
	private Long borrowId;

	/**附件名称**/
	@Column
	private String attrName;

	/**附件路径**/
	@Column
	private String attrPath;

	/**创建时间**/
	@Column
	private Date createTime;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId=borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public void setAttrName(String attrName) {
		this.attrName=attrName;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrPath(String attrPath) {
		this.attrPath=attrPath;
	}

	public String getAttrPath() {
		return this.attrPath;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}