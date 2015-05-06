package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class TInvestDto implements Serializable {
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

	/** 转让金额 **/
	@Column
	private BigDecimal transferAmount;

	/** 是否投资成功（0不成功，1成功） **/
	@Column
	private Integer result;

	/** 上级投资Id **/
	@Column
	private Long preInvestId;

	private String userName;
	private String cellPhone;
	private String realName;
	private String borrowTitle;
	private int repayType;
	private int borrowStatus;
	private BigDecimal annualRate;
	private int deadlineType;
	private int deadline;
	private BigDecimal capitalAmount;
	private BigDecimal profitAmount;
	private Date realRepayTime;
	private String attrName;
	private String borrowNo;
	private Date repayDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date investTimeup;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date investTimeend;
	
	private int repayStatus;

	public int getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(int repayStatus) {
		this.repayStatus = repayStatus;
	}

	public Date getInvestTimeup() {
		return investTimeup;
	}

	public void setInvestTimeup(Date investTimeup) {
		this.investTimeup = investTimeup;
	}

	public Date getInvestTimeend() {
		return investTimeend;
	}

	public void setInvestTimeend(Date investTimeend) {
		this.investTimeend = investTimeend;
	}

	private String thirdUserId;

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public String getBorrowNo() {
		return borrowNo;
	}

	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Date getRealRepayTime() {
		return realRepayTime;
	}

	public void setRealRepayTime(Date realRepayTime) {
		this.realRepayTime = realRepayTime;
	}

	public BigDecimal getCapitalAmount() {
		return capitalAmount;
	}

	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount = capitalAmount;
	}

	public BigDecimal getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}

	public int getDeadlineType() {
		return deadlineType;
	}

	public void setDeadlineType(int deadlineType) {
		this.deadlineType = deadlineType;
	}

	public String getBorrowTitle() {
		return borrowTitle;
	}

	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}

	public int getRepayType() {
		return repayType;
	}

	public void setRepayType(int repayType) {
		this.repayType = repayType;
	}

	public int getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(int borrowStatus) {
		this.borrowStatus = borrowStatus;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

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

	public void setPreInvestId(Long preInvestId) {
		this.preInvestId = preInvestId;
	}

	public Long getPreInvestId() {
		return this.preInvestId;
	}

	public String getThirdUserId() {
		return thirdUserId;
	}

	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}

}
