package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** banner表**/
@Table(name="c_banner")
public class CBanner{
	/****/
	@Id
	private Long id;

	/**banner名称**/
	@Column
	private String bannerName;

	/**图片路径**/
	@Column
	private String imgPath;

	/**路径**/
	@Column
	private String url;

	/**排序**/
	@Column
	private Integer orderNo;

	/**创建时间**/
	@Column
	private Date createTime;

	/**是否删除（0为未删除，1为已删除）**/
	@Column
	private Integer isDelete;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBannerName(String bannerName) {
		this.bannerName=bannerName;
	}

	public String getBannerName() {
		return this.bannerName;
	}

	public void setImgPath(String imgPath) {
		this.imgPath=imgPath;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setUrl(String url) {
		this.url=url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo=orderNo;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

}