package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 媒体报道**/
@Table(name="c_media_report")
public class CMediaReport{
	/****/
	@Id
	private Long id;

	/**标题**/
	@Column
	private String title;

	/**创建时间**/
	@Column
	private Date createTime;

	/**内容**/
	@Column
	private String  content;

	/**发布ID**/
	@Column
	private Long adminId;

	/**排序**/
	@Column
	private Integer orderNo;

	/**来源名称**/
	@Column
	private String src;
	
	/**关键字**/
	@Column
	private String keywords;
	
	/**来源URL**/
	@Column
	private String srcUrl;

	/**图片路径**/
	@Column
	private String srcImgPath;

	/**是否删除（0为未删除，1为已删除）**/
	@Column
	private Integer isDelete;

	/**类型（1，行业资讯，2，媒体报道）**/
	@Column
	private Integer reportType;
	
	/**浏览量**/
	@Column
	private Integer viewCount;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title=title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setContent(String  content) {
		this.content=content;
	}

	public String  getContent() {
		return this.content;
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

	public void setSrc(String src) {
		this.src=src;
	}

	public String getSrc() {
		return this.src;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl=srcUrl;
	}

	public String getSrcUrl() {
		return this.srcUrl;
	}

	public void setSrcImgPath(String srcImgPath) {
		this.srcImgPath=srcImgPath;
	}

	public String getSrcImgPath() {
		return this.srcImgPath;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setReportType(Integer reportType) {
		this.reportType=reportType;
	}

	public Integer getReportType() {
		return this.reportType;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	
}