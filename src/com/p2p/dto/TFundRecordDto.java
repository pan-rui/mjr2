package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.p2p.enums.FundRecordType;

public class TFundRecordDto implements Serializable {
	/** 资金记录表 **/

	/****/
	@Id
	private Long id;

	/** 操作金额 **/
	@Column
	private BigDecimal operAmount;

	/** 可用余额增加金额 **/
	@Column
	private BigDecimal inAmount;

	/** 可用余额减少金额 **/
	@Column
	private BigDecimal outAmount;

	/** 创建时间 **/
	@Column
	private Date createTime;

	/** 用户ID **/
	@Column
	private Long userId;

	/** 项目名称 **/
	@Column
	private String fundMode;

	/** 项目类型 **/
	@Column
	private Integer fundType;

	/** 备注 **/
	@Column
	private String remarks;

	/** 可用余额 **/
	@Column
	private BigDecimal usableAmount;

	/** 冻结金额 **/
	@Column
	private BigDecimal frozenAmount;

	/** 操作类型（1为添加可用余额，2，冻结可用余额，3为解冻可用余额，4为减少可用余额） **/
	@Column
	private Integer operType;

	/** 交易Id **/
	@Column
	private Long traderId;

	private String userName;
	private String cellPhone;
	private String realName;

	private FundRecordType fundRecordType;

	public void setId(Long id) {
		this.id = id;
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

	public Long getId() {
		return this.id;
	}

	public void setOperAmount(BigDecimal operAmount) {
		this.operAmount = operAmount;
	}

	public BigDecimal getOperAmount() {
		return this.operAmount;
	}

	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	public BigDecimal getInAmount() {
		return this.inAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public BigDecimal getOutAmount() {
		return this.outAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setFundMode(String fundMode) {
		this.fundMode = fundMode;
	}

	public String getFundMode() {
		return this.fundMode;
	}

	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}

	public Integer getFundType() {
		return this.fundType;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}

	public BigDecimal getUsableAmount() {
		return this.usableAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public BigDecimal getFrozenAmount() {
		return this.frozenAmount;
	}

	public FundRecordType getFundRecordType() {
		return fundRecordType;
	}

	public void setFundRecordType(FundRecordType fundRecordType) {
		this.fundRecordType = fundRecordType;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Long getTraderId() {
		return traderId;
	}

	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}
}
