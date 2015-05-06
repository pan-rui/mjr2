package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

/** 转账**/
@Table(name="t_transfer")
public class TTransfer{
	/****/
	@Id
	private Long id;

	/**转出者ID**/
	@Column
	private Long transOutId;

	/**转入者ID**/
	@Column
	private Long transInId;

	/**订单号**/
	@Column
	private String ordId;

	/**订单时间**/
	@Column
	private Date ordDate;

	/**转账金额**/
	@Column
	private BigDecimal transAmount;

	/**创建时间**/
	@Column
	private Date createTime;

	/**转账状态（1，转账中，2，转账成功，3，转账失败）**/
	@Column
	private Integer tranStatus;

	/**手续费**/
	@Column
	private BigDecimal feeAmount;

	/**转账备注**/
	@Column
	private String remarks;

	/**状态备注**/
	@Column
	private String statusRemarks;

	/**转账序列号**/
	@Column
	private String seriesNumber;
	
	/**完成时间**/
	@Column
	private Date finishTime;
	
	/**转账类型**/
	@Column
	private String transType;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setTransOutId(Long transOutId) {
		this.transOutId=transOutId;
	}

	public Long getTransOutId() {
		return this.transOutId;
	}

	public void setTransInId(Long transInId) {
		this.transInId=transInId;
	}

	public Long getTransInId() {
		return this.transInId;
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

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount=transAmount;
	}

	public BigDecimal getTransAmount() {
		return this.transAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setTranStatus(Integer tranStatus) {
		this.tranStatus=tranStatus;
	}

	public Integer getTranStatus() {
		return this.tranStatus;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount=feeAmount;
	}

	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}

	public void setRemarks(String remarks) {
		this.remarks=remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks=statusRemarks;
	}

	public String getStatusRemarks() {
		return this.statusRemarks;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber=seriesNumber;
	}

	public String getSeriesNumber() {
		return this.seriesNumber;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	

}