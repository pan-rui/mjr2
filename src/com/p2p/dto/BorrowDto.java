package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class BorrowDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2518206884663386416L;
	private Long id;

	/** 借款标题 **/
	private String borrowTitle;

	/** 借款人ID **/
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

	/** 筹标期限（天） **/
	private Integer raisingPeriod;

	/** 最小投标金额 **/
	private BigDecimal minInvestAmount;

	/** 最大投标金额 **/
	private BigDecimal maxInvestAmount;

	/** 还款方式（1，一次性还款，2，为按月付息，到期还本,3等额本息） **/
	private Integer repayType;

	/** 借款详情 **/
	private String details;

	/** 风控意见 **/
	private String windControlTip;

	/** 已募集金额 **/
	private BigDecimal hasBorrowAmount;

	/** 创建日期 **/
	private Date createTime;

	/** 发标时间 **/
	private Date investStartTime;

	/** 满标时间 **/
	private Date fullTime;

	/** 还款日期 **/
	private Date repayDate;

	/** 借款编号 **/
	private String borrowNo;

	/** 标的类型（1，普通标） **/
	private Integer borrowType;

	/** 借款状态（1，申请中，2，初审通过，3，招标中，4，复审中，5，还款中，6，已还款，7，借款失败(初审)，8复审失败，9流标）**/
	private Integer borrowStatus;
	
	/** 放款时间 **/
	private Date auditTime;
	
	/** 审核人ID**/
	private Long firstAuditId;
	
	/** 复审ID**/
	private Long fullAuditId;
	
	/**借款人用户**/
	private String borrowerUserName;
	
	/**借款人姓名**/
	private String borrowerName;

	/**借款人身份证**/
	private String borrowerIdCard;

	/**借款人手机号**/
	private String borrowerCellPhone;
	
	private BigDecimal startAnnualRate;
	
	private BigDecimal endAnnualRate;
	
	private Integer startDeadline;
	
	private Integer endDeadline;
	
	
	

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

	public String getBorrowerUserName() {
		return borrowerUserName;
	}

	public void setBorrowerUserName(String borrowerUserName) {
		this.borrowerUserName = borrowerUserName;
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

	public String getBorrowerCellPhone() {
		return borrowerCellPhone;
	}

	public void setBorrowerCellPhone(String borrowerCellPhone) {
		this.borrowerCellPhone = borrowerCellPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getStartAnnualRate() {
		return startAnnualRate;
	}

	public void setStartAnnualRate(BigDecimal startAnnualRate) {
		this.startAnnualRate = startAnnualRate;
	}

	public BigDecimal getEndAnnualRate() {
		return endAnnualRate;
	}

	public void setEndAnnualRate(BigDecimal endAnnualRate) {
		this.endAnnualRate = endAnnualRate;
	}

	public Integer getStartDeadline() {
		return startDeadline;
	}

	public void setStartDeadline(Integer startDeadline) {
		this.startDeadline = startDeadline;
	}

	public Integer getEndDealdine() {
		return endDeadline;
	}

	public void setEndDeadline(Integer endDeadline) {
		this.endDeadline = endDeadline;
	}
	
	
}
