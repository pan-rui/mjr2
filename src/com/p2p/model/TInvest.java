package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

/** 投资表 **/
@Table(name = "t_invest")
public class TInvest {
	/****/
	@Id
	private Long id;

	/** 订单号 **/
	@Column
	private String ordId;

	/** 订单时间 **/
	@Column
	private Date ordDate;

	/** 借款ID **/
	@Column
	private Long borrowId;

	/** 投资人Id **/
	@Column
	private Long investorId;

	/** 投资时间 **/
	@Column
	private Date investTime;

	/** 实际投资额度 **/
	@Column
	private BigDecimal realAmount;

	/** 投资金额 **/
	@Column
	private BigDecimal investAmount;

	/** 已转让金额 **/
	@Column
	private BigDecimal transferAmount;

	/** 是否投资成功（0处理中，1成功，2，投标失败） **/
	@Column
	private Integer result;

	/** 债权Id **/
	@Column
	private Long claimId;

	/** 是否转让债权（0否，1是） **/
	@Column
	private Integer isClaim;
	
	/** 投资备注  **/
	@Column
	private String remarks;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getOrdId() {
		return this.ordId;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Date getOrdDate() {
		return this.ordDate;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	public Long getInvestorId() {
		return this.investorId;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Date getInvestTime() {
		return this.investTime;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getRealAmount() {
		return this.realAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public BigDecimal getInvestAmount() {
		return this.investAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	public BigDecimal getTransferAmount() {
		return this.transferAmount;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getResult() {
		return this.result;
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public Integer getIsClaim() {
		return isClaim;
	}

	public void setIsClaim(Integer isClaim) {
		this.isClaim = isClaim;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}