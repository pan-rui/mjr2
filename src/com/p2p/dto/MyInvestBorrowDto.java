package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class MyInvestBorrowDto {
	/****/
	@Id
	private Long id;

	/**订单号**/
	@Column
	private String ordId;

	/**订单时间**/
	@Column
	private Date ordDate;

	/**借款ID**/
	@Column
	private Long borrowId;

	/**投资人Id**/
	@Column
	private Long investorId;

	/**投资时间**/
	@Column
	private Date investTime;

	/**实际投资额度**/
	@Column
	private BigDecimal realAmount;

	/**投资金额**/
	@Column
	private BigDecimal investAmount;

	/**转让金额**/
	@Column
	private BigDecimal transferAmount;

	/**是否投资成功（0不成功，1成功）**/
	@Column
	private Integer result;

	/**上级投资Id**/
	@Column
	private Long preInvestId;
	
	private String borrowTitle;
	private int deadline;
	private BigDecimal profitAmount;
	private int borrowStatus;

	public int getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(int borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public String getBorrowTitle() {
		return borrowTitle;
	}

	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setOrdId(String ordId) {
		this.ordId=ordId;
	}

	public String getOrdId() {
		return this.ordId;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate=ordDate;
	}

	public Date getOrdDate() {
		return this.ordDate;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId=borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId=investorId;
	}

	public Long getInvestorId() {
		return this.investorId;
	}

	public void setInvestTime(Date investTime) {
		this.investTime=investTime;
	}

	public Date getInvestTime() {
		return this.investTime;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount=realAmount;
	}

	public BigDecimal getRealAmount() {
		return this.realAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount=investAmount;
	}

	public BigDecimal getInvestAmount() {
		return this.investAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount=transferAmount;
	}

	public BigDecimal getTransferAmount() {
		return this.transferAmount;
	}

	public void setResult(Integer result) {
		this.result=result;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setPreInvestId(Long preInvestId) {
		this.preInvestId=preInvestId;
	}

	public Long getPreInvestId() {
		return this.preInvestId;
	}
}
