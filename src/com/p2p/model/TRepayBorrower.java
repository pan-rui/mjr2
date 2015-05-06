package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

/** 借款人总还款表**/
@Table(name="t_repay_borrower")
public class TRepayBorrower{
	/****/
	@Id
	private Long id;

	/**借款ID**/
	@Column
	private Long borrowId;

	/**借款人ID**/
	@Column
	private Long borrwerId;

	/**预定还款日期**/
	@Column
	private Date repayDate;

	/**实际还款时间**/
	@Column
	private Date realRepayTime;

	/**应还本金**/
	@Column
	private BigDecimal capitalAmount;

	/**应还收益**/
	@Column
	private BigDecimal profitAmount;

	/**剩余本金**/
	@Column
	private BigDecimal remainCapitalAmount;

	/**剩余利息**/
	@Column
	private BigDecimal remainProfitAmount;

	/**还款状态（1为未还款，2为已还款，3还款处理中）**/
	@Column
	private Integer repayStatus;

	/**总期数**/
	@Column
	private Integer peroids;

	/**第几期**/
	@Column
	private Integer numOfPeriods;

	/**创建时间**/
	@Column
	private Date createTime;
	
	
	/**订单ID**/
	@Column
	private String ordId;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId=borrowId;
	}

	public Long getBorrowId() {
		return this.borrowId;
	}

	public void setBorrwerId(Long borrwerId) {
		this.borrwerId=borrwerId;
	}

	public Long getBorrwerId() {
		return this.borrwerId;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate=repayDate;
	}

	public Date getRepayDate() {
		return this.repayDate;
	}

	public void setRealRepayTime(Date realRepayTime) {
		this.realRepayTime=realRepayTime;
	}

	public Date getRealRepayTime() {
		return this.realRepayTime;
	}

	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount=capitalAmount;
	}

	public BigDecimal getCapitalAmount() {
		return this.capitalAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount=profitAmount;
	}

	public BigDecimal getProfitAmount() {
		return this.profitAmount;
	}

	public void setRemainCapitalAmount(BigDecimal remainCapitalAmount) {
		this.remainCapitalAmount=remainCapitalAmount;
	}

	public BigDecimal getRemainCapitalAmount() {
		return this.remainCapitalAmount;
	}

	public void setRemainProfitAmount(BigDecimal remainProfitAmount) {
		this.remainProfitAmount=remainProfitAmount;
	}

	public BigDecimal getRemainProfitAmount() {
		return this.remainProfitAmount;
	}

	public void setRepayStatus(Integer repayStatus) {
		this.repayStatus=repayStatus;
	}

	public Integer getRepayStatus() {
		return this.repayStatus;
	}

	public void setPeroids(Integer peroids) {
		this.peroids=peroids;
	}

	public Integer getPeroids() {
		return this.peroids;
	}

	public void setNumOfPeriods(Integer numOfPeriods) {
		this.numOfPeriods=numOfPeriods;
	}

	public Integer getNumOfPeriods() {
		return this.numOfPeriods;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	
	

}