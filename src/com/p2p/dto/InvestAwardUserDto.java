package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class InvestAwardUserDto implements Serializable{
	/****/
	@Id
	private Long id;

	/** 用户ID **/
	@Column
	private Long userId;

	/** 奖励级别 **/
	@Column
	private String awardLevel;

	/** 待收金额 **/
	@Column
	private BigDecimal dueinAmount;

	/** 创建时间 **/
	@Column
	private Date createTime;

	/** 投资ID **/
	@Column
	private Long investId;

	/****/
	@Column
	private BigDecimal awardRate;

	/** 奖励金额 **/
	@Column
	private BigDecimal awardAmount;

	/** 奖励状态（0，不发放，1，准备发放，2，发放中，3，发放成功,4,禁止发放） **/
	@Column
	private Integer awardStatus;

	/** 奖励类型(1,新手奖励，2，投资奖励，3，推荐收益奖励) **/
	@Column
	private Integer awardType;

	/** 奖励时间 **/
	@Column
	private Date awardTime;

	/** 借款ID **/
	@Column
	private Long borrowId;

	/** 奖励备注 **/
	@Column
	private String remarks;

	/** 期数 **/
	@Column
	private Integer numOfPeroids;

	/** 状态备注 **/
	@Column
	private String statusRemarks;
	
	/** 借款标题 **/
	@Column
	private String borrowTitle;
	
	private String userName;
	private String cellPhone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAwardLevel() {
		return awardLevel;
	}
	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}
	public BigDecimal getDueinAmount() {
		return dueinAmount;
	}
	public void setDueinAmount(BigDecimal dueinAmount) {
		this.dueinAmount = dueinAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getInvestId() {
		return investId;
	}
	public void setInvestId(Long investId) {
		this.investId = investId;
	}
	public BigDecimal getAwardRate() {
		return awardRate;
	}
	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}
	public BigDecimal getAwardAmount() {
		return awardAmount;
	}
	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}
	public Integer getAwardStatus() {
		return awardStatus;
	}
	public void setAwardStatus(Integer awardStatus) {
		this.awardStatus = awardStatus;
	}
	public Integer getAwardType() {
		return awardType;
	}
	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}
	public Date getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}
	public Long getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getNumOfPeroids() {
		return numOfPeroids;
	}
	public void setNumOfPeroids(Integer numOfPeroids) {
		this.numOfPeroids = numOfPeroids;
	}
	public String getStatusRemarks() {
		return statusRemarks;
	}
	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}
	public String getBorrowTitle() {
		return borrowTitle;
	}
	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
}
