package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class TWithdrawCashDto {
	/****/
	@Id
	private Long id;

	/**订单号**/
	@Column
	private String ordId;

	/**订单时间**/
	@Column
	private Date ordDate;

	/**提现金额**/
	@Column
	private BigDecimal withdrawAmount;

	/**到账金额**/
	@Column
	private BigDecimal accountAmount;

	/**手续费**/
	@Column
	private BigDecimal feeAmount;

	/**创建时间**/
	@Column
	private Date createTime;

	/**提现状态（1，申请中，2，审核通过，3，审核不通过）**/
	@Column
	private Integer withdrawStatus;

	/**备注**/
	@Column
	private String remarks;

	/**用户ID**/
	@Column
	private Long userId;

	/**银行卡号**/
	@Column
	private Long bankCardId;

	/**处理人ID**/
	@Column
	private Long adminId;

	/**Ip地址**/
	@Column
	private String ip;
	private String userName;
	private String cellPhone;
	private String realName;

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

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount=withdrawAmount;
	}

	public BigDecimal getWithdrawAmount() {
		return this.withdrawAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount=accountAmount;
	}

	public BigDecimal getAccountAmount() {
		return this.accountAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount=feeAmount;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setWithdrawStatus(Integer withdrawStatus) {
		this.withdrawStatus=withdrawStatus;
	}

	public Integer getWithdrawStatus() {
		return this.withdrawStatus;
	}

	public void setRemarks(String remarks) {
		this.remarks=remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setBankCardId(Long bankCardId) {
		this.bankCardId=bankCardId;
	}

	public Long getBankCardId() {
		return this.bankCardId;
	}

	public void setAdminId(Long adminId) {
		this.adminId=adminId;
	}

	public Long getAdminId() {
		return this.adminId;
	}

	public void setIp(String ip) {
		this.ip=ip;
	}

	public String getIp() {
		return this.ip;
	}
}
