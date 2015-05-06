package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/** 奖励推荐 **/
@Table(name = "t_invest_award")
public class TInvestAward {
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public String getAwardLevel() {
		return this.awardLevel;
	}

	public void setDueinAmount(BigDecimal dueinAmount) {
		this.dueinAmount = dueinAmount;
	}

	public BigDecimal getDueinAmount() {
		return this.dueinAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setInvestId(Long investId) {
		this.investId = investId;
	}

	public Long getInvestId() {
		return this.investId;
	}

	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}

	public BigDecimal getAwardRate() {
		return this.awardRate;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	public BigDecimal getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardStatus(Integer awardStatus) {
		this.awardStatus = awardStatus;
	}

	public Integer getAwardStatus() {
		return this.awardStatus;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public Integer getAwardType() {
		return this.awardType;
	}

	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}

	public Date getAwardTime() {
		return this.awardTime;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}

	public String getStatusRemarks() {
		return this.statusRemarks;
	}

	public Integer getNumOfPeroids() {
		return numOfPeroids;
	}

	public void setNumOfPeroids(Integer numOfPeroids) {
		this.numOfPeroids = numOfPeroids;
	}

	public String getBorrowTitle() {
		return borrowTitle;
	}

	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}
	
	

}