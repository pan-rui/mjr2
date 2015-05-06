package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 合作伙伴或链接**/
@Table(name="c_links")
public class CLinks{
	/****/
	@Id
	private Long id;

	/**合作伙伴名称**/
	@Column
	private String linkName;

	/**logo路径**/
	@Column
	private String imagePath;

	/**合作伙伴简称**/
	@Column
	private String shortName;

	/**官网地址**/
	@Column
	private String linkUrl;

	/**创建时间**/
	@Column
	private Date createTime;

	/**公司概要**/
	@Column
	private String description;

	/**是否删除（0为未删除，1为已删除）**/
	@Column
	private Integer isDelete;

	/**排序**/
	@Column
	private Integer orderNo;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setLinkName(String linkName) {
		this.linkName=linkName;
	}

	public String getLinkName() {
		return this.linkName;
	}

	public void setImagePath(String imagePath) {
		this.imagePath=imagePath;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setShortName(String shortName) {
		this.shortName=shortName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl=linkUrl;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setDescription(String description) {
		this.description=description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo=orderNo;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

}