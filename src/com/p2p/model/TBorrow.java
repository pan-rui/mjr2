package com.p2p.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/** 借款表 **/
@Table(name = "t_borrow")
public class TBorrow {
	/****/
	@Id
	private Long id;

	/** 借款标题 **/
	@Column
	private String borrowTitle;

	/** 借款人ID **/
	@Column
	private Long borrowerId;

	/** 年化利率 **/
	@Column
	private BigDecimal annualRate;

	/** 借款金额 **/
	@Column
	private BigDecimal borrowAmount;

	/** 借款期限 **/
	@Column
	private Integer deadline;

	/** 借款期限类型（1，为天，2为月） **/
	@Column
	private Integer deadlineType;

	/** 筹标期限（天） **/
	@Column
	private Integer raisingPeriod;

	/** 最小投标金额 **/
	@Column
	private BigDecimal minInvestAmount;

	/** 最大投标金额 **/
	@Column
	private BigDecimal maxInvestAmount;

	/** 还款方式（1，一次性还款，2，为按月付息，到期还本,3等额本息） **/
	@Column
	private Integer repayType;

	/** 借款详情 **/
	@Column
	private String details;

	/** 风控意见 **/
	@Column
	private String windControlTip;

	/** 已募集金额 **/
	@Column
	private BigDecimal hasBorrowAmount;

	/** 创建日期 **/
	@Column
	private Date createTime;

	/** 发标时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column
	private Date investStartTime;

	/** 满标时间 **/
	@Column
	private Date fullTime;

	/** 还款日期 **/
	@Column
	private Date repayDate;

	/** 借款编号 **/
	@Column
	private String borrowNo;

	/** 标的类型（1，信用标，2，抵押标） **/
	@Column
	private Integer borrowType;

	/**
	 * 借款状态（1，申请中，2，初审通过，3，招标中，4，复审中，5，还款中，6，已还款，7，借款失败(初审)，8复审失败，9流标，10，
	 * 复审处理中,11 流标或复审不通过处理中）
	 **/
	@Column
	private Integer borrowStatus;

	/** 放款时间 **/
	@Column
	private Date auditTime;

	/** 审核人ID **/
	@Column
	private Long firstAuditId;

	/** 复审ID **/
	@Column
	private Long fullAuditId;

	/** 借款人个人信息Id **/
	@Column
	private Long personBorrowerId;

	/** 创建者ID **/
	@Column
	private Long createId;

	/** 最后修改人ID **/
	@Column
	private Long lastUpdateId;

	/** 最后修改时间 **/
	@Column
	private Date lastUpateTime;

	/** 借款订单号 **/
	@Column
	private String ordId;

	/** 投标锁定金额 **/
	@Column
	private BigDecimal clockAmount;

	/** 借款用途 **/
	@Column
	private String borrowPurpose;

	/**
	 * 进度
	 */
	private BigDecimal progress;
	/**
	 * 可投金额
	 */
	private BigDecimal mayCast;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBorrowTitle(String borrowTitle) {
		this.borrowTitle = borrowTitle;
	}

	public String getBorrowTitle() {
		return this.borrowTitle;
	}

	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Long getBorrowerId() {
		return this.borrowerId;
	}

	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}

	public BigDecimal getAnnualRate() {
		return this.annualRate;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public BigDecimal getBorrowAmount() {
		return this.borrowAmount;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public Integer getDeadline() {
		return this.deadline;
	}

	public void setDeadlineType(Integer deadlineType) {
		this.deadlineType = deadlineType;
	}

	public Integer getDeadlineType() {
		return this.deadlineType;
	}

	public void setRaisingPeriod(Integer raisingPeriod) {
		this.raisingPeriod = raisingPeriod;
	}

	public Integer getRaisingPeriod() {
		return this.raisingPeriod;
	}

	public void setMinInvestAmount(BigDecimal minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}

	public BigDecimal getMinInvestAmount() {
		return this.minInvestAmount;
	}

	public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}

	public BigDecimal getMaxInvestAmount() {
		return this.maxInvestAmount;
	}

	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
	}

	public Integer getRepayType() {
		return this.repayType;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return this.details;
	}

	public void setWindControlTip(String windControlTip) {
		this.windControlTip = windControlTip;
	}

	public String getWindControlTip() {
		return this.windControlTip;
	}

	public void setHasBorrowAmount(BigDecimal hasBorrowAmount) {
		this.hasBorrowAmount = hasBorrowAmount;
	}

	public BigDecimal getHasBorrowAmount() {
		return this.hasBorrowAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setInvestStartTime(Date investStartTime) {
		this.investStartTime = investStartTime;
	}

	public Date getInvestStartTime() {
		return this.investStartTime;
	}

	public void setFullTime(Date fullTime) {
		this.fullTime = fullTime;
	}

	public Date getFullTime() {
		return this.fullTime;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public Date getRepayDate() {
		return this.repayDate;
	}

	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}

	public String getBorrowNo() {
		return this.borrowNo;
	}

	public Integer getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getBorrowType() {
		return borrowType;
	}

	public Long getFirstAuditId() {
		return firstAuditId;
	}

	public void setFirstAuditId(Long firstAuditId) {
		this.firstAuditId = firstAuditId;
	}

	public Long getFullAuditId() {
		return fullAuditId;
	}

	public void setFullAuditId(Long fullAuditId) {
		this.fullAuditId = fullAuditId;
	}

	public Long getPersonBorrowerId() {
		return personBorrowerId;
	}

	public void setPersonBorrowerId(Long personBorrowerId) {
		this.personBorrowerId = personBorrowerId;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Long getLastUpdateId() {
		return lastUpdateId;
	}

	public void setLastUpdateId(Long lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	public Date getLastUpateTime() {
		return lastUpateTime;
	}

	public void setLastUpateTime(Date lastUpateTime) {
		this.lastUpateTime = lastUpateTime;
	}

	public BigDecimal getProgress() {
		return progress;
	}

	public void setProgress(BigDecimal progress) {
		this.progress = progress;
	}

	public BigDecimal getMayCast() {
		return mayCast;
	}

	public void setMayCast(BigDecimal mayCast) {
		this.mayCast = mayCast;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public BigDecimal getClockAmount() {
		return clockAmount;
	}

	public void setClockAmount(BigDecimal clockAmount) {
		this.clockAmount = clockAmount;
	}

	public String getBorrowPurpose() {
		return borrowPurpose;
	}

	public void setBorrowPurpose(String borrowPurpose) {
		this.borrowPurpose = borrowPurpose;
	}

}