package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

public class InvestAwardRepayDto {
	/****/

	private Long id;

	private Long borrowId;

	private BigDecimal capitalAmount;

	private Integer isNew;

	private Integer numOfPeriods;

	private Long investorId;

	private String awardLevel;

	private BigDecimal realAmount;
	
	private String awardTimeLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getCapitalAmount() {
		return capitalAmount;
	}

	public void setCapitalAmount(BigDecimal capitalAmount) {
		this.capitalAmount = capitalAmount;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getNumOfPeriods() {
		return numOfPeriods;
	}

	public void setNumOfPeriods(Integer numOfPeriods) {
		this.numOfPeriods = numOfPeriods;
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getAwardTimeLevel() {
		return awardTimeLevel;
	}

	public void setAwardTimeLevel(String awardTimeLevel) {
		this.awardTimeLevel = awardTimeLevel;
	}
	
	

}