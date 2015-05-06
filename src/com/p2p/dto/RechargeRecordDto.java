package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class RechargeRecordDto implements Serializable{
	/****/
	@Id
	private Long id;

	/**充值金额**/
	@Column
	private BigDecimal rechargeAmount;

	/**到账金额**/
	@Column
	private BigDecimal rechargeRealAmount;

	/**充值费用**/
	@Column
	private BigDecimal feeAmount;

	/**充值时间**/
	@Column
	private Date createTime;

	/**充值结果（0，失败，1成功）**/
	@Column
	private Integer result;

	/**充值完成时间**/
	@Column
	private Date finishTime;

	/**订单时间**/
	@Column
	private Date ordDate;

	/**订单号**/
	@Column
	private String ordId;

	/**第三方订单号**/
	@Column
	private String thirdOrdId;

	/**充值银行**/
	@Column
	private String bankName;

	private String cellPhone;
	
	private String realName;
	
	private String userName;

	/**备注**/
	@Column
	private String remarks;

	/**ip地址**/
	@Column
	private String ip;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount=rechargeAmount;
	}

	public BigDecimal getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeRealAmount(BigDecimal rechargeRealAmount) {
		this.rechargeRealAmount=rechargeRealAmount;
	}

	public BigDecimal getRechargeRealAmount() {
		return this.rechargeRealAmount;
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

	public void setResult(Integer result) {
		this.result=result;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime=finishTime;
	}

	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate=ordDate;
	}

	public Date getOrdDate() {
		return this.ordDate;
	}

	public void setOrdId(String ordId) {
		this.ordId=ordId;
	}

	public String getOrdId() {
		return this.ordId;
	}

	public void setThirdOrdId(String thirdOrdId) {
		this.thirdOrdId=thirdOrdId;
	}

	public String getThirdOrdId() {
		return this.thirdOrdId;
	}

	public void setBankName(String bankName) {
		this.bankName=bankName;
	}

	public String getBankName() {
		return this.bankName;
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

	public void setRemarks(String remarks) {
		this.remarks=remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setIp(String ip) {
		this.ip=ip;
	}

	public String getIp() {
		return this.ip;
	}
	
	
}
