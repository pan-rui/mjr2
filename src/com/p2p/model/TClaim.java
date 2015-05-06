package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

/** 债权转让表 **/
@Table(name = "t_claim")
public class TClaim {
	/****/
	@Id
	private Long id;

	/** 订单日期 **/
	@Column
	private Date ordDate;

	/** 订单号 **/
	@Column
	private String ordId;

	/** 债权价值 **/
	@Column
	private BigDecimal claimAmount;

	/** 债权年化收益 **/
	@Column
	private BigDecimal claimAnnualRate;

	/** 债权价格 **/
	@Column
	private BigDecimal claimPriceAmount;

	/** 债权本金 **/
	@Column
	private BigDecimal claimCapitalAmount;

	/** 债权收益 **/
	@Column
	private BigDecimal claimProfitAmount;

	/** 转让人的收益 **/
	@Column
	private BigDecimal claimUserProfitAmount;

	/** 债权预期收益 **/
	@Column
	private BigDecimal expectProfitAmount;

	/** 剩余债权价值 **/
	@Column
	private BigDecimal remainClaimAmount;

	/** 剩余债权价格 **/
	@Column
	private BigDecimal remainClaimPriceAmount;

	/** 剩余债权本金 **/
	@Column
	private BigDecimal remainClaimCapitalAmount;

	/** 剩余债权收益 **/
	@Column
	private BigDecimal remainClaimProfitAmount;

	/** 剩余债权预期收益 **/
	@Column
	private BigDecimal remainExpectProfitAmount;

	/** 债权状态(1申请中，2，转让中，3，已完成，4，已取消) **/
	@Column
	private Integer claimStatus;

	/** 创建时间 **/
	@Column
	private Date createTime;

	/** 债权人ID **/
	@Column
	private Long claimUserId;

	/** 投资ID **/
	@Column
	private Long investId;

	/** 借款ID **/
	@Column
	private Long borrowId;

	/** 剩余还款天数 **/
	@Column
	private Integer remainDays;

	/** 最后还款时间 **/
	@Column
	private Date lastRepayDate;

	/** 债权转让完时间 **/
	@Column
	private Date finishTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Date getOrdDate() {
		return this.ordDate;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getOrdId() {
		return this.ordId;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	public BigDecimal getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAnnualRate(BigDecimal claimAnnualRate) {
		this.claimAnnualRate = claimAnnualRate;
	}

	public BigDecimal getClaimAnnualRate() {
		return this.claimAnnualRate;
	}

	public void setClaimPriceAmount(BigDecimal claimPriceAmount) {
		this.claimPriceAmount = claimPriceAmount;
	}

	public BigDecimal getClaimPriceAmount() {
		return this.claimPriceAmount;
	}

	public void setClaimCapitalAmount(BigDecimal claimCapitalAmount) {
		this.claimCapitalAmount = claimCapitalAmount;
	}

	public BigDecimal getClaimCapitalAmount() {
		return this.claimCapitalAmount;
	}

	public void setClaimProfitAmount(BigDecimal claimProfitAmount) {
		this.claimProfitAmount = claimProfitAmount;
	}

	public BigDecimal getClaimProfitAmount() {
		return this.claimProfitAmount;
	}

	public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
		this.expectProfitAmount = expectProfitAmount;
	}

	public BigDecimal getExpectProfitAmount() {
		return this.expectProfitAmount;
	}

	public void setRemainClaimAmount(BigDecimal remainClaimAmount) {
		this.remainClaimAmount = remainClaimAmount;
	}

	public BigDecimal getRemainClaimAmount() {
		return this.remainClaimAmount;
	}

	public void setRemainClaimPriceAmount(BigDecimal remainClaimPriceAmount) {
		this.remainClaimPriceAmount = remainClaimPriceAmount;
	}

	public BigDecimal getRemainClaimPriceAmount() {
		return this.remainClaimPriceAmount;
	}

	public void setRemainClaimCapitalAmount(BigDecimal remainClaimCapitalAmount) {
		this.remainClaimCapitalAmount = remainClaimCapitalAmount;
	}

	public BigDecimal getRemainClaimCapitalAmount() {
		return this.remainClaimCapitalAmount;
	}

	public void setRemainClaimProfitAmount(BigDecimal remainClaimProfitAmount) {
		this.remainClaimProfitAmount = remainClaimProfitAmount;
	}

	public BigDecimal getRemainClaimProfitAmount() {
		return this.remainClaimProfitAmount;
	}

	public void setRemainExpectProfitAmount(BigDecimal remainExpectProfitAmount) {
		this.remainExpectProfitAmount = remainExpectProfitAmount;
	}

	public BigDecimal getRemainExpectProfitAmount() {
		return this.remainExpectProfitAmount;
	}

	public void setClaimStatus(Integer claimStatus) {
		this.claimStatus = claimStatus;
	}

	public Integer getClaimStatus() {
		return this.claimStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setClaimUserId(Long claimUserId) {
		this.claimUserId = claimUserId;
	}

	public Long getClaimUserId() {
		return this.claimUserId;
	}

	public void setInvestId(Long investId) {
		this.investId = investId;
	}

	public Long getInvestId() {
		return this.investId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public BigDecimal getClaimUserProfitAmount() {
		return claimUserProfitAmount;
	}

	public void setClaimUserProfitAmount(BigDecimal claimUserProfitAmount) {
		this.claimUserProfitAmount = claimUserProfitAmount;
	}

	public Integer getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	public Date getLastRepayDate() {
		return lastRepayDate;
	}

	public void setLastRepayDate(Date lastRepayDate) {
		this.lastRepayDate = lastRepayDate;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}