package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/** 账户表**/
@Table(name="t_account")
public class TAccount{
	/****/
	@Id
	private Long id;

	/**可用余额**/
	@Column
	private BigDecimal usableAmount;

	/**冻结金额**/
	@Column
	private BigDecimal frozenAmount;

	/**创建时间**/
	@Column
	private Date createTime;

	/**用户ID**/
	@Column
	private Long userId;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount=usableAmount;
	}

	public BigDecimal getUsableAmount() {
		return this.usableAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount=frozenAmount;
	}

	public BigDecimal getFrozenAmount() {
		return this.frozenAmount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

}