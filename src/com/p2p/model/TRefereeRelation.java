package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 推荐关系表**/
@Table(name="t_referee_relation")
public class TRefereeRelation{
	/****/
	@Id
	private Long id;

	/** 被推荐人ID**/
	@Column
	private Long userId;

	/**推荐人ID**/
	@Column
	private Long refereeId;

	/**创建时间**/
	@Column
	private Date createTime;
	
	private int couponStatus;

	public int getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(int couponStatus) {
		this.couponStatus = couponStatus;
	}

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

	public void setRefereeId(Long refereeId) {
		this.refereeId=refereeId;
	}

	public Long getRefereeId() {
		return this.refereeId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}