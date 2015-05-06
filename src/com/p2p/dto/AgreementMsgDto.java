package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgreementMsgDto implements Serializable{
	private String borrowerName;
	private String borrowerIdCard;
	private String realName;
	private BigDecimal realAmount;//借款本金额
	private int deadline;//借款期限
	private int deadlineType;//借款期限类型（1，为天，2为月）
	private BigDecimal profitAmount;
	private String userName;
	private String borrowPurpose;//借款用途
	private BigDecimal annualRate;//借款年利率
	private Date  auditTime; //放款日期
	private Date  avaryMout; //按月付息，付息日期
	private Date repayDate; //还款日期
	private int repayType; //还款方式
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerIdCard() {
		return borrowerIdCard;
	}
	public void setBorrowerIdCard(String borrowerIdCard) {
		this.borrowerIdCard = borrowerIdCard;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public BigDecimal getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	
	public int getDeadlineType() {
		return deadlineType;
	}
	public void setDeadlineType(int deadlineType) {
		this.deadlineType = deadlineType;
	}
	public BigDecimal getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public String getBorrowPurpose() {
		return borrowPurpose;
	}
	public void setBorrowPurpose(String borrowPurpose) {
		this.borrowPurpose = borrowPurpose;
	}
	public BigDecimal getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	public Date getAvaryMout() {
		return avaryMout;
	}
	public void setAvaryMout(Date avaryMout) {
		this.avaryMout = avaryMout;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public int getRepayType() {
		return repayType;
	}
	public void setRepayType(int repayType) {
		this.repayType = repayType;
	}
	
}
