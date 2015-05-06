package com.p2p.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** 用户 **/
@Table(name = "c_user_detail")
public class CUserDetail {
	/****/
	@Id
	private Long id;

	/** 积分 **/
	@Column
	private Integer score;

	/****/
	@Column
	private Long userId;
	
	/**奖励级别**/
	@Column
	private String awardTimeLevel;//
	
	/**奖励级别过期时间**/
	@Column
	private Date awardExpirationDate;//

	/** 奖励级别 **/
	@Column
	private String awardLevel;
	
	/** 是否新手（0，否，1是） **/
	@Column
	private Integer isNew;

	public String getAwardTimeLevel() {
		return awardTimeLevel;
	}

	public void setAwardTimeLevel(String awardTimeLevel) {
		this.awardTimeLevel = awardTimeLevel;
	}

	public Date getAwardExpirationDate() {
		return awardExpirationDate;
	}

	public void setAwardExpirationDate(Date awardExpirationDate) {
		this.awardExpirationDate = awardExpirationDate;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	
	
}