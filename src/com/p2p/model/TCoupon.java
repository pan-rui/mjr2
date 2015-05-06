package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/** 礼券表**/
@Table(name="t_coupon")
public class TCoupon{
	/****/
	@Id
	private Long id;
	
	/**用户id**/
	@Column
	private Long userId;
	
	/**礼券名称**/
	@Column
	private String couponName;

	/**礼券金额**/
	@Column
	private BigDecimal couponAmount;

	/**创建时间**/
	@Column
	private Date createTime;

	/**备注**/
	@Column
	private String couponRemarks;

	/**礼券类型（1.红包 2:推荐奖励）**/
	@Column
	private Integer couponType;

	/**礼券状态（1，未领取，2，未使用，3，已使用，4，未领取过期，5未使用过期）**/
	@Column
	private Integer couponStatus;
	
	/**过期时间**/
	@Column
	private Date expirationDate;
	/**
	 * 用户电话号码
	 */
	private String cellPhone;
	/**
	 * 用户名
	 */
	private String userName;
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCouponName(String couponName) {
		this.couponName=couponName;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount=couponAmount;
	}

	public BigDecimal getCouponAmount() {
		return this.couponAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCouponRemarks(String couponRemarks) {
		this.couponRemarks=couponRemarks;
	}

	public String getCouponRemarks() {
		return this.couponRemarks;
	}

	public void setCouponType(Integer couponType) {
		this.couponType=couponType;
	}

	public Integer getCouponType() {
		return this.couponType;
	}

	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus=couponStatus;
	}

	public Integer getCouponStatus() {
		return this.couponStatus;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}