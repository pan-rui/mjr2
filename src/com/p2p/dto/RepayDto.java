package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class RepayDto extends BaseDto {

	/** 订单号 **/

	private String ordId;

	/** 投资ID **/

	private Long investId;

	/** 借款ID **/

	private Long borrowId;

	/** 投资人ID **/

	private Long investorId;

	/** 预定还款日期 **/

	private Date repayDate;

	/** 实际还款时间 **/

	private Date realRepayTime;

	/** 应还本金 **/

	private BigDecimal capitalAmount;

	/** 应还收益 **/

	private BigDecimal profitAmount;

	/** 剩余本金 **/

	private BigDecimal remainCapitalAmount;

	/** 剩余利息 **/

	private BigDecimal remainProfitAmount;

	/** 居间费用 **/

	private BigDecimal feeAmount;

	/** 还款状态（1为未还款，2为已还款） **/

	private Integer repayStatus;

	/** 总期数 **/

	private Integer peroids;

	/** 第几期 **/

	private Integer numOfPeriods;

	/** 创建时间 **/

	private Date createTime;

	private String borrowTitle;

	/** 借款人ID **/
	@Column
	private Long borrowerId;

	/** 年化利率 **/
	@Column
	private BigDecimal annualRate;

	/** 借款金额 **/

	private BigDecimal borrowAmount;

	/** 借款期限 **/

	private Integer deadline;

	/** 借款期限类型（1，为天，2为月） **/

	private Integer deadlineType;

	/** 借款人用户名 **/
	private String borrowerName;
	
	private String userName;
	
	private String realName;

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getOrdId() {
		return this.ordId;
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

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	public Long getInvestorId() {
		return this.investorId;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public Date getRepayDate() {
		return this.repayDate;
	}

	public void setRealRepayTime(Date realRepayTime) {
		this.realRepayTime = realRepayTime;
	}

	public Date getRealRepayTime() {
		return this.realRepayTime;
	}

	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount = capitalAmount;
	}

	public BigDecimal getCapitalAmount() {
		return this.capitalAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public BigDecimal getProfitAmount() {
		return this.profitAmount;
	}

	public void setRemainCapitalAmount(BigDecimal remainCapitalAmount) {
		this.remainCapitalAmount = remainCapitalAmount;
	}

	public BigDecimal getRemainCapitalAmount() {
		return this.remainCapitalAmount;
	}

	public void setRemainProfitAmount(BigDecimal remainProfitAmount) {
		this.remainProfitAmount = remainProfitAmount;
	}

	public BigDecimal getRemainProfitAmount() {
		return this.remainProfitAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setRepayStatus(Integer repayStatus) {
		this.repayStatus = repayStatus;
	}

	public Integer getRepayStatus() {
		return this.repayStatus;
	}

	public void setPeroids(Integer peroids) {
		this.peroids = peroids;
	}

	public Integer getPeroids() {
		return this.peroids;
	}

	public void setNumOfPeriods(Integer numOfPeriods) {
		this.numOfPeriods = numOfPeriods;
	}

	public Integer getNumOfPeriods() {
		return this.numOfPeriods;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getBorrowTitle() {
		return borrowTitle;
	}

	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}

	public Long getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}

	public BigDecimal getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public Integer getDeadlineType() {
		return deadlineType;
	}

	public void setDeadlineType(Integer deadlineType) {
		this.deadlineType = deadlineType;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	

}