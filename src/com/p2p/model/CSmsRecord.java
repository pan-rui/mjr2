package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 短信发送记录**/
@Table(name="c_sms_record")
public class CSmsRecord{
	/****/
	@Id
	private Long id;

	/**注册IP或手机号**/
	@Column
	private String keyVal;

	/**创建时间**/
	@Column
	private Date createTime;

	/**次数**/
	@Column
	private Integer counts;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setKeyVal(String keys) {
		this.keyVal=keys;
	}

	public String getKeyVal() {
		return this.keyVal;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCounts(Integer counts) {
		this.counts=counts;
	}

	public Integer getCounts() {
		return this.counts;
	}

}