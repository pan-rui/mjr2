package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 积分**/
@Table(name="b_score")
public class BScore{
	/****/
	@Id
	private Long id;

	/****/
	@Column
	private Long userId;

	/**积分名称**/
	@Column
	private String scoreName;

	/**积分**/
	@Column
	private Integer score;

	/**备注**/
	@Column
	private String remarks;

	/**创建时间**/
	@Column
	private Date createTime;

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

	public void setScoreName(String scoreName) {
		this.scoreName=scoreName;
	}

	public String getScoreName() {
		return this.scoreName;
	}

	public void setScore(Integer score) {
		this.score=score;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setRemarks(String remarks) {
		this.remarks=remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

}