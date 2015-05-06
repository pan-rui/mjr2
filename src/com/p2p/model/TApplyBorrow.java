package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

/** 借款申请表**/
@Table(name="t_apply_borrow")
public class TApplyBorrow{
	/****/
	@Id
	private Long id;

	/**借款人姓名**/
	@Column
	private String borrowerName;

	/**借款人手机号**/
	@Column
	private String borrowerCellPhone;

	/**住址省份**/
	@Column
	private String houseProvince;
	
	/**借款用途**/
	@Column
	private String borrowUse;

	/**住址城市**/
	@Column
	private String houseCity;

	/**创建时间**/
	@Column
	private Date createTime;

	/**借款金额**/
	@Column
	private BigDecimal borrowAmount;

	/**借款期限（月）**/
	@Column
	private Integer borrowDealine;

	/**借款人类型（1，个人，2，企业）**/
	@Column
	private Integer borrowerType;

	/**标的类型（1，信用标，2，抵押标）**/
	@Column
	private Integer borrowType;

	/**住址**/
	@Column
	private String address;

	/**备注**/
	@Column
	private String remarks;

	/**用户ID**/
	@Column
	private Long userId;
	
	/**用户名**/
	private String userName;
	/**借款验证码**/
	private String qbCode;
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName=borrowerName;
	}

	public String getBorrowerName() {
		return this.borrowerName;
	}

	public void setBorrowerCellPhone(String borrowerCellPhone) {
		this.borrowerCellPhone=borrowerCellPhone;
	}

	public String getBorrowerCellPhone() {
		return this.borrowerCellPhone;
	}

	public void setHouseProvince(String houseProvince) {
		this.houseProvince=houseProvince;
	}

	public String getHouseProvince() {
		return this.houseProvince;
	}
	
	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}

	public void setHouseCity(String houseCity) {
		this.houseCity=houseCity;
	}

	public String getHouseCity() {
		return this.houseCity;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount=borrowAmount;
	}

	public BigDecimal getBorrowAmount() {
		return this.borrowAmount;
	}

	public void setBorrowDealine(Integer borrowDealine) {
		this.borrowDealine=borrowDealine;
	}

	public Integer getBorrowDealine() {
		return this.borrowDealine;
	}

	public void setBorrowerType(Integer borrowerType) {
		this.borrowerType=borrowerType;
	}

	public Integer getBorrowerType() {
		return this.borrowerType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType=borrowType;
	}

	public Integer getBorrowType() {
		return this.borrowType;
	}

	public void setAddress(String address) {
		this.address=address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setRemarks(String remarks) {
		this.remarks=remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQbCode() {
		return qbCode;
	}

	public void setQbCode(String qbCode) {
		this.qbCode = qbCode;
	}
	
}