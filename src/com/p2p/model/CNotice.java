package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 公告**/
@Table(name="c_notice")
public class CNotice{
	/****/
	@Id
	private Long id;

	/**公告标题**/
	@Column
	private String noticeTitle;

	/**创建时间**/
	@Column
	private Date createTime;

	/**内容**/
	@Column
	private String  noticeContent;

	/**发布ID**/
	@Column
	private Long adminId;

	/**排序**/
	@Column
	private Integer orderNo;

	/**是否删除（0为未删除，1为已删除）**/
	@Column
	private Integer isDelete;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle=noticeTitle;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setNoticeContent(String  noticeContent) {
		this.noticeContent=noticeContent;
	}

	public String  getNoticeContent() {
		return this.noticeContent;
	}

	public void setAdminId(Long adminId) {
		this.adminId=adminId;
	}

	public Long getAdminId() {
		return this.adminId;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo=orderNo;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

}