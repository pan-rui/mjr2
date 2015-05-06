package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 第三方托管账户**/
@Table(name="t_thrid_account")
public class TThridAccount{
	/****/
	@Id
	private Long id;

	/****/
	@Column
	private Long userId;

	/**第三方用户ID**/
	@Column
	private String thirdUserId;
	
	/**第三方用户名**/
	@Column
	private String thirdUserName;

	/**是否开通(1，为开通，0为未开通)**/
	@Column
	private Integer isOpen;

	/**开通时间**/
	@Column
	private Date openTime;
	
	/**是否授权：0，未处理，1,：授权成功：2：授权失败**/
	@Column
	private Integer isAccredit;
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setThirdUserName(String thirdUserName) {
		this.thirdUserName=thirdUserName;
	}

	public String getThirdUserName() {
		return this.thirdUserName;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen=isOpen;
	}

	public Integer getIsOpen() {
		return this.isOpen;
	}

	public void setOpenTime(Date openTime) {
		this.openTime=openTime;
	}

	public Date getOpenTime() {
		return this.openTime;
	}

	public String getThirdUserId() {
		return thirdUserId;
	}

	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}

	public Integer getIsAccredit() {
		return isAccredit;
	}

	public void setIsAccredit(Integer isAccredit) {
		this.isAccredit = isAccredit;
	}
	
}