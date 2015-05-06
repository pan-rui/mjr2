package com.p2p.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class TBankCardDto {
	/****/
	@Id
	private Long id;

	/**银行名称**/
	@Column
	private String bankName;

	/**支行名称**/
	@Column
	private String subBankName;

	/**银行编号**/
	@Column
	private String bankCode;

	/**银行卡号**/
	@Column
	private String bankCardNo;

	/**卡状态（1，审核中，2，审核通过，3审核不通过）**/
	@Column
	private Integer cardStatus;

	/**用户ID**/
	@Column
	private Long userId;

	/**创建时间**/
	@Column
	private Date createTime;
	
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

	public void setBankName(String bankName) {
		this.bankName=bankName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setSubBankName(String subBankName) {
		this.subBankName=subBankName;
	}

	public String getSubBankName() {
		return this.subBankName;
	}

	public void setBankCode(String bankCode) {
		this.bankCode=bankCode;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo=bankCardNo;
	}

	public String getBankCardNo() {
		return this.bankCardNo;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus=cardStatus;
	}

	public Integer getCardStatus() {
		return this.cardStatus;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
